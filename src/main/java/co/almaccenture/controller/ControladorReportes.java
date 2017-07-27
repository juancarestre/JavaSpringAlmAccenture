package co.almaccenture.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import co.almaccenture.business.LogicaNegocioProducto;

import co.almaccenture.business.LogicaNegocioVenta;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;


import co.almaccenture.model.Producto;

@Controller
public class ControladorReportes {

	@Autowired
	private LogicaNegocioProducto producto;
	@Autowired
	private LogicaNegocioVenta ventaBL;
		
	private Venta venta;


	// @RequestMapping(method = RequestMethod.GET, value = "/reportes")
	// public ModelAndView listar(Pageable pageable) { //metodo
	// ModelAndView mav = new ModelAndView("reportesAgotados"); // constructor ,
	// html
	// try {
	// mav.addObject("producto", producto.obtenerAgotados(pageable)); // crud
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// return mav;
	// }
	//
	// }


	@RequestMapping(method = RequestMethod.GET, value = "/reportes")
	public ModelAndView listar(Pageable pageable) { // metodo
		ModelAndView mav = new ModelAndView("reportesAgotados"); // constructor
																	// , html
		try {
			Page<Producto> page = producto.obtenerAgotados(pageable);
			mav.addObject("productos", page.getContent()); // crud
			// pages tiene todos las paginas enumeradas en un array
			// para 5 paginas, pages es {1,2,3,4,5}
			mav.addObject("pages", IntStream.range(1, page.getTotalPages() + 1).toArray());
			mav.addObject("producto", new Producto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	
	@RequestMapping(value = "/reportes/ventas")
	public ModelAndView listarVentasPorFecha(Pageable pageable) { //metodo
		ModelAndView mav = new ModelAndView("reportesVentas"); // constructor , html
		try {
			venta=new Venta();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		venta.setDetalles(new ArrayList<>());
		mav.addObject("detalles", new DetalleVenta());
		mav.addObject("venta", venta);

		return mav;
	}
	

}