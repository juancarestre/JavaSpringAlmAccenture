/**
 * 
 */
package co.almaccenture.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Esta clase es la encargada de agregar información a la vista así
 * como recibir valores ingresads por el usuario para hacer su previa validación
 * @author Federico
 *
 */
public interface ControladorVenta {
	
	/**
	 * El usuario inicia una nueva venta desde cero (Aun no se asigna idVenta)
	 * Este metodo se ejecuta con la ruta almaccenture/ventas
	 * @return Vista y Modelo con información inicial de nueva Venta como fecha
	 */
	public ModelAndView iniciaVenta();
	
	/**
	 * Al iniciar venta el modulo recibe una caja definida por la caja de negocio
	 * @return Caja que se va usar
	 */
	//public Caja cargarCaja();
	
	/**
	 * El usuario ingresa el codigo del producto y la cantidad que quiere agregarse a la venta.
	 * Si el producto ya existe, suma la cantidad del producto ingresado, por el producto ya existente
	 * para cumplir requisito de solo un codigo de producto por venta. 
	 * Agrega el producto a la lista de DetalleVenta, y calcula subtotal y Total parcial.
	 * tipo POST
	 * TODO: url: almaccenture/ventas/producto?id=<idproducto>&cant=<cant> (
	 * @param idProducto
	 * @param cantidad
	 * @return Vista y modelo con la tabla actualizada (almaccenture/ventas)
	 */
	public ModelAndView ingresarProducto(String idProducto, int cantidad);
	
	/**
	 * Elimina un producto de la tabla de productos de venta, obtiene id de producto por medio
	 * de QueryParam y redirije a la misma pagina con la tabla actualizada. Se accede bajo la ruta
	 * almaccenture/ventas/del?idProd=<idProd>
	 * @param req
	 * @param redirect
	 * @return a vista de venta con la tabla actualizada. url: /almaccenture/ventas
	 */
	public ModelAndView eliminarProducto(final HttpServletRequest req, RedirectAttributes redirect);
	
	/**
	 * El usuario confirma la venta para ser procesada por la capa de negocio y persistirla.
	 * En caso de alguna excepción tirada por la capa de bl, se escala hasta mostrar en la vista.
	 * Lanza modal para confirmar y si es aceptada, lanza modal con el id de la venta.
	 * url: almaccenture/ventas/confirmar
	 * TODO: Como tirar un modal o popup antes de ir a nueva Venta.
	 * @return Un Modal o un popup
	 */
	public ModelAndView confirmarVenta();
	
	
}
