package cl.duoc.yuyitos.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="codigo_barra")
public class CodigoBarra {
	
	@Id
	@Column(name="id_codigo")
	private Long idCodigo;
	
	@Column(name="codigo_barra")
	private String codigoDeBarra;
	
	@Column(name="foto_codigo")
	private Blob fotoAsoc;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "tipo_produc_id_tipo" , referencedColumnName = "id_tipo")
	private TipoProducto tipoProducto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "producto_id_produ" , referencedColumnName = "id_produ")
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "marca_id_marca" , referencedColumnName = "id_marca")
	private Marca marca;

	public CodigoBarra() {
	}

	public CodigoBarra(Long idCodigo, String codigoDeBarra, Blob fotoAsoc, TipoProducto tipoProducto, Producto producto,
			Marca marca) {
		super();
		this.idCodigo = idCodigo;
		this.codigoDeBarra = codigoDeBarra;
		this.fotoAsoc = fotoAsoc;
		this.tipoProducto = tipoProducto;
		this.producto = producto;
		this.marca = marca;
	}

	public Long getIdCodigo() {
		return idCodigo;
	}

	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}

	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}

	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	public Blob getFotoAsoc() {
		return fotoAsoc;
	}

	public void setFotoAsoc(Blob fotoAsoc) {
		this.fotoAsoc = fotoAsoc;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
