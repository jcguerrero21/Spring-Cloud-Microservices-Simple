package jotace.microservice.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jotace.microservice.productos.dao.ProductoDAO;
import jotace.microservice.productos.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	private ProductoDAO productoDAO;

    @Autowired
    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Producto> findAll() {
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoDAO.findById(id).orElse(null);
    }
	
}
