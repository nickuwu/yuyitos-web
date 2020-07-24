package cl.duoc.yuyitos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Usuario;
import cl.duoc.yuyitos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public Usuario findByIdUsuario(Long id) {
		
		return usuarioRepository.getOne(id);
	}
}
