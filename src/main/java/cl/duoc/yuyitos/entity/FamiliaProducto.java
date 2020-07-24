package cl.duoc.yuyitos.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="familia_produc")
public class FamiliaProducto {

	@Id
	@Column(name="id_familia")
	private Long idFamilia;
	private String descripcion;
	
	@OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TipoProducto> tipoProducto;
	
	public FamiliaProducto() {
	}

	public FamiliaProducto(Long idFamilia, String descripcion, Set<TipoProducto> tipoProducto) {
		this.idFamilia = idFamilia;
		this.descripcion = descripcion;
		this.tipoProducto = tipoProducto;
	}

	public Long getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TipoProducto> getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(Set<TipoProducto> tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
}
