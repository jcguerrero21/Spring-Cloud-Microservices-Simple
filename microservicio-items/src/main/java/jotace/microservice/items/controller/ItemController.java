package jotace.microservice.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import jotace.microservice.items.model.Item;
import jotace.microservice.items.model.Producto;
import jotace.microservice.items.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/listar")
	public List<Item> llistar() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Samsung TV");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
	}

}
