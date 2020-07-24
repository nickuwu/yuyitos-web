package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Marca;
import cl.duoc.yuyitos.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;

	public List<Marca> findAll(){
			return marcaRepository.findAll();

	}
	public Marca findByIdMarca(Long id) {
		
		return marcaRepository.getOne(id);
	}
}
