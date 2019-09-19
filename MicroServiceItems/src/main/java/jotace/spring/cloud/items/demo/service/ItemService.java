package jotace.spring.cloud.items.demo.service;

import jotace.spring.cloud.items.demo.model.Item;

import java.util.List;

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
