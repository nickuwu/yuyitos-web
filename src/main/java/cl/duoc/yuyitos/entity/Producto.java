package cl.duoc.yuyitos.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="producto")
public class Producto {
	
	@Id
	@Column(name="id_produ")
	private Long idProducto;
	private String descripcion;
	private int stock;
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DetallePedido> detallePedido;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DetalleVenta> detalleVenta;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Precio> precio;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CodigoBarra> codigoBarra;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "tipo_produc_id_tipo" , referencedColumnName = "id_tipo")
	private TipoProducto tipoProducto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "marca_id_marca" , referencedColumnName = "id_marca")
	private Marca marca;
	

	public Producto() {
		 
	}


	public Producto(Long idProducto, String descripcion, int stock, Date fechaVencimiento,
			Set<DetallePedido> detallePedido, Set<DetalleVenta> detalleVenta, Set<Precio> precio,
			Set<CodigoBarra> codigoBarra, TipoProducto tipoProducto, Marca marca) {
		super();
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.stock = stock;
		this.fechaVencimiento = fechaVencimiento;
		this.detallePedido = detallePedido;
		this.detalleVenta = detalleVenta;
		this.precio = precio;
		this.codigoBarra = codigoBarra;
		this.tipoProducto = tipoProducto;
		this.marca = marca;
	}


	public Long getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public Set<DetallePedido> getDetallePedido() {
		return detallePedido;
	}


	public void setDetallePedido(Set<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}


	public Set<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}


	public void setDetalleVenta(Set<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}


	public Set<Precio> getPrecio() {
		return precio;
	}


	public void setPrecio(Set<Precio> precio) {
		this.precio = precio;
	}


	public Set<CodigoBarra> getCodigoBarra() {
		return codigoBarra;
	}


	public void setCodigoBarra(Set<CodigoBarra> codigoBarra) {
		this.codigoBarra = codigoBarra;
	}


	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}


	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}


	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
