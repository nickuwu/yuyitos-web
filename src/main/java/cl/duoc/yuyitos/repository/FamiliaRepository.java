package cl.duoc.yuyitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.FamiliaProducto;

@Repository
public interface FamiliaRepository extends JpaRepository<FamiliaProducto, Long>  {

}
