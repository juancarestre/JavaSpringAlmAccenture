package co.almaccenture.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Producto;

@Repository
public interface RepositorioProducto extends CrudRepository<Producto, String> {

	public Producto findByIdProductoAndEstadoProducto(String idProducto, boolean estadoProducto);
	
	public List<Producto> findByNombreProductoContaining(String nombreProducto);
	
	/**
	 * Retorna todos los productos paginados, cada pagina de productos se denomina slice. 
	 * Al llamar este metodo hacerlo de la forma RepositorioProducto.findAll(new PageRequest(<nro de slide>,<nro de elementos por pagina>)
	 * El <nro de slice> es zero-based (la primera es el indice 0)
	 * @param pageable Informacion de la pagina, del tipo PageRequest(<nro de slide>,<nro de elementos por pagina>)
	 * @return Paginacion de producto, con todas las slice disponibles.
	 */
	public Page<Producto> findAll(Pageable pageable);
	
}
