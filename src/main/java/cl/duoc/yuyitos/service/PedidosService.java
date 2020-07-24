package cl.duoc.yuyitos.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.repository.PedidoRepository;

@Service
public class PedidosService {

	@Autowired
	private PedidoRepository pedidoRepository; 
	
	public List<Pedido> findAll(){
			return pedidoRepository.findAllByOrderByIdPedidoDesc();
			
	}
	
	public Pedido findByIdPedido(Long id) {
		
		return pedidoRepository.getOne(id);
	}
	
	public void updatePedidoRecibido(Pedido pedido) {
		
		Date fecha = new Date();
		pedido.setFechaRecibido(fecha);
		
		pedidoRepository.saveAndFlush(pedido);
		
	}
	
	public void updatePedidoRevisado(Pedido pedido, int estado) {
		Date fecha = new Date();
		
		System.out.println(fecha);
		System.out.println(estado);
		pedido.setFechaRevisado(fecha);
		pedido.setEstado(estado);
		
		pedidoRepository.saveAndFlush(pedido);
	}
	
	public void insertarPedido(Pedido pedido) {
		
		pedidoRepository.saveAndFlush(pedido);
	}
}
