package cl.duoc.yuyitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.CodigoBarra;
import cl.duoc.yuyitos.repository.CodigoBarraRepository;

@Service
public class CodigoBarraService {
	
	@Autowired
	private CodigoBarraRepository codigoBarraRepository;

	public CodigoBarra findbyCodigo(String codigo) {
		return codigoBarraRepository.findByCodigoDeBarra(codigo);
	}
	
}
