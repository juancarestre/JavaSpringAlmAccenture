package co.almaccenture.controller;

import java.util.stream.IntStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.model.Producto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioCategoria;

@Controller
public class ControladorInventario {
	
	@Autowired
	private LogicaNegocioProducto producto;
	
	@Autowired
	private RepositorioCategoria repositorioCategoria;
	
	private Producto p = new Producto();
	
	/**
	 * Muestra lista de todos los productos en inventario, pageable es rellenado
	 * con los parametros size y page que vienen en la url, por default es un pageable
	 * con size 20 y page 0. url : /inventario?page=0&size=5
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/inventario")
	public ModelAndView listar(Pageable pageable) { //metodo
		ModelAndView mav = new ModelAndView("inventario"); // constructor , html
		try {
			Page<Producto> page = producto.obtenerTodos(pageable);
			mav.addObject("productos", page.getContent()); // crud
			//pages tiene todos las paginas enumeradas en un array
			//para 5 paginas, pages es {1,2,3,4,5}
			mav.addObject("pages",IntStream.range(1,page.getTotalPages()+1).toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "/inventario/new")
	public ModelAndView nuevoProducto() {
		
		ModelAndView mav = new ModelAndView("AgregarProducto");
		mav.addObject("producto",p);
		return mav;
	}
	
		
	@RequestMapping(value="/inventario/new", params={"idProducto",
			"nombreProducto","descripcionProducto","precioProducto",
			"cantidadProducto","categoria.nombreCategoria"})
	public ModelAndView ingresarProducto(HttpServletRequest req){
		
		
		
		try{
			p.setIdProducto(req.getParameter("idProducto"));
			p.setNombreProducto(req.getParameter("nombreProducto"));
			p.setCantidadProducto(Integer.parseInt(req.getParameter("cantidadProducto")));
			p.setDescripcionProducto(req.getParameter("descripcionProducto"));
			p.setPrecioProducto(Float.valueOf(req.getParameter("precioProducto")));
			Categoria cate = new Categoria();
			cate = repositorioCategoria.findBynombreCategoria(req.getParameter("categoria.nombreCategoria"));
			p.setCategoria(cate);
			producto.agregarProducto(p);
		
		}catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
		}
		
		// Agrega producto a a lista de productos de venta
		ModelAndView mav= new ModelAndView("main");

		return mav;
	}
	

}
