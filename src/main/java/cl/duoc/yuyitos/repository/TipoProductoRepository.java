package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.FamiliaProducto;
import cl.duoc.yuyitos.entity.TipoProducto;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long>  {
	
	@Query(value = "SELECT t FROM TipoProducto t WHERE familia = ?1")
	List<TipoProducto> findByFamilia(FamiliaProducto familia);

}
