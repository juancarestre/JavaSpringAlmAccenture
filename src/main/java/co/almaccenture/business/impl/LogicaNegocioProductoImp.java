package co.almaccenture.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioProducto;

@Service
public class LogicaNegocioProductoImp implements LogicaNegocioProducto  {
	
	
	
	@Autowired
	private RepositorioProducto repositorioProducto;
	
	public static final int UMBRAL = 10;	
	public static final String MENSAJE_ALERTA_PRODUCTO_ESCASO = "Alerta: Este producto tiene pocas existencias en inventario.";
	public static final String MENSAJE_PRODUCTO_NO_ENCONTRADO = "El Producto con el codigo especificado no existe.";

	private static final String MENSAJE_PRODUCTO_INACTIVO = "El producto se encuentra en estado inactivo";
	
	@Override
	public void restarProductos(Producto producto, int cantidad) throws LogicaNegocioExcepcion {
		
		Integer stock;
		stock=producto.getCantidadProducto();
		
		producto.setCantidadProducto(stock-cantidad);
		repositorioProducto.save(producto);
		
	}
	@Override
	public boolean verificarCantidadProducto(Producto producto)throws LogicaNegocioExcepcion{
		
		if(producto == null){throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);}
		
		Integer stock = producto.getCantidadProducto();
		return stock <= UMBRAL;
		
	}
	@Override
	public Producto obtenerProducto(String id) throws LogicaNegocioExcepcion {
		if(id==null || "".equals(id.trim())) throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);
		
		Producto p = repositorioProducto.findOne(id);
		if(!p.getEstadoProducto()) throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_INACTIVO);

		return p;
	}
	
	
		
	

}
