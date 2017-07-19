package co.almaccenture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Venta;

@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {

	public Venta findByIdVentaAndEstadoVenta(Integer idVenta, int estadoVenta);
	
	public Venta findOne(Integer idVenta);
		
}
