package jotace.example.cloud.dao;

import jotace.example.cloud.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDAO extends CrudRepository<Producto, Long> {
}
