package cl.duoc.yuyitos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.repository.VentaRepository;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Venta;

@Service
public class VentaService {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	public List<Venta> findAll(){
		return ventaRepository.findAllByOrderByIdVentaDesc();
	}

	public Venta findByIdVenta(Long id) {
		return ventaRepository.getOne(id);
	}
	
	public void insertarVenta(Venta venta) {
		ventaRepository.saveAndFlush(venta);
	}
	
	public List<Venta> findByPersona(Persona persona){
		return ventaRepository.findByPersonaOrderByIdVentaDesc(persona);
	}

	public List<Venta> findByFechas(String desde, String hasta) throws ParseException{
		List<Venta> lista = null;
		Date inicio = null;
		Date fin = null;
		
		if(desde != "") { 
			inicio = new SimpleDateFormat("yyyy-MM-dd").parse(desde);
		}
		if(hasta != "") { 	
			fin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(hasta+" 23:59:59");
		}
		
		if (inicio != null && fin != null) {
			// >= inicio && <= fin
			lista = ventaRepository.findByFechaVentaGreaterThanEqualAndFechaVentaLessThanEqual(inicio, fin);
		} else if (inicio != null || fin == null) {
			// >= inicio
			lista = ventaRepository.findByFechaVentaGreaterThanEqual(inicio);
		} else if (inicio == null || fin != null) {
			// <= fin
			lista = ventaRepository.findByFechaVentaLessThanEqual(fin); 
		}
		
		return lista;
		
	}
}
