package cl.duoc.yuyitos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.duoc.yuyitos.entity.CodigoBarra;
import cl.duoc.yuyitos.entity.DetallePedido;
import cl.duoc.yuyitos.entity.DetalleVenta;
import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.FamiliaProducto;
import cl.duoc.yuyitos.entity.HistorialVenta;
import cl.duoc.yuyitos.entity.Marca;
import cl.duoc.yuyitos.entity.MedioPago;
import cl.duoc.yuyitos.entity.PagoDeuda;
import cl.duoc.yuyitos.entity.Pedido;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Precio;
import cl.duoc.yuyitos.entity.Producto;
import cl.duoc.yuyitos.entity.Proveedor;
import cl.duoc.yuyitos.entity.Usuario;
import cl.duoc.yuyitos.entity.Venta;
import cl.duoc.yuyitos.service.CodigoBarraService;
import cl.duoc.yuyitos.service.DetalleVentaService;
import cl.duoc.yuyitos.service.DeudaService;
import cl.duoc.yuyitos.service.HistorialVentaService;
import cl.duoc.yuyitos.service.MedioPagoService;
import cl.duoc.yuyitos.service.PagoDeudaService;
import cl.duoc.yuyitos.service.PersonaService;
import cl.duoc.yuyitos.service.ProductoService;
import cl.duoc.yuyitos.service.UsuarioService;
import cl.duoc.yuyitos.service.VentaService;

@RequestMapping ("ventas")
@Controller
public class VentasController {

	@Autowired
	private VentaService ventaService;
	@Autowired
	private DetalleVentaService detalleVentaService;
	@Autowired
	private MedioPagoService medioPagoService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private CodigoBarraService codigoBarraService;
	@Autowired
	private HistorialVentaService historialVentaService;
	@Autowired
	private DeudaService deudaService;
	@Autowired
	private PagoDeudaService pagoDeudaService;
	
	@Value("${VAR_ID_DEUDA}")
	private Integer VAR_ID_DEUDA;
	
	@Value("${VAR_ID_EFECTIVO}")
	private Integer VAR_ID_EFECTIVO;
	
	@Value("${VAR_ID_DEBITO}")
	private Integer VAR_ID_DEBITO;
	
	@Value("${VAR_ID_CREDITO}")
	private Integer VAR_ID_CREDITO;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,
						@RequestParam(name="desde", required = false) String desde,
						@RequestParam(name="hasta", required = false) String hasta) throws ParseException {
		
	
		List<Venta> listVentas = ventaService.findAll();
		
		if(desde != null || hasta != null) {
			if(desde != "" || hasta != "") {
				listVentas = ventaService.findByFechas(desde, hasta);
			}
		}
		
		model.addAttribute("listVentas", listVentas);
		
		String msgRespuesta = (String)model.asMap().get("msgRespuesta");
		model.addAttribute("msgRespuesta", msgRespuesta);

		model.addAttribute("desde", desde);
		model.addAttribute("hasta", hasta);
		
		
		return "ventas/index";
	}
	
	@RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		Venta venta = ventaService.findByIdVenta(id);
		List<DetalleVenta> listDetalleVenta = detalleVentaService.findByVenta(venta);
		
		model.addAttribute("listDetalleVenta", listDetalleVenta);
		model.addAttribute("venta", venta);
		return "ventas/detail";
	}
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model) {
		List<MedioPago> medioPago = medioPagoService.findAll();
		
		model.addAttribute("listMedioPago", medioPago);	
		
		model.addAttribute("VAR_ID_DEUDA", VAR_ID_DEUDA);
		model.addAttribute("VAR_ID_EFECTIVO", VAR_ID_EFECTIVO);
		

		return "ventas/new";
	}
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String crearVenta(@RequestParam("productos[]") List<Long> listProductos,
			@RequestParam("cantidad[]") List<Integer> listCantidades,
			@RequestParam("idMedioPago") Long idMedio,
			@RequestParam("idCliente") Long idCliente,
			@RequestParam("montoPagar") Integer montoPagar,
			@RequestParam("montoPago") Integer montoPago,
			@RequestParam("vuelto") Integer vuelto,
			RedirectAttributes redirectAttributes
			) {

		
		Venta venta = new Venta();
		List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();
		
		//Comprador
		Persona comprador = null;
		if(idCliente != 0) {
			comprador = personaService.findbyId(idCliente);
		}
		
		//Vendedor (usuario logueado)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		
		Persona vendedor = personaService.findByMail(mail);
		Usuario usuario = null;
				
		for (Usuario usu : vendedor.getUsuario()) {
        	usuario = usu;
        }//test
		
		MedioPago pago = medioPagoService.findByIdMedioPago(idMedio);
		HistorialVenta historial = new HistorialVenta();
		historialVentaService.insertarHistorial(historial);

		venta.setFechaVenta(new Date());
		venta.setPersona(comprador);
		venta.setUsuario(usuario);
		venta.setMedioPago(pago);
		
		venta.setHistorialVenta(historial);
		
		Integer valor = 0;
		Integer total = 0;
		
		for (int i = 0; i < listCantidades.size(); i++) {
			
			Producto producto = productoService.findByIdProducto(listProductos.get(i));
			
			DetalleVenta detalleVenta = new DetalleVenta();

			for(Precio precio : producto.getPrecio()) {
				valor = precio.getValor();
			}
			
			total += (valor * listCantidades.get(i));
			
			detalleVenta.setProducto(producto);
			detalleVenta.setCantidadProducto(listCantidades.get(i));
			detalleVenta.setVenta(venta);
			
			detalle.add(detalleVenta);
		}

		venta.setMontoTotal(total);
		
		if(pago.getIdPago() == (long)VAR_ID_DEBITO ||pago.getIdPago() == (long)VAR_ID_CREDITO) {
			montoPago = total;
		}
		
		venta.setMontoPago(montoPago);
	
		if(pago.getIdPago() == (long)VAR_ID_EFECTIVO) { //es efectivo
			Integer montoVuelto = 0;
			montoVuelto = (montoPago - total);
			venta.setMontoVuelto(montoVuelto);
		}
		
		ventaService.insertarVenta(venta);
		detalleVentaService.insertarDetalleVenta(detalle);
		
		if(pago.getIdPago() == (long) VAR_ID_DEUDA) { //Deuda
			System.out.println("entro 1");
			Deuda deuda = new Deuda();
			deuda.setFechaDeuda(new Date());
			deuda.setEstado('1');
			deuda.setMontoDeuda(total);
			deuda.setVenta(venta);
			comprador.setEstado(0L); // 0 = moroso
			personaService.insertarPersona(comprador); 
			
			deudaService.insertarDeuda(deuda);
			System.out.println(montoPago);
			if(montoPago != 0) {
				System.out.println("entro 2");
				PagoDeuda pagoDeuda = new PagoDeuda();
				pagoDeuda.setMontoAbono(montoPago);
				pagoDeuda.setFechaAbono(new Date());
				pagoDeuda.setDeuda(deuda);
				pagoDeuda.setMedioPago(pago);
				pagoDeudaService.insertarPagoDeuda(pagoDeuda);
			}
		}
		
		redirectAttributes.addFlashAttribute("msgRespuesta", "OK");
		return "redirect:/ventas/";
	}

}
