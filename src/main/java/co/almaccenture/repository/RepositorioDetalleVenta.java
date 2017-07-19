package co.almaccenture.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;

@Repository
public interface RepositorioDetalleVenta extends CrudRepository<DetalleVenta, Integer> {
	
	public DetalleVenta findByVentaAndProducto(Venta venta, Producto producto);
	
	public List<DetalleVenta> findByVenta(Venta venta);
	
}
