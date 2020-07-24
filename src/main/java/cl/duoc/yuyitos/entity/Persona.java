package cl.duoc.yuyitos.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQUENCE_PERSONA_GENERATOR", sequenceName="SEQUENCE_PERSONA", initialValue=5, allocationSize=1)
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PERSONA_GENERATOR")
	@Column(name="id_persona")
	private Long id;
	private String rut;
	private String nombre;
	private String mail;
	private Long estado;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Usuario> usuario;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<RolAsignado> rolesAsignados;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Venta> venta;
	
	public Persona() {
	}

	public Persona(Long id, String rut, String nombre, String mail, Long estado, Set<Usuario> usuario,
			Set<RolAsignado> rolesAsignados, Set<Venta> venta) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.mail = mail;
		this.estado = estado;
		this.usuario = usuario;
		this.rolesAsignados = rolesAsignados;
		this.venta = venta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Set<RolAsignado> getRolesAsignados() {
		return rolesAsignados;
	}

	public void setRolesAsignados(Set<RolAsignado> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}

	public Set<Venta> getVenta() {
		return venta;
	}

	public void setVenta(Set<Venta> venta) {
		this.venta = venta;
	}

}