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
@SequenceGenerator(name="SEQUENCE_HISTORIAL_VENTA_GENERATOR", sequenceName="SEQUENCE_HISTORIAL_VENTA", initialValue=1, allocationSize=1)
@Table(name="historial_venta")
public class HistorialVenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_HISTORIAL_VENTA_GENERATOR")
	@Column(name = "id_historial")
	private Long idHistorial;
		
	@OneToMany(mappedBy = "historialVenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Venta> venta;
	
	public HistorialVenta() {
		
	}
	
	public HistorialVenta(Long idHistorial, Set<Venta> venta) {
		super();
		this.idHistorial = idHistorial;
		this.venta = venta;
	}

	public Long getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(Long idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Set<Venta> getVenta() {
		return venta;
	}

	public void setVenta(Set<Venta> venta) {
		this.venta = venta;
	}
	
	
	
	

	
	
	
	

}
