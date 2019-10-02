package jotace.microservice.productos.dao;

import org.springframework.data.repository.CrudRepository;

import jotace.app.microservicio.commons.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long> {

}
