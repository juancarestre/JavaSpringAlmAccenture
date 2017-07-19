package co.almaccenture.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Caja;
import co.almaccenture.model.Venta;

@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {

	public Venta findByIdVentaAndEstadoVenta(Integer idVenta, boolean estadoVenta);
	
	public List<Venta> findByCaja(Caja caja);
	
	public List<Venta> findByFechaVenta(Date date);
		
}
