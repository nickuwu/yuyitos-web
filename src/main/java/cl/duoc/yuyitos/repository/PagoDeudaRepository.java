package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.PagoDeuda;

@Repository
public interface PagoDeudaRepository extends JpaRepository<PagoDeuda, Long>{

	//@Query("SELECT p from PagoDeuda p WHERE p.deuda = ?1")
	List<PagoDeuda> findByDeudaOrderByIdPagoDeudaDesc(Deuda deuda);
}
