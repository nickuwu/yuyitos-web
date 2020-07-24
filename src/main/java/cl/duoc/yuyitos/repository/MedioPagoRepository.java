package cl.duoc.yuyitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.MedioPago;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {

}
