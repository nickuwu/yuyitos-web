package cl.duoc.yuyitos.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.DetallePedido;
import cl.duoc.yuyitos.entity.Pedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

	@Query(value = "SELECT p FROM DetallePedido p WHERE pedido = ?1")
	List<DetallePedido> findByPedido(Pedido pedido);
	
}
