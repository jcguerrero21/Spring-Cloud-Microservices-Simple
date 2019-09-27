package jotace.microservice.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jotace.microservice.productos.entity.Producto;
import jotace.microservice.productos.service.IProductoService;

@RestController
public class ProductoController {

	@Value("${server.port}")
	private Integer port;

	private IProductoService productoService;

	@Autowired
	public ProductoController(IProductoService productoService, Environment env) {
		this.productoService = productoService;
	}

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		producto.setPort(port);

		try {
			Thread.sleep(2000L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return producto;
	}

}
