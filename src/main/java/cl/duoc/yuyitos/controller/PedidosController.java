package cl.duoc.yuyitos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.duoc.yuyitos.entity.DetallePedido;
import cl.duoc.yuyitos.entity.FamiliaProducto;
import cl.duoc.yuyitos.entity.Marca;
import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.Proveedor;
import cl.duoc.yuyitos.entity.Usuario;
import cl.duoc.yuyitos.service.DetallePedidoService;
import cl.duoc.yuyitos.service.FamiliaService;
import cl.duoc.yuyitos.service.MarcaService;
import cl.duoc.yuyitos.service.PedidosService;
import cl.duoc.yuyitos.service.PersonaService;
import cl.duoc.yuyitos.service.ProductoService;
import cl.duoc.yuyitos.service.ProveedorService;
import cl.duoc.yuyitos.service.UsuarioService;

@RequestMapping("pedidos")
@Controller
public class PedidosController {

	@Autowired
	private PedidosService pedidosService;
	@Autowired
	private DetallePedidoService detallePedidoService;
	@Autowired
	private MarcaService marcaService;
	@Autowired
	private FamiliaService familiaService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProductoService productoService; 
	@Autowired
	private PersonaService personaService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<Pedido> listPedidos = pedidosService.findAll();
		
		String msgEstado = (String)model.asMap().get("msgEstado");
		model.addAttribute("msgEstado", msgEstado);
		
		String msgRespuesta = (String)model.asMap().get("msgRespuesta");
		model.addAttribute("msgRespuesta", msgRespuesta);
		
		model.addAttribute("listPedidos", listPedidos);
		return "pedidos/index";
	}

	@RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		Pedido pedido = pedidosService.findByIdPedido(id);
		List<DetallePedido> listDetallePedido = detallePedidoService.findByPedido(pedido);
		boolean revisado = true;
		for (DetallePedido detalle : listDetallePedido) {
			if (detalle.getEstado() == null) {
				revisado = false;
			}
		}
		model.addAttribute("listDetallePedido", listDetallePedido);
		model.addAttribute("pedido", pedido);
		model.addAttribute("revisado", revisado);
		
		String msgEstado = (String)model.asMap().get("msgEstado");
		model.addAttribute("msgEstado", msgEstado);
		
		String msgRespuesta = (String)model.asMap().get("msgRespuesta");
		model.addAttribute("msgRespuesta", msgRespuesta);
		
		return "pedidos/detail";
	}

	@RequestMapping(value = "/recibido/{id}", method = RequestMethod.GET)
	public String pedidoRecibido(@PathVariable Long id,
								RedirectAttributes redirectAttributes) {
		Pedido pedido = pedidosService.findByIdPedido(id);
		pedidosService.updatePedidoRecibido(pedido);
		
		redirectAttributes.addFlashAttribute("msgEstado", "OKRECIBIDO");
		redirectAttributes.addFlashAttribute("msgRespuesta", "Pedido " +id+ " recibido correctamente ");

		return "redirect:/pedidos/";
	}

	@RequestMapping(value = "/revisado/{id}/{estado}", method = RequestMethod.GET)
	public String pedidoRevisado(@PathVariable Long id, 
								@PathVariable Integer estado,
								RedirectAttributes redirectAttributes) {
		Pedido pedido = pedidosService.findByIdPedido(id);
		pedidosService.updatePedidoRevisado(pedido, estado);
		
		String respuesta = "Rechazado";
		if(estado == 1) {
			respuesta = "Aceptado";
		}
		
		redirectAttributes.addFlashAttribute("msgEstado", "OKREVISADO");
		redirectAttributes.addFlashAttribute("msgRespuesta", "Pedido " +id+ " " +respuesta+ " con éxito");
		
		return "redirect:/pedidos/";
	}

	@RequestMapping(value = "/detalle/revisado/{id}/{estado}", method = RequestMethod.GET)
	public String updateDatellePedidoEstado(@PathVariable Long id, 
											@PathVariable Integer estado,
											@RequestParam("fechaVencimiento") String fechaVencimiento,
											RedirectAttributes redirectAttributes) throws ParseException {
		
		DetallePedido detalle = detallePedidoService.findByIdDetalle(id);
		
		
		if(fechaVencimiento != "") {
			
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaVencimiento);
			
			detalle.setFechaVencimiento(fecha);
			
		}
		
		detallePedidoService.updateDatellePedidoEstado(detalle, "", estado);
		
		String estadoProducto = "Rechazado";
		
		if(estado == 1) {
			estadoProducto = "Aceptado";
		}
		
		redirectAttributes.addFlashAttribute("msgEstado", "OK");
		redirectAttributes.addFlashAttribute("msgRespuesta", "Producto " +id+ " " +estadoProducto+ " correctamente");
		
		return "redirect:/pedidos/detalle/" + detalle.getPedido().getIdPedido();
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model) {
		List<Marca> listMarcas = marcaService.findAll();
		List<FamiliaProducto> listFamilia = familiaService.findAll();
		List<Proveedor> listProveedor = proveedorService.findAll();

		model.addAttribute("listMarcas", listMarcas);
		model.addAttribute("listFamilia", listFamilia);
		model.addAttribute("listProveedor", listProveedor);

		return "pedidos/new";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String crearPedido(@RequestParam("idProveedor") Long idProveedor,
			@RequestParam("cantidad[]") List<Integer> listCantidades,
			@RequestParam("precioUnitario[]") List<Integer> listPrecios,
			@RequestParam("idProducto[]") List<Long> listProductos,
			@RequestParam("iva") Long iva,
			RedirectAttributes redirectAttributes) {

		Pedido pedido = new Pedido();
		List<DetallePedido> detalle = new ArrayList<DetallePedido>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		
		Persona persona = personaService.findByMail(mail);
		Usuario usuario = null;
				
		for (Usuario usu : persona.getUsuario()) {
        	usuario = usu;
        }
		//Fin usuario autenticado
		
		Proveedor proveedor = proveedorService.findByIdidProveedor(idProveedor);
		pedido.setProveedor(proveedor);
		pedido.setFechaSolicitud(new Date());
		pedido.setUsuario(usuario);
		pedidosService.insertarPedido(pedido);
		
		for (int i = 0; i < listCantidades.size(); i++) {

			Producto producto = productoService.findByIdProducto(listProductos.get(i));
			
			DetallePedido detallePedido = new DetallePedido();
			
			detallePedido.setPedido(pedido);
			detallePedido.setProducto(producto);
			detallePedido.setCantidadProducto(listCantidades.get(i));
			detallePedido.setPrecioUnitario(listPrecios.get(i));
			detallePedido.setIva(iva);
			
			detalle.add(detallePedido);
		}
		
		detallePedidoService.insertarDetallePedido(detalle);
		
		Long idPedido = pedido.getIdPedido();
		redirectAttributes.addFlashAttribute("msgEstado", "OKCREADO");
		redirectAttributes.addFlashAttribute("msgRespuesta", "Pedido " +idPedido+ " creado correctamente");

		return "redirect:/pedidos/";
	}

}
