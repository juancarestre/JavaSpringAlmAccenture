package co.almaccenture.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ControladorVentaImpl{
	
	
	
	private List<DetalleVenta> detalles = new ArrayList<>();
	@Autowired
	private LogicaNegocioVenta ventaBl;
	
	private Venta venta;
	public static final String MENSAJE_ID_PRODUCTO_INVALIDO = "El código de producto debe contener algún valor.";
	public static final String MENSAJE_CANTIDAD_INVALIDO = "La cantidad debe contener algún valor.";
	

	/**
	 * Genera una nueva venta, iniciando todos los form-backing beans.
	 * url: /ventas/new ---- > retorna /ventas
	 */
	@RequestMapping(value="/ventas/new")
	public ModelAndView nuevaVenta(){
		try {
			venta = ventaBl.nuevaVenta();
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
		}
		venta.setDetalles(new ArrayList<>());
		
		ModelAndView mav = new ModelAndView("/venta");
		mav.addObject("detalle", new DetalleVenta()); // agregar detalle venta
		mav.addObject("venta", venta); // carga nformacion inicial de venta
		
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
		mav.addObject("venta", venta); // carga nformacion inicial de venta (contiene la lista de detalles)
		return mav;
	}

	
	@RequestMapping(value="/ventas", params={"producto.idProducto","cantidad"})
	public ModelAndView ingresarProducto(HttpServletRequest req, RedirectAttributes redirect){
		
		
		String message="";
		DetalleVenta producto = null;
		
		try{
			if(req.getParameter("producto.idProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_ID_PRODUCTO_INVALIDO);
			if(req.getParameter("cantidad").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_ID_PRODUCTO_INVALIDO);
			String idp = req.getParameter("producto.idProducto");
			int cant= Integer.parseInt(req.getParameter("cantidad"));
			
			
			producto = ventaBl.agregarDetalleVenta(idp,cant);
			venta.getDetalles().add(producto);
			for (DetalleVenta detalleVenta : venta.getDetalles()) {
				System.out.println("Detalle guardado en lista "+ detalleVenta.getProducto().getNombreProducto());
			}
			venta.setTotalVenta(sumarTotal());
		}catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			message=e.getMessage();
			//TODO: Definir excepción
		}
		
		// Agrega producto a a lista de productos de venta
		ModelAndView mav= new ModelAndView("redirect:/ventas");
		redirect.addFlashAttribute("message", message);
		
		return mav;
	}

	@GetMapping("ventas/{idProducto}")
	public ModelAndView eliminarProducto(@PathVariable("idProducto") String idProducto) {
		ModelAndView mav = new ModelAndView("/venta");
		
		for(int i=0; i<detalles.size(); i++){
			DetalleVenta dp = detalles.get(i);
			if(dp.getProducto().getIdProducto().equals(idProducto))
				detalles.remove(dp);
		}
		
		mav.addObject("venta", venta);
		mav.addObject("detalles", detalles);
		mav.addObject("detalle", new DetalleVenta());
		
		return new ModelAndView("redirect:/ventas");
	}
	
	@RequestMapping(value = "/ventas", method=RequestMethod.GET, params="guardar")
	public String confirmarVenta() throws LogicaNegocioExcepcion {
		//TODO: Popup de confirmación
		venta = ventaBl.nuevaVenta(); //temporal
		venta.setDetalles(detalles);
		venta.setTotalVenta(sumarTotal());
		ventaBl.guardarVenta(venta);
		return "Producto Guardado";
	}
	
	public float sumarTotal() {
		float suma=0;
		
		
		
		for(int i=0; i<detalles.size(); i++){
			DetalleVenta dp = detalles.get(i);
			suma += (dp.getCantidad()*dp.getValorUnitario());			
		}
		
		return suma;
		
	}
	
	

}
