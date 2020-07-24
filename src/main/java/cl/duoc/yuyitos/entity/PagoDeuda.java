package cl.duoc.yuyitos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SEQUENCE_PAGO_DEUDA_GENERATOR", sequenceName="SEQUENCE_PAGO_DEUDA", initialValue=1, allocationSize=1)
@Table(name="pago_deuda")
public class PagoDeuda {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PAGO_DEUDA_GENERATOR")
	@Column(name = "id_pago_deuda")
	private Long idPagoDeuda;
	
	@Column(name = "monto_abono")
	private int montoAbono;
	
	@Column(name = "fecha_abono")
	@Temporal(TemporalType.DATE)
	private Date fechaAbono;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deuda_id_deuda", referencedColumnName = "id_deuda")
	private Deuda deuda;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_pago_id_pago", referencedColumnName = "id_pago")
	private MedioPago medioPago;

	public PagoDeuda() {
		
	}
	
	public PagoDeuda(Long idPagoDeuda, int montoAbono, Date fechaAbono, Deuda deuda, MedioPago medioPago) {
		super();
		this.idPagoDeuda = idPagoDeuda;
		this.montoAbono = montoAbono;
		this.fechaAbono = fechaAbono;
		this.deuda = deuda;
		this.medioPago = medioPago;
	}

	public Long getIdPagoDeuda() {
		return idPagoDeuda;
	}

	public void setIdPagoDeuda(Long idPagoDeuda) {
		this.idPagoDeuda = idPagoDeuda;
	}

	public int getMontoAbono() {
		return montoAbono;
	}

	public void setMontoAbono(int montoAbono) {
		this.montoAbono = montoAbono;
	}

	public Date getFechaAbono() {
		return fechaAbono;
	}

	public void setFechaAbono(Date fechaAbono) {
		this.fechaAbono = fechaAbono;
	}

	public Deuda getDeuda() {
		return deuda;
	}

	public void setDeuda(Deuda deuda) {
		this.deuda = deuda;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}	
}
