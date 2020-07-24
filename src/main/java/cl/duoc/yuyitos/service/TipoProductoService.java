package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.FamiliaProducto;
import cl.duoc.yuyitos.entity.TipoProducto;
import cl.duoc.yuyitos.repository.FamiliaRepository;
import cl.duoc.yuyitos.repository.TipoProductoRepository;

@Service
public class TipoProductoService {
	
	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	@Autowired
	private FamiliaRepository familiaRepository;

	public List<TipoProducto> findByFamilia(Long idFamilia){
		List<TipoProducto> tipoProducto = null;
		
		if(idFamilia != null) {
			FamiliaProducto familia = familiaRepository.getOne(idFamilia);
			tipoProducto = tipoProductoRepository.findByFamilia(familia);
		}
		return tipoProducto;
	}
}
