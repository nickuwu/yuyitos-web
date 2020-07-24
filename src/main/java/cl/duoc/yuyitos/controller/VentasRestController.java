package cl.duoc.yuyitos.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.yuyitos.entity.CodigoBarra;
import cl.duoc.yuyitos.entity.Precio;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.Rol;
import cl.duoc.yuyitos.entity.RolAsignado;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.service.ProductoService;
import cl.duoc.yuyitos.service.RolAsignadoService;
import cl.duoc.yuyitos.service.RolService;
import cl.duoc.yuyitos.service.CodigoBarraService;
import cl.duoc.yuyitos.service.PersonaService;
import cl.duoc.yuyitos.service.PrecioService;
import cl.duoc.yuyitos.service.ProductoService;

@RequestMapping("request")
@RestController
public class VentasRestController {

	@Autowired
	private CodigoBarraService codigoBarraService;
	@Autowired
	private PrecioService precioService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private RolService rolService;
	@Autowired
	private RolAsignadoService rolAsignadoService;
	
	@Value("${VAR_ID_ROL_CLIENTE}")
	private Integer VAR_ID_ROL_CLIENTE;

	/**
	 * busca productos por el codigo de barra
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/venta-productos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> productos(@RequestParam("codigoBarra") String codigo) {
		boolean validador = false;

		System.out.println(codigo);
		CodigoBarra codigoBarra = codigoBarraService.findbyCodigo(codigo);
		JSONObject entity = new JSONObject();
		if (codigoBarra != null) {
			Producto producto = codigoBarra.getProducto();
			
			Precio precio = precioService.findByProducto(producto);

			entity.put("id", producto.getIdProducto());
			entity.put("nombre", producto.getDescripcion());
			entity.put("precio", precio.getValor());
			validador = true;
		}
		entity.put("validador", validador);
		return new ResponseEntity<Object>(entity, HttpStatus.OK);
	}
	
	/**
	 * Busca si un cliente existe por rut
	 * @param rutCliente
	 * @return
	 */
	@RequestMapping(value = "/buscar-rut", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> Persona(@RequestParam("rutCliente") String rutCliente) {
		boolean validador = false;

		Persona persona = personaService.findByRut(rutCliente);
		JSONObject entity = new JSONObject();
		if (persona != null) {
			entity.put("id", persona.getId());
			entity.put("rut", persona.getRut());
			entity.put("nombre", persona.getNombre());
			entity.put("mail", persona.getMail());
			validador = true;
		}
		entity.put("validador", validador);
		return new ResponseEntity<Object>(entity, HttpStatus.OK);
	}
	
	/**
	 * Registra una persona
	 * @param rut
	 * @param mail
	 * @param nombre
	 * @return
	 */
	@RequestMapping(value = "/guardar-persona", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> Persona(@RequestParam("rut") String rut,
										  @RequestParam("mail") String mail,
										  @RequestParam("nombre") String nombre) {
		
		Persona validarPersona = personaService.findByRut(rut);
		boolean existe = true;
		JSONObject entity = new JSONObject();
		
		if(validarPersona == null) {

			Persona persona = new Persona();
			persona.setRut(rut);
			persona.setMail(mail);
			persona.setNombre(nombre);
			persona.setEstado(1L); //1 NO MOROSO
			personaService.insertarPersona(persona);
			
			
			Rol rol = rolService.findByIdRol((long) VAR_ID_ROL_CLIENTE); 
			
			System.out.println(rol.getNombre());
			
			RolAsignado rolAsignado = new RolAsignado();
			
			rolAsignado.setPersona(persona);
			rolAsignado.setRol(rol);
			rolAsignadoService.insertarRolAsignado(rolAsignado);
			
			existe = false;
			
			entity.put("id", persona.getId());
		}
		
		entity.put("existe", existe);
		return new ResponseEntity<Object>(entity, HttpStatus.OK);
	}
}
