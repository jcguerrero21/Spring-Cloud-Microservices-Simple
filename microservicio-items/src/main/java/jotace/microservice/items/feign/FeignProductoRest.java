package jotace.microservice.items.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jotace.microservice.items.model.Producto;

@FeignClient(name = "servicio-productos")
public interface FeignProductoRest {

	@GetMapping("/listar")
	public List<Producto> listar();

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);

}
