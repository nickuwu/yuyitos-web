package cl.duoc.yuyitos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.duoc.yuyitos.entity.DetalleVenta;
import cl.duoc.yuyitos.entity.Persona;
import cl.duoc.yuyitos.entity.Rol;
import cl.duoc.yuyitos.entity.RolAsignado;
import cl.duoc.yuyitos.entity.Venta;
import cl.duoc.yuyitos.entity.Deuda;
import cl.duoc.yuyitos.entity.MedioPago;
import cl.duoc.yuyitos.entity.PagoDeuda;
import cl.duoc.yuyitos.service.DeudaService;
import cl.duoc.yuyitos.service.MedioPagoService;
import cl.duoc.yuyitos.service.PagoDeudaService;
import cl.duoc.yuyitos.service.PersonaService;
import cl.duoc.yuyitos.service.RolAsignadoService;
import cl.duoc.yuyitos.service.RolService;
import cl.duoc.yuyitos.service.VentaService;

@RequestMapping ("clientes")
@Controller
public class PersonasController {
	
	@Autowired
	private RolAsignadoService rolAsignadoService;
	@Autowired
	private RolService rolService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private VentaService ventaService;
	@Autowired
	private DeudaService deudaService;
	@Autowired
	private PagoDeudaService pagoDeudaService;
	@Autowired
	private MedioPagoService medioPagoService;
	
	@Value("${VAR_ID_ROL_CLIENTE}")
	private Integer VAR_ID_ROL_CLIENTE;
	
	@Value("${VAR_ID_DEUDA}")
	private Integer VAR_ID_DEUDA;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,
						@RequestParam(name = "rut", required = false) String rut,
						@RequestParam(name = "estado", required = false) Long estado) {
		
		Rol rol = rolService.findByIdRol((long) VAR_ID_ROL_CLIENTE);
		
		List<RolAsignado> listRoles = rolAsignadoService.findAllByRol(rol);
		

		if(rut != null || estado != null) {
			if(rut != "" && estado == -1) { //solo rut
				listRoles = null;
				Persona persona = personaService.findByRut(rut);

				if (persona != null) {
					listRoles = rolAsignadoService.findAllByPersona(persona);
				}
			} else if (rut == "" && estado != -1) { //solo estado
				for(int i = 0; i< listRoles.size(); i++) {
					if(listRoles.get(i).getPersona().getEstado() != estado) {
						listRoles.remove(i);
					}
				}
			} else if (rut != "" && estado != -1) { //rut y estado
				listRoles = null;
				Persona persona = personaService.findByRut(rut);
				
				if (persona != null) {
					if(persona.getEstado() == estado) {
						listRoles = rolAsignadoService.findAllByPersona(persona);
					}
				}
			}
		}
		
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("rut", rut);
		model.addAttribute("estado", estado);
		return "cliente/index";
	}
	
	@RequestMapping(value = "/{id}/ventas", method = RequestMethod.GET)
	public String deuda(@PathVariable Long id, Model model) {
		
		Persona cliente = personaService.findbyId(id);
		List<Venta> listVentas = ventaService.findByPersona(cliente);
		

		model.addAttribute("cliente", cliente);
		model.addAttribute("listVentas", listVentas);
		model.addAttribute("VAR_ID_DEUDA", VAR_ID_DEUDA);
		return "cliente/detail";
	}

	@RequestMapping(value = "/{idCliente}/ventas/{idVenta}/deuda", method = RequestMethod.GET)
	public String deuda(@PathVariable Long idCliente,
						@PathVariable Long idVenta,
						Model model) {
		
		Venta venta = ventaService.findByIdVenta(idVenta);
		Deuda deuda = deudaService.findByVenta(venta);
		List<PagoDeuda> listPagos = pagoDeudaService.findbyDeuda(deuda);
		List<MedioPago> listMedioPago = medioPagoService.findAll();
		
		Integer montoAbonos = 0;
		for(PagoDeuda pagos : listPagos) {
			montoAbonos += pagos.getMontoAbono();
		}
		
		model.addAttribute("deuda", deuda);
		model.addAttribute("venta", venta);
		model.addAttribute("listPagos", listPagos);
		model.addAttribute("listMedioPago", listMedioPago);
		model.addAttribute("montoAbonos", montoAbonos);
		model.addAttribute("VAR_ID_DEUDA", VAR_ID_DEUDA);
		
		String msgEstado = (String)model.asMap().get("msgEstado");
		model.addAttribute("msgEstado", msgEstado);
		
		String msgRespuesta = (String)model.asMap().get("msgRespuesta");
		model.addAttribute("msgRespuesta", msgRespuesta);
		return "cliente/pagos";
	}
	
	@RequestMapping(value = "/generarAbono", method = RequestMethod.POST)
	public String abono(@RequestParam("abono") int abono,
						@RequestParam("idDeuda") long idDeuda,
						@RequestParam("idMedioPago") long idMedio,
						RedirectAttributes redirectAttributes) {
		
		Deuda deuda = deudaService.findById(idDeuda);
		MedioPago medio = medioPagoService.findByIdMedioPago(idMedio);
		
		Venta venta = deuda.getVenta();
		
		PagoDeuda pago = new PagoDeuda();
		
		pago.setFechaAbono(new Date());
		pago.setMontoAbono(abono);
		pago.setDeuda(deuda);
		pago.setMedioPago(medio);
		
		pagoDeudaService.insertarPagoDeuda(pago);
		
		Integer montoPago = venta.getMontoPago() + abono;
		
		venta.setMontoPago(montoPago);
		ventaService.insertarVenta(venta);
		
		if(venta.getMontoTotal() == venta.getMontoPago()) {
			deuda.setEstado('0');
			deudaService.insertarDeuda(deuda);
		}
		
		Persona cliente = venta.getPersona();
		List<Venta> ventas = ventaService.findByPersona(cliente);
		boolean validador = false;
		
		for(Venta listaVenta : ventas) {
			Deuda deudas = deudaService.findByVenta(listaVenta);
			if(deudas != null) {
				if(deudas.getEstado() == '1') { // tiene una deuda vigente
					validador = true;
				}
			}
		}
		
		if(validador == true) { // no pagado
			cliente.setEstado(0L); // 0 moroso - 1 no moroso
			personaService.insertarPersona(cliente);
		} else {
			cliente.setEstado(1L);
			personaService.insertarPersona(cliente);
		}
		
		redirectAttributes.addFlashAttribute("msgEstado", "OK");
		redirectAttributes.addFlashAttribute("msgRespuesta", "Abono de " +abono+  " registrado correctamente");
		
		Integer saldoPagar = venta.getMontoPago() + abono;

		return "redirect:/clientes/" +venta.getPersona().getId()+ "/ventas/" +deuda.getVenta().getIdVenta()+ "/deuda";
		
	}
}
