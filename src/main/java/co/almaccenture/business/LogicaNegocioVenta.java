package co.almaccenture.business;

import co.almaccenture.model.Caja;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;

/**
 * Métodos necesarios para ser consumidos por el controlador  
 * @author Administrator
 *
 */
public interface LogicaNegocioVenta {
	
	public DetalleVenta agregarDetalleVenta(String codigoProducto, int cantidad);
	public void guardarVenta(Venta venta);
	public void eliminarVenta(int codigoVenta);
	public Caja generarCaja();
}
