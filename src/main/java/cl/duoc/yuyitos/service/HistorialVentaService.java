package cl.duoc.yuyitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.HistorialVenta;
import cl.duoc.yuyitos.repository.HistorialVentaRepository;

@Service
public class HistorialVentaService {
	
	@Autowired
	private HistorialVentaRepository historialVentaRepository;
	
	public void insertarHistorial(HistorialVenta historial) {
		historialVentaRepository.saveAndFlush(historial);
	}

}
