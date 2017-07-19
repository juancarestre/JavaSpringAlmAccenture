package co.almaccenture.business;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Caja;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;
import co.almaccenture.repository.RepositorioCaja;
import co.almaccenture.repository.RepositorioProducto;
import co.almaccenture.repository.RepositorioVenta;

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

	@Autowired
	private RepositorioProducto repositorioProducto;
	@Autowired
	private RepositorioVenta repositorioVenta;
	@Autowired
	private RepositorioCaja repositorioCaja;

	/**
	 * 
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

	@Override
	public void guardarVenta(Venta venta) throws LogicaNegocioExcepcion {

		if (venta.getDetalles().isEmpty()) {
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_SIN_DETALLES);
		}
		
		repositorioVenta.save(venta);

	}

	@Override
	public void eliminarVenta(int idVenta) throws LogicaNegocioExcepcion {
		
		Venta venta = repositorioVenta.findByIdVentaAndEstadoVenta(idVenta, ACTIVO);
		
		if (venta == null) {
			throw new LogicaNegocioExcepcion(MENSAJE_VENTA_INEXISTENTE);
		}
		venta.setEstadoVenta(INACTIVO);
		repositorioVenta.save(venta);

	}

	/**
	 * Consulta todas las cajas existentes la base de datos y retorna una caja
	 * basado en un index generado aleatoriamente entre 0 y la cantidad de
	 * cajas.
	 */
	@Override
	public Caja generarCaja() {
		ArrayList<Caja> cajas = (ArrayList<Caja>) repositorioCaja.findAll();
		Random random = new Random();
		int index = random.nextInt(cajas.size());
		return cajas.get(index);
	}

}
