package co.almaccenture.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import co.almaccenture.business.LogicaNegocioProducto;

@Controller
public class ControladorReportes {
	
	@Autowired
	private LogicaNegocioProducto producto;
	
	@RequestMapping(method = RequestMethod.GET, value = "/reportes")
	public ModelAndView listar(Pageable pageable) { //metodo
		ModelAndView mav = new ModelAndView("reportesAgotados"); // constructor , html
		try {
			mav.addObject("producto", producto.obtenerAgotados(pageable)); // crud
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return mav;
	}
	
}