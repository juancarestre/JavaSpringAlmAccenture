package co.almaccenture.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.almaccenture.business.LogicaNegocioCaja;
import co.almaccenture.business.LogicaNegocioVenta;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;

@Controller
public class ControladorVenta{
	
	
	@Autowired
	private LogicaNegocioVenta ventaBl;
	
	private Venta venta;
	public static final String MENSAJE_ID_PRODUCTO_INVALIDO = "El código de producto debe contener algún valor.---";
	public static final String MENSAJE_CANTIDAD_INVALIDO = "La cantidad debe contener algún valor.";
	private static final String MENSAJE_DETALLE_ACTUALIZADO = "Se ha actualizado producto";
	private static final String FRAGMENTO_CALCULA_CAMBIO = "fragmento-calcula-cambio :: calculaCambio";
	

	/**
	 * Genera una nueva venta, iniciando todos los form-backing beans.
	 * url: /ventas/new ---- > retorna /ventas
	 */
	@RequestMapping(value="/ventas/new")
	public ModelAndView nuevaVenta(){
		float totalInicial=0;
		try {
			venta = ventaBl.nuevaVenta();
			venta.setTotalVenta(totalInicial);
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
		}
		venta.setDetalles(new ArrayList<>());
		
		ModelAndView mav = new ModelAndView("/venta");
		mav.addObject("detalle", new DetalleVenta()); // agregar detalle venta
		mav.addObject("venta", venta); // carga nformacion inicial de venta
		mav.addObject("message", "");
		return mav;
		
	}
	
	/**
	 * Mapeo a la misma pagina donde se refrescan los formbacking beans,
	 * mas que todo el nuevo DetalleVenta para ser rellenado.
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	@RequestMapping(value = "/ventas")
	public ModelAndView nuevoDetalleVenta() throws LogicaNegocioExcepcion {
		
		ModelAndView mav = new ModelAndView("/venta");
		mav.addObject("detalle", new DetalleVenta()); // agregar detalle venta
		mav.addObject("venta", venta);
		//mav.addObject("message", "");// carga nformacion inicial de venta (contiene la lista de detalles)
		return mav;
	}

	/**
	 * Agrega un nuevo detalle a la venta. Hace la busqueda de la disponibilidad del producto en bl y
	 * recibe de bl el detalleVenta validado. Hace validación con posible duplicados en lista DEtalleVenta
	 * de Venta, en caso tal, hace merge de cantidad y actualiza detalle antiguo.
	 * @param req
	 * @param redirect
	 * @return
	 * @throws RemoteException Excepcion remota
	 */
	@RequestMapping(value="/ventas", params={"producto.idProducto","cantidad"})
	public ModelAndView ingresarProducto(HttpServletRequest req, RedirectAttributes redirect) throws RemoteException{
		
		
		
		String message="";
		DetalleVenta producto = null;
		
		try{
			
			if(req.getParameter("producto.idProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_ID_PRODUCTO_INVALIDO);
			if(req.getParameter("cantidad").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_CANTIDAD_INVALIDO);
			
			String idp = req.getParameter("producto.idProducto");
			int cant= Integer.parseInt(req.getParameter("cantidad"));
			
			producto = ventaBl.agregarDetalleVenta(idp,cant);
			
			if(mergeDetalles(producto, venta.getDetalles())){
				message = MENSAJE_DETALLE_ACTUALIZADO + " " + producto.getProducto().getNombreProducto();
			}else{
				venta.getDetalles().add(producto);
			}
			venta.setTotalVenta(sumarTotal());
		}catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		
		// Agrega producto a a lista de productos de venta
		ModelAndView mav= new ModelAndView("redirect:/ventas");
		redirect.addFlashAttribute("message", message);
		
		return mav;
	}
	
	/**
	 * Hace merge en detalles si detalle ya existe.
	 * @param detalle detalle que quiere agregarse a detalles
	 * @param detalles lista de detalle
	 * @return true si se hizo merge, false de lo contrario
	 */
	private Boolean mergeDetalles(DetalleVenta detalle, List<DetalleVenta> detalles) {
		
		for (DetalleVenta detalleVenta : detalles) {
			if(detalleVenta.getProducto().getIdProducto().equals(detalle.getProducto().getIdProducto())){
				detalleVenta.setCantidad(detalleVenta.getCantidad()+detalle.getCantidad());
				return true;
			}
		}
		return false;
	}

	@GetMapping("ventas/{idProducto}")
	public ModelAndView eliminarProducto(@PathVariable("idProducto") String idProducto) {
		ModelAndView mav = new ModelAndView("/venta");
		List<DetalleVenta> detalles = venta.getDetalles();
		for(int i=0; i<detalles.size(); i++){
			DetalleVenta dp = detalles.get(i);
			if(dp.getProducto().getIdProducto().equals(idProducto))
				detalles.remove(dp);
//				venta.setTotalVenta(venta.getTotalVenta()-dp.getValorUnitario()*dp.getCantidad());
				venta.setTotalVenta(sumarTotal());
		}
		
		mav.addObject("venta", venta);
		mav.addObject("detalles", detalles);
		mav.addObject("detalle", new DetalleVenta());
		
		return new ModelAndView("redirect:/ventas");
	}
	
	/**
	 * Inyecta objecto venta a fragmento para mostrar en el bootstrap dialog
	 * @param model modelo que se le inyecta a fragmento
	 * @return fragmento para ser usado por el bootstrap dialog
	 */
	@RequestMapping(value="/ventas/pago",method= RequestMethod.GET)
	public String preparaModalPago(final Model model){
		venta.setTotalVenta(sumarTotal());
		model.addAttribute(venta);
		return FRAGMENTO_CALCULA_CAMBIO;
	}
	
	/**
	 * Confirma la venta y la envia a la base de datos
	 * @return inicia una nueva venta
	 * @throws LogicaNegocioExcepcion
	 */
	@RequestMapping(value = "/ventas/guardar", method=RequestMethod.GET)
	public String confirmarVenta() throws LogicaNegocioExcepcion {
		//TODO: Popup de confirmación
		System.out.println("Entro a confirmar venta");
		venta.setTotalVenta(sumarTotal());
		ventaBl.guardarVenta(venta);
		return "redirect:/ventas/new";
	}
	
	@RequestMapping(value = "/ventas", method=RequestMethod.GET, params="limpiar")
	public ModelAndView limpiarVenta() throws LogicaNegocioExcepcion {
		return new ModelAndView("redirect:/ventas/new");

	}
	
	public float sumarTotal() {
		float suma=0;
		
		
		List<DetalleVenta> detalles = venta.getDetalles();
		for(int i=0; i<detalles.size(); i++){
			DetalleVenta dp = detalles.get(i);
			suma += (dp.getCantidad()*dp.getValorUnitario());			
		}
		
		return suma;
		
	}
	
	

}
