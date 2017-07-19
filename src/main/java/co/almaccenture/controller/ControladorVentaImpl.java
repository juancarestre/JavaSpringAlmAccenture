package co.almaccenture.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.almaccenture.business.LogicaNegocioVenta;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;

@Controller
public class ControladorVentaImpl implements ControladorVenta {
	
	
	private Venta venta;
	private List<DetalleVenta> productos;
	private LogicaNegocioVenta ventaBl;
	
	

	@RequestMapping(value = "/ventas", method=RequestMethod.GET)
	@Override
	public ModelAndView iniciaVenta() {
		venta = new Venta();
		productos = new ArrayList<>();
		venta.setCaja(ventaBl.generarCaja());
		
		ModelAndView mav = new ModelAndView("/venta");
		mav.addObject(venta);
		mav.addObject(productos);
		
		return mav;
	}

	@RequestMapping(value = "/ventas", method=RequestMethod.GET, params={"id","cant"})
	@Override
	public ModelAndView ingresarProducto(@QueryParam("id") String idProducto,
			@QueryParam("cant") int cantidad) {
		
		DetalleVenta producto = ventaBl.agregarDetalleVenta(idProducto, cantidad);
		
		// Agrega producto a a lista de productos de venta
		productos.add(producto);
		
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/ventas/del", method=RequestMethod.GET, params={"id"})
	@Override
	public ModelAndView eliminarProducto(HttpServletRequest req, RedirectAttributes redirect) {
		String idProducto = req.getParameter("id");
		for (DetalleVenta detalleVenta : productos) {
			if(detalleVenta.getProducto().getIdProducto().equals(idProducto))
				productos.remove(detalleVenta);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/ventas", method=RequestMethod.POST, params="save")
	@Override
	public ModelAndView confirmarVenta() {
		//TODO: Popup de confirmación
		
		venta.setDetalles(productos);
		ventaBl.guardarVenta(venta);
		return null;
	}

}
