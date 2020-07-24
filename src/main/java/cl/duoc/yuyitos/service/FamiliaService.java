package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.FamiliaProducto;
import cl.duoc.yuyitos.repository.FamiliaRepository;

@Service
public class FamiliaService {
	
	@Autowired
	FamiliaRepository familiaRepository;
	
	public List<FamiliaProducto> findAll(){
			return familiaRepository.findAll();
			
	}
	public FamiliaProducto findByIdFamilia(Long id) {
		
		return familiaRepository.getOne(id);
	}

}
