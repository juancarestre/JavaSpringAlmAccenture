package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;

import co.almaccenture.model.Venta;

public interface RepositorioVenta extends CrudRepository<Venta, Integer> {

	public Venta findByIdVentaAndEstadoVenta(Integer idVenta, boolean estadoVenta);
	
}
