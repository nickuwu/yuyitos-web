package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.DetallePedido;
import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService { 

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	
	public List<DetallePedido> findByPedido(Pedido pedido){
		
		return detallePedidoRepository.findByPedido(pedido);
	}
	
	public DetallePedido findByIdDetalle (Long id) { 
		return detallePedidoRepository.getOne(id);
	}
	
	public void updateDatellePedidoEstado(DetallePedido detalle, String motivoEstado, Integer estado) {
		
		detalle.setEstado(estado);
		detalle.setMotivoEstado(motivoEstado);
		
		detallePedidoRepository.saveAndFlush(detalle);
	}
	
	public void insertarDetallePedido(List<DetallePedido> detalle) {
		detallePedidoRepository.saveAll(detalle);
	}
}
