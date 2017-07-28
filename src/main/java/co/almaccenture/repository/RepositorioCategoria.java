/**
 * 
 */
package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Categoria;

/**
 * Interfaz que hereda de CRUDrepository, genera los metodos necesarios DAO 
 * @author Administrator
 *
 */
@Repository
public interface RepositorioCategoria extends CrudRepository<Categoria, Integer> {
/**
 * Retorna una categoria de la base de datos filtrada por el nombre de dicha categoria
 * @param nombreCategoria
 * @return
 */
	public Categoria findBynombreCategoria(String nombreCategoria);
}
