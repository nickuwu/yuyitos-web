package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.DetalleVenta;
import cl.duoc.yuyitos.entity.Venta;
import cl.duoc.yuyitos.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {
	
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	public List<DetalleVenta> findByVenta(Venta venta){
		return detalleVentaRepository.findByVenta(venta);
	}

	public DetalleVenta findByIdDetalle (Long id) {
		return detalleVentaRepository.getOne(id);
	}
	
	public void insertarDetalleVenta(List<DetalleVenta> detalle) {
		detalleVentaRepository.saveAll(detalle);
	}
}
