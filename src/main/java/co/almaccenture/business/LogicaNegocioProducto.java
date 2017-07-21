package co.almaccenture.business;

import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Producto;

public interface LogicaNegocioProducto {
	
	
	/**
	 * Resta del inventario la cantidad de productos vendidos
	 * @param producto
	 * @throws LogicaNegocioExcepcion 
	 */
	public void restarProductos(Producto producto, int cantidad) throws LogicaNegocioExcepcion;
	
	/**
	 * Verifica si un producto está próximo a agotarse 
	 * @param producto
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	public boolean verificarCantidadProducto(Producto producto) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene un producto Activo a partir del codigo
	 */
	public Producto obtenerProducto(String id) throws LogicaNegocioExcepcion;

}
