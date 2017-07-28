package co.almaccenture.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;
/**
 * Interfaz que hereda de CRUDrepository, genera los metodos necesarios DAO 
 * @author Administrator
 *
 */
@Repository
public interface RepositorioDetalleVenta extends CrudRepository<DetalleVenta, Integer> {
	/**
	 * Retorna un detalle venta filtrado por la venta y por el respectivo producto
	 * @param venta
	 * @param producto
	 * @return
	 */
	public DetalleVenta findByVentaAndProducto(Venta venta, Producto producto);
	/**
	 * Retorna lista de detalleventa de una respectiva venta
	 * @param venta
	 * @return
	 */
	public List<DetalleVenta> findByVenta(Venta venta);
	
	
}
