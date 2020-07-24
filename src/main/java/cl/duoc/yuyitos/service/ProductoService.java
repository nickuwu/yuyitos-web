package cl.duoc.yuyitos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.yuyitos.entity.Marca;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.TipoProducto;
import cl.duoc.yuyitos.repository.MarcaRepository;
import cl.duoc.yuyitos.repository.ProductoRepository;
import cl.duoc.yuyitos.repository.TipoProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	
	public Producto findByIdProducto(Long id) {
		
		return productoRepository.getOne(id);
	} 

	public List<Producto> getProductos(Long idMarca, Long idTipo) {
		List<Producto> listaProductos = null;
		
		if(idMarca != null && idTipo == null) {
			Marca marca = marcaRepository.getOne(idMarca);
			listaProductos = productoRepository.findByMarca(marca);
		} else if(idMarca == null && idTipo != null) {
			TipoProducto tipoProducto = tipoProductoRepository.getOne(idTipo);
			listaProductos = productoRepository.findByTipoProducto(tipoProducto);
		} else if(idMarca != null && idTipo != null){
			Marca marca = marcaRepository.getOne(idMarca);
			TipoProducto tipoProducto = tipoProductoRepository.getOne(idTipo);
			listaProductos = productoRepository.findByMarcaAndTipoProducto(tipoProducto, marca);
		} 
		
		return listaProductos;
	}
	
	public void guardarProducto(Producto producto) {
		productoRepository.saveAndFlush(producto);
	}
}
