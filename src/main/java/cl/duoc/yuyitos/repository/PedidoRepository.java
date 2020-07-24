package cl.duoc.yuyitos.repository;

import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Pedido;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{


	List<Pedido> findAllByOrderByIdPedidoDesc();
	
} 

