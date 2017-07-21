package co.almaccenture.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	private Venta venta;
	private List<DetalleVenta> detalles;
	@Autowired
	private LogicaNegocioVenta ventaBl;
	@Autowired
	private LogicaNegocioCaja caja;
	

	@RequestMapping(value = "/ventas", method=RequestMethod.GET)
	public ModelAndView iniciaVenta() throws LogicaNegocioExcepcion {
		venta = ventaBl.nuevaVenta();
		
		detalles = new ArrayList<>();
		
		ModelAndView mav = new ModelAndView("/ventas");
		mav.setViewName("venta");
		mav.addObject("detalle",new DetalleVenta()); // agregar detalle venta
		mav.addObject("venta", venta); // carga nformacion inicial de venta
		mav.addObject("productos", detalles); // muestra los productos
		
		return mav;
	}

	@RequestMapping(value = "/ventas", method=RequestMethod.POST, params={"search"})
	public ModelAndView ingresarProducto(DetalleVenta detalle) throws LogicaNegocioExcepcion {
		System.out.println("DetalleVenta obtenido con idprod "
				+ detalle.getProducto().getIdProducto() + " y cantidad "+ detalle.getCantidad());
		DetalleVenta producto = ventaBl.agregarDetalleVenta(detalle.getProducto().getIdProducto(),detalle.getCantidad());
		
		// Agrega producto a a lista de productos de venta
		detalles.add(producto);
		for (DetalleVenta detalleVenta : detalles) {
			System.out.println("Detalle guardado en lista "+ detalleVenta.getProducto().getNombreProducto());
		}
		venta.setTotalVenta(sumarTotal());
		ModelAndView mav = new ModelAndView("/venta");
		mav.addObject("venta", venta);
		mav.addObject("detalles", detalles);
		mav.addObject("detalle", new DetalleVenta());
		
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
	
	@RequestMapping(value = "/ventas", method=RequestMethod.POST, params="save")
	public ModelAndView confirmarVenta() throws LogicaNegocioExcepcion {
		//TODO: Popup de confirmación
		
		venta.setDetalles(detalles);
		ventaBl.guardarVenta(venta);
		return null;
	}
	
	public float sumarTotal() {
		float suma=0;
		
		
		
		for(int i=0; i<detalles.size(); i++){
			DetalleVenta dp = detalles.get(i);
			suma += (dp.getCantidad()*dp.getValorUnitario());			
		}
		
		return suma;
		
	}
	
	@GetMapping("departamentos/a/{nombre}")
	public ModelAndView getDepartamentosByName(@PathVariable("nombre") String departamentoname) {
		

		return null;
	}
	
	

}
