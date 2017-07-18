package co.almaccenture.business;

import co.almaccenture.model.Caja;
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
	 * recibe un produto y verifica que exista para luego completar el detalle y retornarlo completo.
	 * 
	 * @param codigoProducto Codigo del producto que se desea agregar como detalle. 
	 * @param cantidad cantidad del producto que se desea agregar como detalle.
	 * @return Detalle completo con nombre y precio unitario.
	 */
	public DetalleVenta agregarDetalleVenta(String codigoProducto, int cantidad);

	/**
	 * Guarda una venta en la base de Datos.
	 * 
	 * @param venta
	 *            Objeto de tipo venta que será guardado en la base de datos.
	 */
	public void guardarVenta(Venta venta);

	/**
	 * Elimina una venta de forma lógica de la base de datos.
	 * 
	 * @param codigoVenta
	 *            codigo de la venta que se eliminará de forma lógica.
	 */
	public void eliminarVenta(int codigoVenta);

	/**
	 * 
	 * @return Retorna una caja aleatoriamente desde la base de datos.
	 */
	public Caja generarCaja();
}
