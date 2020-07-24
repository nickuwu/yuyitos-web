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
@SequenceGenerator(name="SEQUENCE_VENTA_GENERATOR", sequenceName="SEQUENCE_VENTA", initialValue=1, allocationSize=1)
@Table(name="venta")
public class Venta {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_VENTA_GENERATOR")
	@Column(name = "id_venta")
	private Long idVenta;
	
	@Column(name ="monto_total")
	private int montoTotal;
	
	@Column(name = "monto_pago")
	private int montoPago;
	
	@Column(name = "monto_vuelto")
	private int montoVuelto;
	
	@Column(name = "fecha_venta")
	@Temporal(TemporalType.DATE) 
	private Date fechaVenta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id_persona", referencedColumnName = "id_persona")
	private Persona persona;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_pago_id_pago", referencedColumnName = "id_pago")
	private MedioPago medioPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historial_venta_id_historial", referencedColumnName = "id_historial")
	private HistorialVenta historialVenta;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Deuda> deuda;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DetalleVenta> detalleVenta;

	public Venta() {
		
	}

	public Venta(Long idVenta, int montoTotal, int montoPago, int montoVuelto, Date fechaVenta, Persona persona,
			Usuario usuario, MedioPago medioPago, HistorialVenta historialVenta, Set<Deuda> deuda,
			Set<DetalleVenta> detalleVenta) {
		super();
		this.idVenta = idVenta;
		this.montoTotal = montoTotal;
		this.montoPago = montoPago;
		this.montoVuelto = montoVuelto;
		this.fechaVenta = fechaVenta;
		this.persona = persona;
		this.usuario = usuario;
		this.medioPago = medioPago;
		this.historialVenta = historialVenta;
		this.deuda = deuda;
		this.detalleVenta = detalleVenta;
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public int getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(int montoPago) {
		this.montoPago = montoPago;
	}

	public int getMontoVuelto() {
		return montoVuelto;
	}

	public void setMontoVuelto(int montoVuelto) {
		this.montoVuelto = montoVuelto;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public HistorialVenta getHistorialVenta() {
		return historialVenta;
	}

	public void setHistorialVenta(HistorialVenta historialVenta) {
		this.historialVenta = historialVenta;
	}

	public Set<Deuda> getDeuda() {
		return deuda;
	}

	public void setDeuda(Set<Deuda> deuda) {
		this.deuda = deuda;
	}

	public Set<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(Set<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
			
}
