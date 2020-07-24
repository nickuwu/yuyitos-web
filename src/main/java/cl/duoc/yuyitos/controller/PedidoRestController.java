package cl.duoc.yuyitos.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.TipoProducto;
import cl.duoc.yuyitos.service.ProductoService;
import cl.duoc.yuyitos.service.TipoProductoService;

@RequestMapping("request") 
@RestController
public class PedidoRestController {

	@Autowired
	private TipoProductoService tipoProductoService;
	@Autowired
	private ProductoService productoService;
	
	@RequestMapping(value = "/tipos-productos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> tiposProducto(@RequestParam("idFamilia") Long idFamilia){
		List<TipoProducto> listaTipo = tipoProductoService.findByFamilia(idFamilia);
		List<JSONObject> entities = new ArrayList<JSONObject>();
		if(listaTipo != null) {
			for(TipoProducto tipo : listaTipo) {
				JSONObject entity = new JSONObject();
				entity.put("id", tipo.getIdTipoProducto());
				entity.put("nombre", tipo.getNombre());
				entities.add(entity);
			}
		}
		return new ResponseEntity<Object>(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> tiposProducto(@RequestParam("idMarca") Long idMarca, 
												@RequestParam("idTipo") Long idTipo){

		List<Producto> listaProducto = productoService.getProductos(idMarca, idTipo);
		List<JSONObject> entities = new ArrayList<JSONObject>();
		if(listaProducto != null) {
			for(Producto producto : listaProducto) {
				JSONObject entity = new JSONObject();
				entity.put("id", producto.getIdProducto());
				entity.put("nombre", producto.getDescripcion());
				entities.add(entity);
			}
		}
		return new ResponseEntity<Object>(entities, HttpStatus.OK);
		
	}
}
