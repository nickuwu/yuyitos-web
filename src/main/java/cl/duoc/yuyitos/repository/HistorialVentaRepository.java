package cl.duoc.yuyitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.HistorialVenta;

@Repository
public interface HistorialVentaRepository extends JpaRepository<HistorialVenta, Long> {

}
