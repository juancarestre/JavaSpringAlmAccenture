package co.almaccenture.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	public Page<Producto> obtenerProductosPorNombre(String nombre, Pageable pageable) throws LogicaNegocioExcepcion;

	
	/**
	 * Obtiene todos los productos en una pagina definida por Pageable,
	 * Pageable puede inicializarse asi
	 * 
	 * new PageRequest(0,5)
	 * 
	 * va a retornar la primera pagina con un tamaño de 5 productos por pagina
	 * @param pageable
	 * @return
	 * @throws LogicaNegocioExcepcion
	 */
	public Page<Producto> obtenerTodos(Pageable pageable) throws LogicaNegocioExcepcion;

	
	public void modificarProducto(Producto producto) throws LogicaNegocioExcepcion;
	
	public Producto obtenerProductoPorId(String id) throws LogicaNegocioExcepcion;
	

	public Producto agregarProducto(Producto producto) throws LogicaNegocioExcepcion;

	public void eliminarLogicamenteProducto(String id) throws LogicaNegocioExcepcion;
	
	/**
	 * Obtiene una lista paginada de productos agotados (productos cuya cantidad es menor que 100)
	 * @param pageable Pagina requerida
	 * @return Lista de productos agotados
	 * @throws LogicaNegocioExcepcion
	 */
	public Page<Producto> obtenerAgotados(Pageable pageable) throws LogicaNegocioExcepcion;

	

}
