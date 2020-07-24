package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository; 
	
	public Persona findByMail(String mail) {
		return personaRepository.findByMail(mail);
	}
	
	public Persona findByRut(String rut) {
		return personaRepository.findByRut(rut);
	}
	
	public Persona findbyId(Long id) {
		return personaRepository.getOne(id);
	}
	
	public void insertarPersona(Persona persona) {
		personaRepository.saveAndFlush(persona);
	}
	
//	public List<Persona> findByEstado(String estado){
//		return personaRepository.findByEstado(estado);
//	}
//	
//	public List<Persona> finByEstadoAndRut(String estado, String rut) {
//		return personaRepository.findByEstadoAndRut(estado, rut);
//	}
//	
//	public List<Persona> findAllByRut(String rut) {
//		return personaRepository.findAllByRut(rut);
//	}
}
