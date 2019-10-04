package jotace.app.microservicio.oauth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jotace.app.microservicio.oauth.feign.UsuarioFeignClient;
import jotace.app.microservicio.usuarios.commons.entity.Usuario;

@Service	
public class UsuarioService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUsername(username);

		if (usuario == null) {
			log.error("Error en el login, no existe el usuario'" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario'" + username + "' en el sistema");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.peek(authority -> log.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());
		// los roles tienen que ser del tipo spring security que son GrantedAuthority por lo tanto tenemos que convertir los nuestros a ese tipo
		
		log.info("Usuario autenticado: " + username);
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
