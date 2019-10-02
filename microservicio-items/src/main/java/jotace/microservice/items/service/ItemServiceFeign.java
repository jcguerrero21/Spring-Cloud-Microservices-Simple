package jotace.microservice.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jotace.microservice.items.feign.FeignProductoRest;
import jotace.microservice.items.model.Item;
import jotace.app.microservicio.commons.entity.Producto;

@Service("ServiceFeign")
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

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}
}
