package cl.duoc.yuyitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Rol;
import cl.duoc.yuyitos.repository.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository rolRepository;

	public Rol findByIdRol(Long id) {
		
		return rolRepository.getOne(id);
	}
}
