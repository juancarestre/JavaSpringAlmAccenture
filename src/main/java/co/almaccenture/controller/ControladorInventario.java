package co.almaccenture.controller;

import java.util.stream.IntStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.model.Producto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.helpers.jpa.PageWrapper;
import co.almaccenture.model.Categoria;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioCategoria;
import co.almaccenture.repository.RepositorioProducto;

@Controller
public class ControladorInventario {
	
	private static final String INVENTARIO_HTML = "inventario";

	@Autowired
	private LogicaNegocioProducto productoBl;
	
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
			PageWrapper<Producto> page;
			page = new PageWrapper<>(productoBl.obtenerTodos(pageable),"/inventario");
			mav.addObject("productos", page.getContent()); // crud
			//pages tiene todos las paginas enumeradas en un array
			//para 5 paginas, pages es {1,2,3,4,5}
			mav.addObject("pages",page);
			mav.addObject("producto",new Producto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * Direge a la vista correspondiente a agregar un producto nuevo
	 * @return
	 */
	@RequestMapping(value = "/inventario/new")
	public ModelAndView nuevoProducto() {
		
		ModelAndView mav = new ModelAndView("AgregarProducto");
		mav.addObject("producto", new Producto());
		mav.addObject("message", "");
		return mav;
	}
	
	/**
	 * Ingresa un nuevo producto a la base de datos, se controla con excepciones los campos
	 * en blanco, se construye un producto y es enviado a la lógica de negocio de agregar producto. 
	 * @param req
	 * @return
	 */
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
			productoBl.agregarProducto(p);
			mav.setViewName("redirect:/inventario");

			
		
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
	 * Dirige de la vista de inventario a la vista de modificar un producto elegido por el usuario
	 * @param idProducto
	 * @return
	 */
	@RequestMapping(value = "/inventario/modificar/{idProducto}")
	public ModelAndView botonModificar(@PathVariable("idProducto") String idProducto) {
		
		ModelAndView mav = new ModelAndView("ModificarProducto");
		try {
			mav.addObject("producto", productoBl.obtenerProductoPorId(idProducto));
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
		}
		
		mav.addObject("message", "");
		return mav;
	}
	
	/**
	 * Se modifica un producto en la base de datos, se realizan validaciones correspondientes
	 * se actualiza los valores de producto según los ingresados por el usuario y se envía a la 
	 * lógica de negocio
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/inventario/modificar", params={"idProducto",
			"nombreProducto","descripcionProducto","precioProducto",
			"cantidadProducto","categoria.nombreCategoria"})
	public ModelAndView modificarProducto(HttpServletRequest req){
		
		String message="asd";
		ModelAndView mav= new ModelAndView();
		Producto p = new Producto();
		try{
			
			if(req.getParameter("idProducto").isEmpty()) throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);
			p = producto.obtenerProductoPorId(req.getParameter("idProducto"));
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
			Categoria cate;
			cate = repositorioCategoria.findBynombreCategoria(req.getParameter("categoria.nombreCategoria"));
			p.setCategoria(cate);
			productoBl.modificarProducto(p);
			mav.setViewName("redirect:/inventario");
		}catch (LogicaNegocioExcepcion e) {
			message=e.getMessage();
			mav.addObject("producto", p);
			mav.setViewName("/ModificarProducto");
			e.printStackTrace();
		}
		
		mav.addObject("message", message);
		
		return mav;
	}

	/**
	 * Cambia el estado cuando el usuario interactua el check box de estado
	 * @param idProducto
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/inventario/modificarestado/{idProducto}")
	public ModelAndView eliminarProducto(@PathVariable("idProducto") String idProducto) throws LogicaNegocioExcepcion {
		
		System.out.println("eliminarProducto" + idProducto);
		productoBl.cambiarLogicamenteProducto(idProducto);
		
		ModelAndView mav= new ModelAndView("redirect:/inventario");
		return mav;
}
	/**
	 * Busca un producto por el id enviado en el form buscar. Si hay algun error es catcheado por el bindingResult.
	 * Si no se ingresa ningun valor en queryParam id, se muestran todos los productos
	 * url: /inventario/consulta?id
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/inventario/consulta")
	public String buscarProductoPorCodigo(final Model model, @Valid Producto prod, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "redirect:/inventario";
		}
		String id = prod.getIdProducto();
		String message = ""; //mensaje para enviar error
		List<Producto> p = new ArrayList<>();
		try {
			p.add(productoBl.obtenerProductoPorId(id));
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
		//si no ingresa nada, muestre todos los productos
		if("".equals(prod.getIdProducto())) return "redirect:/inventario";
		
		model.addAttribute("productos", p);
		model.addAttribute("producto", new Producto());
		model.addAttribute("message",message);
		model.addAttribute("esConsulta",true);
		return INVENTARIO_HTML;
	}
	

}
