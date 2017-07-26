package co.almaccenture.controller;

import java.util.stream.IntStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import co.almaccenture.repository.RepositorioProducto;

@Controller
public class ControladorInventario {
	
	private static final String INVENTARIO_HTML = "inventario";

	@Autowired
	private LogicaNegocioProducto producto;
	
	@Autowired
	private RepositorioCategoria repositorioCategoria;
	
	
	
	public static final String MENSAJE_NO_ID = "El campo de código de producto está vacío";
	public static final String MENSAJE_NO_NOMBRE_PRODUCTO = "El campo de nombre de producto está vacío";
	public static final String MENSAJE_NO_CANTIDAD = "El campo de cantidad de producto está vacío";
	public static final String MENSAJE_CANTIDAD_NO_VALIDA = "El campo de cantidad de producto está vacío.";
	public static final String MENSAJE_NO_DESCRIPCION = "El campo de descripción de producto está vacío";
	public static final String MENSAJE_NO_CATEGORIA = "El campo de categoria de producto está vacío";
	public static final String MENSAJE_NO_PRECIO = "El campo de precio de producto está vacío";
	public static final String MENSAJE_PRECIO_NO_VALIDO = "El precio debe ser un campo mayor y diferente a cero";

	
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
		mav.addObject("producto", new Producto());
		mav.addObject("message", "");
		return mav;
	}
	
		
	@RequestMapping(value="/inventario/new", params={"idProducto",
			"nombreProducto","descripcionProducto","precioProducto",
			"cantidadProducto","categoria.nombreCategoria"})
	public ModelAndView ingresarProducto(HttpServletRequest req){
		
		String message="";
		ModelAndView mav= new ModelAndView();
		
		try{
			Producto p = new Producto();
			if(req.getParameter("idProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);
			if(req.getParameter("nombreProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_NOMBRE_PRODUCTO);
			if(req.getParameter("cantidadProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_CANTIDAD);
			if(req.getParameter("descripcionProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_DESCRIPCION);
			if(req.getParameter("precioProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_PRECIO);
			if(req.getParameter("categoria.nombreCategoria").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_CATEGORIA);
			
			p.setIdProducto(req.getParameter("idProducto"));
			p.setNombreProducto(req.getParameter("nombreProducto"));
			p.setCantidadProducto(Integer.parseInt(req.getParameter("cantidadProducto")));
			p.setDescripcionProducto(req.getParameter("descripcionProducto"));
			p.setPrecioProducto(Float.valueOf(req.getParameter("precioProducto")));
			Categoria cate = new Categoria();
			cate = repositorioCategoria.findBynombreCategoria(req.getParameter("categoria.nombreCategoria"));
			p.setCategoria(cate);
			producto.agregarProducto(p);
			mav.setViewName("main");

			
		
		}catch (LogicaNegocioExcepcion e) {
			message=e.getMessage();
			mav.addObject("producto", new Producto());
			mav.setViewName("/AgregarProducto");
			e.printStackTrace();
		}
		
		mav.addObject("message", message);
		
		return mav;
	}
	
	/**
	 * Busca un producto por el id enviado como queryParam.
	 * url: /inventario/consulta?id
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/inventario/consulta", params={"id"})
	public String buscarProductoPorCodigo(final Model model, final HttpServletRequest req){
		String id = req.getParameter("id");
		String message = "";
		List<Producto> p = new ArrayList<>();
		try {
			p.add(producto.obtenerProductoPorId(id));
			message = p!=null ? "":"No se encontro producto";
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
		}	
		
		model.addAttribute("productos", p);
		model.addAttribute(message);
		return INVENTARIO_HTML;
	}
	

}
