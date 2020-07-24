package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Marca;
import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.TipoProducto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query(value = "SELECT p FROM Producto p WHERE tipoProducto = ?1")
	List<Producto> findByTipoProducto(TipoProducto tipoProducto);
	
	@Query(value = "SELECT p FROM Producto p WHERE marca = ?1")
	List<Producto> findByMarca(Marca marca);
	
	@Query(value = "SELECT p FROM Producto p WHERE marca = ?1 and tipoProducto = ?2")
	List<Producto> findByMarcaAndTipoProducto(TipoProducto tipoProducto, Marca marca);
	
}
