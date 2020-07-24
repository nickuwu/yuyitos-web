package cl.duoc.yuyitos.entity;

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

@Entity
@SequenceGenerator(name="SEQUENCE_DETALLE_VENTA_GENERATOR", sequenceName="SEQUENCE_DETALLE_VENTA", initialValue=1, allocationSize=1)
@Table(name="detalle_venta")
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_DETALLE_VENTA_GENERATOR")
	@Column(name="id_detall_venta")
	private Long idDetalleVenta;
	
	@Column(name="cantidad_producto")
	private int cantidadProducto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "producto_id_produ" , referencedColumnName = "id_produ")
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "venta_id_venta" , referencedColumnName = "id_venta")
	private Venta venta;
	
	public DetalleVenta() {
		
	}
		
	public DetalleVenta(Long idDetalleVenta, int cantidadProducto, Producto producto, Venta venta) {
		super();
		this.idDetalleVenta = idDetalleVenta;
		this.cantidadProducto = cantidadProducto;
		this.producto = producto;
		this.venta = venta;
	}
	
	public Long getIdDetalleVenta() {
		return idDetalleVenta;
	}
	public void setIdDetalleVenta(Long idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}
	public int getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}
