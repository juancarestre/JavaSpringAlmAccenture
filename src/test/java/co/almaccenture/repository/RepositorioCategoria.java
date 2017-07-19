/**
 * 
 */
package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;

import co.almaccenture.model.Categoria;

/**
 * @author Administrator
 *
 */
public interface RepositorioCategoria extends CrudRepository<Categoria, Integer> {

}
