package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.DetalleVenta;
import cl.duoc.yuyitos.entity.Venta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>  {

	@Query(value = "SELECT p FROM DetalleVenta p WHERE venta = ?1")
	List<DetalleVenta> findByVenta(Venta venta);
}
