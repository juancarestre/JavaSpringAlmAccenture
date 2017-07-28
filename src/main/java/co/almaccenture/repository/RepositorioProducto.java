package co.almaccenture.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Producto;
/**
 * Interfaz que hereda de CRUDrepository, genera los metodos necesarios DAO 
 * @author Administrator
 *
 */
@Repository
public interface RepositorioProducto extends CrudRepository<Producto, String> {
/**
 * Retorna un producto por su respectivo ID y su estado actual (Activo o inactivo)
 * @param idProducto
 * @param estadoProducto
 * @return
 */
	public Producto findByIdProductoAndEstadoProducto(String idProducto, boolean estadoProducto);
	/**
	 * Encuentra productos por su nombre, los asocia a una paginacion
	 * @param nombreProducto
	 * @param pageable
	 * @return
	 */
	public Page<Producto> findByNombreProductoContaining(String nombreProducto, Pageable pageable);
	
	/**
	 * Retorna todos los productos paginados, cada pagina de productos se denomina slice. 
	 * Al llamar este metodo hacerlo de la forma RepositorioProducto.findAll(new PageRequest(<nro de slide>,<nro de elementos por pagina>)
	 * El <nro de slice> es zero-based (la primera es el indice 0)
	 * @param pageable Informacion de la pagina, del tipo PageRequest(<nro de slide>,<nro de elementos por pagina>)
	 * @return Paginacion de producto, con todas las slice disponibles.
	 */
	public Page<Producto> findAll(Pageable pageable);
	
	/**
	 * Retorna paginacion de productos cuya cantidad menor que < cantidad >
	 * @param cantidad - umbral de busqueda
	 * @param pageable - informacion de la paginacion
	 * @return Paginacion de productos
	 */
	public Page<Producto> findByCantidadProductoLessThan(int cantidad,Pageable pageable);
	
}
