package co.almaccenture.business;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.almaccenture.exception.LogicaNegocioExcepcion;

import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;

/**
 * Métodos necesarios para ser consumidos por el controlador de la venta
 * 
 * @author Cristian Isaza - Sara Pavas
 *
 */
public interface LogicaNegocioVenta {
	/**
	 * Genera una nueva venta inicializando incializando fecha y caja
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	public Venta nuevaVenta() throws LogicaNegocioExcepcion; 

	/**
	 * recibe un produto y verifica que exista para luego completar el detalle y retornarlo completo.
	 * 
	 * @param codigoProducto Codigo del producto que se desea agregar como detalle. 
	 * @param cantidad cantidad del producto que se desea agregar como detalle.
	 * @return Detalle completo con nombre y precio unitario.
	 * @throws LogicaNegocioExcepcion 
	 */
	public DetalleVenta agregarDetalleVenta(String idProducto, int cantidad) throws LogicaNegocioExcepcion;

	/**
	 * Guarda una venta en la base de Datos.
	 * 
	 * @param venta
	 *            Objeto de tipo venta que será guardado en la base de datos.
	 * @return 
	 * @throws LogicaNegocioExcepcion 
	 */
	public Venta guardarVenta(Venta venta) throws LogicaNegocioExcepcion;

	/**
	 * Elimina una venta de forma lógica de la base de datos.
	 * 
	 * 
	 * @param codigoVenta codigo de la venta que se eliminará de forma lógica.
	 * @throws LogicaNegocioExcepcion 
	 */
	public void eliminarVenta(int idVenta) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene una venta de la base de datos dado un id
	 */
	public Venta obtenerVentaPorID(int idVenta) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene una lista paginada de las ventas en un rango de fecha: verifica las fechas no nulas y 
	 * secuencialidad de las fechas  obtiene las ventas de la base de datos.
	 * @param fechaInicio
	 * @param fechaFin
	 * @param pageable
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	public Page<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin, Pageable pageable) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene lista del total del dinero de las ventas para determinado rango de fechas, independientemente de las paginas
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public List<Venta> obtenerTotalVentasPorFecha(Date fechaInicio, Date fechaFin);
	
	
}
