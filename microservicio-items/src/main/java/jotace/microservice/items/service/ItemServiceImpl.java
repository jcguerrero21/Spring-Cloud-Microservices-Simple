package jotace.microservice.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import jotace.microservice.items.model.Item;
import jotace.app.microservicio.commons.entity.Producto;

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

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		
		ResponseEntity<Producto> response = clienteRestTemplate.exchange("http://servicio-productos/crear", HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRestTemplate.exchange("http://servicio-productos/editar/{id}",
				HttpMethod.PUT, body, Producto.class, pathVariables);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
		
		clienteRestTemplate.delete("http://servicio-productos/eliminar/{id}", pathVariables);
		
	}

}
