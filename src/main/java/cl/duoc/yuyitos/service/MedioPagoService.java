package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.repository.MedioPagoRepository;
import cl.duoc.yuyitos.entity.MedioPago;

@Service
public class MedioPagoService {

	@Autowired
	private MedioPagoRepository medioPagoRepository;
	
	public List<MedioPago> findAll(){
		return medioPagoRepository.findAll();
	}
	
	public MedioPago findByIdMedioPago(Long id) {
		return medioPagoRepository.getOne(id);
	}
}
