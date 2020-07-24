package cl.duoc.yuyitos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

	//@Query(value = "SELECT v FROM venta v WHERE persona = ?1")
	List<Venta> findByPersonaOrderByIdVentaDesc(Persona persona);
	
	List<Venta> findAllByOrderByIdVentaDesc();
	
	List<Venta> findByFechaVentaGreaterThanEqualAndFechaVentaLessThanEqual(Date inicio, Date fin);
	
	List<Venta> findByFechaVentaGreaterThanEqual(Date inicio);
	
	List<Venta> findByFechaVentaLessThanEqual(Date fin);
}
