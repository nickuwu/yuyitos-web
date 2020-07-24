package cl.duoc.yuyitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.yuyitos.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

}
