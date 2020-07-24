package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Rol;
import cl.duoc.yuyitos.entity.RolAsignado;
import cl.duoc.yuyitos.repository.RolAsignadoRepository;

@Service
public class RolAsignadoService {
	
	@Autowired
	private RolAsignadoRepository rolAsignadoRepository;

	public RolAsignado findByIdRolAsignado(Long id) {
			
			return rolAsignadoRepository.getOne(id);
	}
	
	public List<RolAsignado> findAllByRol(Rol rol){
		return rolAsignadoRepository.findByRol(rol);
	}
	
	public List<RolAsignado> findAllByPersona(Persona persona){
		return rolAsignadoRepository.findByPersona(persona);
	}
	
	public void insertarRolAsignado(RolAsignado rolAsignado) {
		rolAsignadoRepository.saveAndFlush(rolAsignado);
	}
}
