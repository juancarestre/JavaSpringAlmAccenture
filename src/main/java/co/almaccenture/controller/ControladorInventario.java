package co.almaccenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.almaccenture.business.LogicaNegocioProducto;

@Controller
public class ControladorInventario {
	
	@Autowired
	private LogicaNegocioProducto producto;
	
	@RequestMapping(method = RequestMethod.GET, value = "/inventario")
	public ModelAndView listar(Pageable pageable) { //metodo
		ModelAndView mav = new ModelAndView("inventario"); // constructor , html
		// mav.setViewName("/list");
		try {
			mav.addObject("producto", producto.obtenerTodos(pageable)); // crud
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

}
