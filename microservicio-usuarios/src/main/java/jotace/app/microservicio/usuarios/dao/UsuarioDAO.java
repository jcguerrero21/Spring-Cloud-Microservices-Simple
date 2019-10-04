package jotace.app.microservicio.usuarios.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import jotace.app.microservicio.usuarios.commons.entity.Usuario;

@RepositoryRestResource(path="usuarios")
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {
	
	@RestResource(path="buscar-username")
	Usuario findByUsername(@Param("username") String username);

	@Query(value="select * from Usuario u where u.username = ?1", nativeQuery = true)
	Usuario obtenerPorUsername(String username);

}
