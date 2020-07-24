package cl.duoc.yuyitos.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Precio;
import cl.duoc.yuyitos.entity.Producto;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long>{

	@Query("SELECT p FROM Precio p WHERE p.producto = ?1")
	Precio findByProducto(Producto producto);
}
