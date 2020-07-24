package cl.duoc.yuyitos.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="precio")
public class Precio {

	@Id
	@Column(name="id_precio")
	private int idPrecio;
	@Column(name="precio")
	private int valor;
	@Column (name = "fecha_inicio")
	private Date fechaInicio;
	@Column (name = "fecha_termino")
	private Date fechaTermino;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "producto_id_produ" , referencedColumnName = "id_produ")
	private Producto producto;
	
	public Precio() {
		
	}

	public Precio(int idPrecio, int valor, Date fechaInicio, Date fechaTermino, Producto producto) {
		super();
		this.idPrecio = idPrecio;
		this.valor = valor;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.producto = producto;
	}

	public int getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(int idPrecio) {
		this.idPrecio = idPrecio;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
