/**
 * 
 */
package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Categoria;

/**
 * @author Administrator
 *
 */
@Repository
public interface RepositorioCategoria extends CrudRepository<Categoria, Integer> {

	public Categoria findBynombreCategoria(String nombreCategoria);
}
