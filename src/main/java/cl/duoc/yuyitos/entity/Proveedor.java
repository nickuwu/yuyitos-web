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
@Table (name="proveedor")
public class Proveedor {
	
	@Id
	@Column(name="id_prov")
	private Long idProveedor; 
	private String nombre;
	@Column(name="razon_social")
	private String razonSocial;
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;
	
	public Proveedor() {
	}

	public Proveedor(Long idProveedor, String nombre, String razonSocial) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
