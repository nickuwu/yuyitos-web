package cl.duoc.yuyitos.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="tipo_produc")
public class TipoProducto implements Serializable{

	private static final long serialVersionUID = 1020235574700660500L;
	@Id
	@Column (name="id_tipo")
	private Long idTipoProducto;
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy = "tipoProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Producto> producto;
	
	@OneToMany(mappedBy = "tipoProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CodigoBarra> codigoBarra;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "familia_produc_id_familia" , referencedColumnName = "id_familia")
	@JsonIgnore
	private FamiliaProducto familia;
	
	public TipoProducto() {
	}

	public TipoProducto(Long idTipoProducto, String nombre, String descripcion, Set<Producto> producto,
			Set<CodigoBarra> codigoBarra, FamiliaProducto familia) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.producto = producto;
		this.codigoBarra = codigoBarra;
		this.familia = familia;
	}

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
	}

	public Set<CodigoBarra> getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(Set<CodigoBarra> codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public FamiliaProducto getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaProducto familia) {
		this.familia = familia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
