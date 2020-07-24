package cl.duoc.yuyitos.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "rol")
public class Rol {
	
	@Id
	@Column(name ="id_rol")
	private Long idRol;
	
	@Column(name ="nombre_rol")
	private String nombre;

	@OneToMany(mappedBy = "rol"/*, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY*/)
	private Set<RolAsignado> rolesAsignados;
	
	public Rol() {
	}

	public Rol(Long idRol, String nombre, Set<RolAsignado> rolesAsignados) {
		this.idRol = idRol;
		this.nombre = nombre;
		this.rolesAsignados = rolesAsignados;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<RolAsignado> getRolesAsignados() {
		return rolesAsignados;
	}

	public void setRolesAsignados(Set<RolAsignado> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}
}
