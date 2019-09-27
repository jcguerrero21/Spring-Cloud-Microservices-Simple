package jotace.microservice.productos.dao;

import org.springframework.data.repository.CrudRepository;

import jotace.microservice.productos.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long> {

}
