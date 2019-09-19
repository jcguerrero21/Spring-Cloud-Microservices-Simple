package jotace.spring.cloud.items.demo.service;

import jotace.spring.cloud.items.demo.model.Item;
import jotace.spring.cloud.items.demo.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

    private RestTemplate clienteRestTemplate;

    @Autowired
    public ItemServiceImpl(RestTemplate restTemplate){
        this.clienteRestTemplate = restTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRestTemplate.getForObject("http://servicio-productos/listar", Producto[].class));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = clienteRestTemplate.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

}
