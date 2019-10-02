package jotace.microservice.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jotace.microservice.productos.dao.ProductoDAO;
import jotace.app.microservicio.commons.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	private ProductoDAO productoDAO;

	@Autowired
	public ProductoServiceImpl(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDAO.deleteById(id);
	}

}
