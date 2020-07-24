package cl.duoc.yuyitos.entity;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name="id_usuario")
	private Long idUsuario; 
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id_persona"/*, referencedColumnName = "id"*/)
    private Persona persona;
	
	@OneToMany(mappedBy = "usuario"/*, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY*/)
	private Set<Pedido> pedidos;
	
	@OneToMany(mappedBy = "usuario"/*, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY*/)
	private Set<Venta> ventas;

	public Usuario() {
	}

	public Usuario(Long idUsuario, String password, Persona persona, Long estado, Set<Pedido> pedidos,
			Set<Venta> ventas) {
		super();
		this.idUsuario = idUsuario;
		this.password = password;
		this.persona = persona;
		this.pedidos = pedidos;
		this.ventas = ventas;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}
	
}
