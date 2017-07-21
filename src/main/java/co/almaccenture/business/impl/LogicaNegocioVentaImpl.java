package co.almaccenture.business.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.almaccenture.business.LogicaNegocioCaja;
import co.almaccenture.business.LogicaNegocioVenta;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Caja;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;
import co.almaccenture.repository.RepositorioCaja;
import co.almaccenture.repository.RepositorioProducto;
import co.almaccenture.repository.RepositorioVenta;
@Service
public class LogicaNegocioVentaImpl implements LogicaNegocioVenta {

	public static final boolean ACTIVO = true;
	public static final boolean INACTIVO = false;

	public static final String MENSAJE_ID_PRODUCTO_INVALIDO = "El codigo de producto debe contener algun valor.";
	public static final String MENSAJE_CANTIDAD_DETALLE_INVALIDA = "La cantidad especificada en el detalle no es válida.";
	public static final String MENSAJE_PRODUCTO_NO_ENCONTRADO = "El Producto con el codigo especificado no existe.";
	public static final String MENSAJE_PRODUCTO_AGOTADO = "El Producto especificado Está agotado.";
	public static final String MENSAJE_ALERTA_PRODUCTO_ESCASO = "Alerta: Este producto tiene pocas existencias en inventario.";
	public static final String MENSAJE_VENTA_INEXISTENTE = "No existe ninguna venta asociada a este codigo.";
	public static final String MENSAJE_VENTA_SIN_DETALLES = "La venta no tiene Productos agregados, Se debe agregar por lo menos un producto a la venta.";
	public static final String MENSAJE_VENTA_NULA = "Error: La venta no puede ser nula.";
	public static final String MENSAJE_VENTA_CAMPOS_SIN_COMPLETAR = "Error: Hay campos de venta sin completar.";
	public static final String MENSAJE_VENTA_TOTALVENTA_NO_COINCIDE = "El total de venta no es válido.";
	public static final String MENSAJE_VENTA_TOTALVENTA_NO_EXISTE = "No se generó el total de la venta";
	

	@Autowired
	private RepositorioProducto repositorioProducto;
	@Autowired
	private RepositorioVenta repositorioVenta;
	@Autowired
	private LogicaNegocioCaja cajaBl;

	
	/**
	 * Verifica los datos ingresados. Se obtiene el producto con el idProducto,
	 * se verifica si existe en la base de datos, se obtiene el precio del producto en el 
	 * momento de la venta. Finalmente se completa el objeto detalleventa con la cantidad,
	 * el precio, y el producto para ser retornado. 
	 */
	@Override
	public DetalleVenta agregarDetalleVenta(String idProducto, int cantidad) throws LogicaNegocioExcepcion {

		DetalleVenta detalleVenta;
		
		if (idProducto.isEmpty()) {
			throw new LogicaNegocioExcepcion(MENSAJE_ID_PRODUCTO_INVALIDO);
		}
		if (cantidad <= 0) {
			throw new LogicaNegocioExcepcion(MENSAJE_CANTIDAD_DETALLE_INVALIDA);
		}
		Producto producto = repositorioProducto.findByIdProductoAndEstadoProducto(idProducto, ACTIVO);
		if (producto == null) {
			throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);
		}

		Float precioDeCompra = producto.getPrecioProducto();
		detalleVenta = new DetalleVenta(cantidad, precioDeCompra);
		detalleVenta.setProducto(producto);
		
		return detalleVenta;
	}
	
	/**
	 * Verifica y almacena la venta: Se verifica que existan detalles de venta en la venta,
	 * así como el total de venta esté generado, luego se verfica que el total de la venta sea correcto,
	 * se inngresa el estado de la venta a activo. Finalmente se guarda la venta en la base de datos y 
	 * se retorna la venta ya almacenada.
	 * 
	 */
	@Override
	public Venta guardarVenta(Venta venta) throws LogicaNegocioExcepcion {

		List<DetalleVenta> detalleVenta;
		float total=0;
		
		if (venta.getDetalles().isEmpty()) {
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_SIN_DETALLES);
		}
		if (venta.getTotalVenta()==null ) {
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_TOTALVENTA_NO_EXISTE);
			}
		detalleVenta = venta.getDetalles();
		
		for(int i=0; i<detalleVenta.size(); i++){
			total += detalleVenta.get(i).getCantidad()*detalleVenta.get(i).getValorUnitario();	
			detalleVenta.get(i).setVenta(venta);
		}
		if(total != venta.getTotalVenta()){
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_CAMPOS_SIN_COMPLETAR);
		}
		
		venta.setEstadoVenta(ACTIVO);
		return repositorioVenta.save(venta);

	}
	
	/**
	 * Elimina una venta: Se busca por id, se verifica que existe y el estado y se realiza el 
	 * borrado lógico cambiando el estado a inactivo.
	 */
	@Override
	public void eliminarVenta(int idVenta) throws LogicaNegocioExcepcion {
		
		Venta venta = repositorioVenta.findByIdVentaAndEstadoVenta(idVenta, ACTIVO);
		
		if (venta == null || venta.getEstadoVenta() == INACTIVO) {
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_INEXISTENTE);
		}
		venta.setEstadoVenta(INACTIVO);
		repositorioVenta.save(venta);

	}
	/**
	 * Genera una nueva venta, ingresando la caja y la fecha, retrona la venta. 
	 */
	@Override
	public Venta nuevaVenta() throws LogicaNegocioExcepcion {
		
		Venta venta = new Venta();
		venta.setCaja(cajaBl.generarCajaAleatoria());
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		venta.setFechaVenta(date);
		return venta;
	}
	

}
