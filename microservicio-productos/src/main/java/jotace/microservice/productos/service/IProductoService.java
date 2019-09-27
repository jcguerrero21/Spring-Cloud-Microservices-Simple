package jotace.microservice.productos.service;

import java.util.List;

import jotace.microservice.productos.entity.Producto;

public interface IProductoService {

	 /**
     * Método para retornar todos los productos
     *
     * @return Lista de productos
     */
    public List<Producto> findAll();

    /**
     * Método que busca un producto por su id
     *
     * @param id
     * @return Un único producto
     */
    public Producto findById(Long id);
	
}
