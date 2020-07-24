package cl.duoc.yuyitos.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.CodigoBarra;

@Repository
public interface CodigoBarraRepository extends JpaRepository<CodigoBarra, Long>{

	@Query("SELECT c FROM codigoBarra c WHERE c.codigoBarra = ?1")
	CodigoBarra findByCodigoDeBarra(String codigo);
	
}
