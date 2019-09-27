package jotace.microservice.items.service;

import java.util.List;

import jotace.microservice.items.model.Item;

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
     * @return
     */
    Item findById(Long id, Integer cantidad);
	
}
