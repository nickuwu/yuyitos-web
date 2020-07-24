package cl.duoc.yuyitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Precio;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.repository.PrecioRepository;

@Service 
public class PrecioService {

	@Autowired
	private PrecioRepository precioRepository;
	
	public Precio findByProducto(Producto producto) {
		return precioRepository.findByProducto(producto);
	}
}
