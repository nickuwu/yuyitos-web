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
@SequenceGenerator(name="SEQUENCE_DEUDA_GENERATOR", sequenceName="SEQUENCE_DEUDA", initialValue=1, allocationSize=1)
@Table(name="deuda")
public class Deuda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_DEUDA_GENERATOR")
	@Column(name = "id_deuda")
	private Long idDeuda;
	
	@Column(name = "fecha_deuda")
	@Temporal(TemporalType.DATE)
	private Date fechaDeuda;
	
	@Column(name = "monto_deuda")
	private int montoDeuda;
	
	private char estado;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id_venta", referencedColumnName = "id_venta")
	private Venta venta;
	
	@OneToMany(mappedBy = "deuda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PagoDeuda> pagoDeuda;

	public Deuda() {

	}

	public Deuda(Long idDeuda, Date fechaDeuda, int montoDeuda, char estado, Venta venta, Set<PagoDeuda> pagoDeuda) {
		super();
		this.idDeuda = idDeuda;
		this.fechaDeuda = fechaDeuda;
		this.montoDeuda = montoDeuda;
		this.estado = estado;
		this.venta = venta;
		this.pagoDeuda = pagoDeuda;
	}

	public Long getIdDeuda() {
		return idDeuda;
	}

	public void setIdDeuda(Long idDeuda) {
		this.idDeuda = idDeuda;
	}

	public Date getFechaDeuda() {
		return fechaDeuda;
	}

	public void setFechaDeuda(Date fechaDeuda) {
		this.fechaDeuda = fechaDeuda;
	}

	public int getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(int montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Set<PagoDeuda> getPagoDeuda() {
		return pagoDeuda;
	}

	public void setPagoDeuda(Set<PagoDeuda> pagoDeuda) {
		this.pagoDeuda = pagoDeuda;
	}
	
} 
