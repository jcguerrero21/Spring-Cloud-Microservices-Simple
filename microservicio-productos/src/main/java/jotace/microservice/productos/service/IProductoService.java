package jotace.microservice.productos.service;

import java.util.List;

import jotace.app.microservicio.commons.entity.Producto;

public interface IProductoService {

	 /**
     * Método para retornar todos los productos
     *
     * @return Lista de productos
     */
    List<Producto> findAll();

    /**
     * Método que busca un producto por su id
     *
     * @param id
     * @return Un único producto
     */
    Producto findById(Long id);
    
    /**
     * Método para guardar un producto
     * 
     * @param producto
     * @return
     */
    Producto save(Producto producto);
    
    /**
     * Método para eliminar un producto por su id
     * 
     * @param id
     */
    void deleteById(Long id);
	
}
