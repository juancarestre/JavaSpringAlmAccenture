package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;

import co.almaccenture.model.Producto;

public interface RepositorioProducto extends CrudRepository<Producto, String> {

	public Producto findByIdProductoAndEstadoProducto(String idProducto, boolean estadoProducto);
	
}
