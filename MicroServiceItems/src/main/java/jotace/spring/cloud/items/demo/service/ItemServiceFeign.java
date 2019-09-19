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
@Primary
public class ItemServiceFeign implements ItemService {

    private ProductoClienteRest clienteFeign;

    @Autowired
    public  ItemServiceFeign(ProductoClienteRest productoClienteRest){
        this.clienteFeign = productoClienteRest;
    }

    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream().map(producto -> new Item(producto,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad);
    }
}
