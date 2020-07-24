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

@Entity
@SequenceGenerator(name="SEQUENCE_DETALLE_PEDIDO_GENERATOR", sequenceName="SEQUENCE_DETALLE_PEDIDO", initialValue=8, allocationSize=1)
@Table (name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_DETALLE_PEDIDO_GENERATOR")
	@Column (name = "id_detall_pedido")
	private Long idDetalle;
	@Column (name = "cantidad_producto")
	private int cantidadProducto;
	@Column (name = "fecha_vencimiento")
	private Date fechaVencimiento = null;
	@Column (name = "precio_uni")
	private int precioUnitario;
	private Long iva;
	private Integer estado = null;
	@Column (name = "motivo_estado")
	private String motivoEstado;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "producto_id_produ" , referencedColumnName = "id_produ")
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "pedido_id_pedido" , referencedColumnName = "id_pedido")
	private Pedido pedido;

	public DetallePedido() {
		
	}

	public DetallePedido(Long idDetalle, int cantidadProducto, Date fechaVencimiento, int precioUnitario, Long iva,
			Integer estado, String motivoEstado, Producto producto, Pedido pedido) {
		super();
		this.idDetalle = idDetalle;
		this.cantidadProducto = cantidadProducto;
		this.fechaVencimiento = fechaVencimiento;
		this.precioUnitario = precioUnitario;
		this.iva = iva;
		this.estado = estado;
		this.motivoEstado = motivoEstado;
		this.producto = producto;
		this.pedido = pedido;
	}

	public Long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Long getIva() {
		return iva;
	}

	public void setIva(Long iva) {
		this.iva = iva;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getMotivoEstado() {
		return motivoEstado;
	}

	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
