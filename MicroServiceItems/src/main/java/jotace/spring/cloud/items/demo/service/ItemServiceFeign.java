package jotace.spring.cloud.items.demo.service;

import jotace.spring.cloud.items.demo.feignclients.ProductoClienteRest;
import jotace.spring.cloud.items.demo.model.Item;
import jotace.spring.cloud.items.demo.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ServiceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductoClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream().map(prod -> new Item(prod,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad);
    }
}
