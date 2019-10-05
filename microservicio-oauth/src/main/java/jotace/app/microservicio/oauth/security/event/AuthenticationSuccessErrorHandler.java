package jotace.app.microservicio.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import feign.FeignException;
import jotace.app.microservicio.oauth.service.IUsuarioService;
import jotace.app.microservicio.usuarios.commons.entity.Usuario;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
				
		if(usuario.getIntentosLogin() != null && usuario.getIntentosLogin() > 0) {
			usuario.setIntentosLogin(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);

		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentosLogin() == null) {
				usuario.setIntentosLogin(0);
			}
			
			log.info("Intentos actual es de: " + usuario.getIntentosLogin());
			usuario.setIntentosLogin(usuario.getIntentosLogin() + 1);
			log.info("Intentos después es de: " + usuario.getIntentosLogin());
			
			errors.append(" : Intentos del login: " + usuario.getIntentosLogin());

			if (usuario.getIntentosLogin() >= 3) {
				String errorMaxIntentos = String.format("El usuario %s deshabilitado por máximos intentos", usuario.getUsername());
				log.error(errorMaxIntentos);
				errors.append(" : " + errorMaxIntentos);
				usuario.setEnabled(false);
			}

			usuarioService.update(usuario, usuario.getId());
			
			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}

}
