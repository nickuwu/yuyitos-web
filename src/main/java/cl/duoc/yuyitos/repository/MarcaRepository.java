package cl.duoc.yuyitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
