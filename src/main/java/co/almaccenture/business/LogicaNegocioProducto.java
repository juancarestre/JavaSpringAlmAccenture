package co.almaccenture.business;

import java.util.List;

import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Producto;

public interface LogicaNegocioProducto {
	
	
	/**
	 * Resta del inventario la cantidad de productos vendidos
	 * @param producto
	 * @throws LogicaNegocioExcepcion 
	 */
	public void restarProducto(Producto producto, int cantidad) throws LogicaNegocioExcepcion;
	
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
	public Producto obtenerProductoActivo(String id) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene los producto cuyo nombre contiene el parametro string dado
	 * @param nombre - Nombre del producto
	 * @return Producto(s) encontrado(s) con el nombre o null si no existe
	 * @throws LogicaNegocioExcepcion
	 */
	public List<Producto> obtenerProductoPorNombre(String nombre) throws LogicaNegocioExcepcion;
	
	public void modificarProducto(Producto producto) throws LogicaNegocioExcepcion;
	
	public Producto obtenerProductoPorId(String id) throws LogicaNegocioExcepcion;
	
	public void eliminarLogicamenteProducto(String id) throws LogicaNegocioExcepcion;
	

}
