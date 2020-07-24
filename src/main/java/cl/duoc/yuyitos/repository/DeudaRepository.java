package cl.duoc.yuyitos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.Venta;

@Repository
public interface DeudaRepository extends JpaRepository<Deuda, Long>{
	
	@Query("SELECT d from Deuda d WHERE d.venta = ?1")
	Deuda findByVenta(Venta venta);

}
