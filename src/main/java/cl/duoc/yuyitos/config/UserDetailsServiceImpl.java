package cl.duoc.yuyitos.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.RolAsignado;
import cl.duoc.yuyitos.entity.Usuario;
import cl.duoc.yuyitos.repository.PersonaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Persona persona = personaRepository.findByMail(username);
		System.out.println(persona);
		if (persona == null) throw new UsernameNotFoundException(username);
		System.out.println(persona.getNombre());
		
		String password = null;
        for (Usuario usuario : persona.getUsuario()) {
        	password = usuario.getPassword();
        }
        
        System.out.println(username);
        System.out.println(password);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RolAsignado role : persona.getRolesAsignados()){
        	System.out.println(role.getRol().getNombre());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRol().getNombre()));
        }
        
        
		return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
	} 
}
