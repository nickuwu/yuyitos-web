package cl.duoc.yuyitos.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@SequenceGenerator(name="SEQUENCE_PEDIDO_GENERATOR", sequenceName="SEQUENCE_PEDIDO", initialValue=5, allocationSize=1)
@Table (name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PEDIDO_GENERATOR")
	@Column(name = "id_pedido")
	private Long idPedido;
	@Column(name = "fecha_solicitud")
	@Temporal(TemporalType.DATE)
	private Date fechaSolicitud;
	
	@Column(name = "fecha_recibido")
	@Temporal(TemporalType.DATE)
	private Date fechaRecibido;
	@Column(name = "fecha_revisado")
	@Temporal(TemporalType.DATE)
	private Date fechaRevisado;
	private Integer estado = null;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DetallePedido> detallePedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
	
	@ManyToOne( fetch = FetchType.LAZY) 
    @JoinColumn(name = "proveedor_id_prov" , referencedColumnName = "id_prov")
    private Proveedor proveedor;
	
	public Pedido() {
	}

	public Pedido(Long idPedido, Date fechaSolicitud, Date fechaRecibido, Date fechaRevisado, Integer estado,
			Usuario usuario, Proveedor proveedor) {
		this.idPedido = idPedido;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaRecibido = fechaRecibido;
		this.fechaRevisado = fechaRevisado;
		this.estado = estado;
		this.usuario = usuario;
		this.proveedor = proveedor;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public Date getFechaRevisado() {
		return fechaRevisado;
	}

	public void setFechaRevisado(Date fechaRevisado) {
		this.fechaRevisado = fechaRevisado;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}		
}
