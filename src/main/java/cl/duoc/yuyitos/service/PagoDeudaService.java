package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.PagoDeuda;
import cl.duoc.yuyitos.repository.PagoDeudaRepository;

@Service
public class PagoDeudaService {

	@Autowired
	private PagoDeudaRepository pagoDeudaRepository;
	
	public void insertarPagoDeuda(PagoDeuda pago) {
		pagoDeudaRepository.saveAndFlush(pago);
	}
	
	public List<PagoDeuda> findbyDeuda(Deuda deuda){
		return pagoDeudaRepository.findByDeudaOrderByIdPagoDeudaDesc(deuda);
	}
}
