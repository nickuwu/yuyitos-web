package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.entity.Proveedor;
import cl.duoc.yuyitos.repository.ProveedorRepository;

@Service 
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository; 
	
	public List<Proveedor> findAll(){
		return proveedorRepository.findAll();

	}
	
	public Proveedor findByIdidProveedor(Long id) {
		
		return proveedorRepository.getOne(id);
	}
	
}


