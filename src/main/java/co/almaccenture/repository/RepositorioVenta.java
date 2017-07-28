package co.almaccenture.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.Caja;
import co.almaccenture.model.Venta;
/**
 * Interfaz que hereda de CRUDrepository, genera los metodos necesarios DAO 
 * @author Administrator
 *
 */
@Repository
public interface RepositorioVenta extends CrudRepository<Venta, Integer> {
/**
 * Retorna una venta por su ID de venta y su respectivo estado
 * @param idVenta
 * @param estadoVenta
 * @return
 */
	public Venta findByIdVentaAndEstadoVenta(Integer idVenta, boolean estadoVenta);
	/**
	 * Retorna una lista de ventas asociadas a una caja
	 * @param caja
	 * @return
	 */
	public List<Venta> findByCaja(Caja caja);
	/**
	 * Retorna una lista de ventas relacionadas a una fecha en particular
	 * @param date
	 * @return
	 */
	public List<Venta> findByFechaVenta(Date date);
	/**
	 * Devuelve una Lista Page que contiene las ventas comprendidas en el rango de fechas que recibe como parametro, ademas las divide en paginas
	 * @param date1
	 * @param date2
	 * @param pageabe
	 * @return
	 */
	public Page<Venta> findByFechaVentaBetween(Date date1, Date date2, Pageable pageabe);
	/**
	 * Devuelve una lista que contiene las todas ventas comprendidas en el rango de fecas que recibe como parametro, no depende de paginacion
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<Venta> findByFechaVentaBetween(Date date1, Date date2);

		
}
