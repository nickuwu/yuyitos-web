package cl.duoc.yuyitos.service;

import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.Venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cl.duoc.yuyitos.repository.DeudaRepository;

@Service
public class DeudaService {

	@Autowired
	private DeudaRepository deudaRepository;
	
	public List<Deuda> findByAll(){
		return deudaRepository.findAll();
	}
	
	public Deuda findById(Long id) {
		return deudaRepository.getOne(id);
	}
	
	public void insertarDeuda(Deuda deuda) {
		deudaRepository.saveAndFlush(deuda);
	}
	
	public Deuda findByVenta(Venta venta) {
		return deudaRepository.findByVenta(venta); 
	}
}
