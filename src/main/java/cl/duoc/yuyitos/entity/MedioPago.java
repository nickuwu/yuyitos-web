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
@Table(name="medio_pago")
public class MedioPago {
	
	@Id
	@Column(name = "id_pago")
	private Long idPago;
	
	private String descripcion;
	
	@OneToMany(mappedBy = "medioPago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PagoDeuda> pagoDeuda;
	
	@OneToMany(mappedBy = "medioPago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Venta> venta;

	public MedioPago() {
		
	}
	
	public MedioPago(Long idPago, String descripcion, Set<PagoDeuda> pagoDeuda, Set<Venta> venta) {
		super();
		this.idPago = idPago;
		this.descripcion = descripcion;
		this.pagoDeuda = pagoDeuda;
		this.venta = venta;
	}

	public Long getIdPago() {
		return idPago;	
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<PagoDeuda> getPagoDeuda() {
		return pagoDeuda;
	}

	public void setPagoDeuda(Set<PagoDeuda> pagoDeuda) {
		this.pagoDeuda = pagoDeuda;
	}

	public Set<Venta> getVenta() {
		return venta;
	}

	public void setVenta(Set<Venta> venta) {
		this.venta = venta;
	}

}
