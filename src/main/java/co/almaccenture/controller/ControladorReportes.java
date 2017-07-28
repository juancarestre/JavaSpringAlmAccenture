package co.almaccenture.controller;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.almaccenture.business.LogicaNegocioProducto;

import co.almaccenture.business.LogicaNegocioVenta;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.helpers.jpa.PageWrapper;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;

import co.almaccenture.model.Producto;

@Controller
public class ControladorReportes {

	@Autowired
	private LogicaNegocioProducto producto;
	@Autowired
	private LogicaNegocioVenta ventaBL;

	public static final String MENSAJE_FECHA_VACIA = "La fecha está vacia";
	private static final String REPORTE_VENTA_MAPPING = "/reportes/ventas";
	private static final String REPORTE_VENTA_HTML = "reportesVentas";
	private static final String REPORTE_VENTA_LIST_MAPPING = "/reportes/ventas/lista";
	private static final String FRAGMENTO_CALCULA_CAMBIO = "fragments :: calculaCambio";
	private static final String FRAGMENTO_DETALLE_VENTA= "fragments :: detalleVenta";
	
	private Venta venta;
	private PageWrapper<Venta> ventasPage;

	/**
	 * Lista los productos con cantidades menores a 100.
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/reportes/productosAgotados")
	public ModelAndView listar(Pageable pageable) { // metodo
		ModelAndView mav = new ModelAndView("reportesAgotados"); // constructor
																	// , html
		try {
			PageWrapper<Producto> page;
			page = new PageWrapper<>(producto.obtenerAgotados(pageable), 
					"/reportes/productosAgotados");
//			Page<Producto> page = producto.obtenerAgotados(pageable);
			mav.addObject("productos", page.getContent()); // crud
			// pages tiene todos las paginas enumeradas en un array
			// para 5 paginas, pages es {1,2,3,4,5}
			mav.addObject("pages", page);
			mav.addObject("producto", new Producto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * Direge a la vista de consulta de ventas por fecha
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/reportes/ventas")
	public ModelAndView ventasPorFecha(Pageable pageable) {
		ModelAndView mav = new ModelAndView("reportesVentas");
		
		venta = new Venta();
		venta.setDetalles(new ArrayList<>());
		//mav.addObject("ventas", ventasPage.getContent());
		mav.addObject("detalles", new DetalleVenta());
		mav.addObject("message", "");


		return mav;
	}

	/**
	 * Trae a la vista las ventas realizadas en un rango de ventas, validad las
	 * fechas, se cambia el formato de las fechas y son enviadas a las logica de
	 * negocio par traer las ventas.
	 * 
	 * @param req
	 * @param redirect
	 * @param pageable
	 * @return
	 * @throws RemoteException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/reportes/ventas/lista", params={"fechaInicio", "fechaFinal"})
	public ModelAndView agregarVentasPorFecha(HttpServletRequest req, RedirectAttributes redirect, Pageable pageable) throws RemoteException, ParseException{
		String message="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ModelAndView mav= new ModelAndView();
		float totalDineroReporte=0;
		try{
			String fechaInicial = req.getParameter("fechaInicio");
			String fechaFinal = req.getParameter("fechaFinal");

			if (fechaInicial.equals(""))
				throw new LogicaNegocioExcepcion(MENSAJE_FECHA_VACIA);
			if (fechaFinal.equals(""))
				throw new LogicaNegocioExcepcion(MENSAJE_FECHA_VACIA);
			fechaInicial = fechaInicial.replace("-", "/");
			fechaFinal = fechaFinal.replace("-", "/");
			java.util.Date date1 = sdf.parse(fechaInicial);
			java.util.Date date2 = sdf.parse(fechaFinal);

			Date sqldate1 = new Date(date1.getTime());
			Date sqldate2 = new Date(date2.getTime());
			
			Page<Venta> page =ventaBL.obtenerVentasPorFecha(sqldate1, sqldate2, pageable);  
			
			ventasPage = new PageWrapper<>(page, REPORTE_VENTA_LIST_MAPPING+"?fechaInicio="+fechaInicial+"&fechaFinal="+fechaFinal);
			System.out.println("Entre las fechas: " + sqldate1 + " y " + sqldate2 + " se ENCONTRARON: "
					+ page.getContent().size() + " registros de venta");
			

			totalDineroReporte=sumarTotalReporte(sqldate1, sqldate2);

		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
		}

		mav = new ModelAndView(REPORTE_VENTA_HTML);
		mav.addObject("ventas", ventasPage.getContent());
		mav.addObject("pages", ventasPage);
		mav.addObject("totaldineroventa",totalDineroReporte);
		mav.addObject("message", message);

		return mav;

	}
	
	@RequestMapping(value="/ventas/reporte",method= RequestMethod.GET)
	public String ventasReportes(final Model model){
		
			venta.getDetalles();
			model.addAttribute("venta",venta);
			return FRAGMENTO_DETALLE_VENTA;
		
	}
	
	public float sumarTotalReporte(Date fechainicial, Date fechafinal) {
		float suma=0;		
		List<Venta> totalVentasPorFecha= ventaBL.obtenerTotalVentasPorFecha(fechainicial, fechafinal);
		
		for(int i=0; i<totalVentasPorFecha.size(); i++){
			suma += (totalVentasPorFecha.get(i).getTotalVenta());		
		}
		
		System.out.println("Suma total del reporte: " + suma);
		return suma;
	}
	

}