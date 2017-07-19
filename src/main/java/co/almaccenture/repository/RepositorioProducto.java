package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Producto;

@Repository
public interface RepositorioProducto extends CrudRepository<Producto, String> {

	public Producto findByIdProductoAndEstadoProducto(String idProducto, boolean estadoProducto);
	
}
