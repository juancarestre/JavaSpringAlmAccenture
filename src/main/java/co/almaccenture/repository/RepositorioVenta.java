package co.almaccenture.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Caja;
import co.almaccenture.model.Venta;

@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {

	public Venta findByIdVentaAndEstadoVenta(Integer idVenta, boolean estadoVenta);
	
	public List<Venta> findByCaja(Caja caja);
	
	public List<Venta> findByFechaVenta(Date date);
	
	public Page<Venta> findByFechaVentaBetween(Date date1, Date date2, Pageable pageabe);
	
	public List<Venta> findByFechaVentaBetween(Date date1, Date date2);

		
}
