package jotace.app.microservicio.oauth.service;

import jotace.app.microservicio.usuarios.commons.entity.Usuario;

public interface IUsuarioService {
	
	Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
	
}
