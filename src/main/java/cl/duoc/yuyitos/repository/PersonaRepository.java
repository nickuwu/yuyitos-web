package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query("SELECT p from Persona p WHERE p.mail = ?1")
	Persona findByMail(String mail);
	
	@Query("SELECT p from Persona p WHERE p.rut = ?1")
	Persona findByRut(String rut);
}
