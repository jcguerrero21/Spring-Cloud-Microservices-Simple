package jotace.microservice.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jotace.microservice.items.feign.FeignProductoRest;
import jotace.microservice.items.model.Item;

@Service("ServiceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

    @Autowired
    private FeignProductoRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream().map(prod -> new Item(prod,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad);
    }
}
