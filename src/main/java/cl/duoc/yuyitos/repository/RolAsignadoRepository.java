package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Rol;
import cl.duoc.yuyitos.entity.RolAsignado;

@Repository
public interface RolAsignadoRepository extends JpaRepository<RolAsignado, Long>{

	//@Query("SELECT r from RolAsignado r WHERE r.rol = ?1")
	List<RolAsignado> findByRol(Rol rol);

	List<RolAsignado> findByPersona(Persona persona);
	
}
