package jotace.microservice.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jotace.app.microservicio.commons.entity.Producto;
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

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);

		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());

		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
