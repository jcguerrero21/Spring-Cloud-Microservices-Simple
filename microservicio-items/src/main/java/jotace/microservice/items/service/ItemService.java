package jotace.microservice.items.service;

import java.util.List;

import jotace.microservice.items.model.Item;
import jotace.app.microservicio.commons.entity.Producto;

public interface ItemService {
	
	/**
     * Método para listar todos los items
     *
     * @return Listado de Items
     */
    List<Item> findAll();

    /**
     * Método para listar un item concreto y su cantidad
     *
     * @param id
     * @param cantidad
     * @return Item
     */
    Item findById(Long id, Integer cantidad);
    
    /**
     * Método para guardar un producto
     * 
     * @param producto
     * @return producto
     */
    Producto save(Producto producto);
    
    /**
     * Método para actualizar un producto
     * 
     * @param producto
     * @param id
     * @return producto
     */
    Producto update(Producto producto, Long id);
    
    /**
     * Método para borrar un producto
     * 
     * @param id
     */
    void delete(Long id);
	
}
