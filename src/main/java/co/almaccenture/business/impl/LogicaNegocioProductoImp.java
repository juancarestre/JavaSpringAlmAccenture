package co.almaccenture.business.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioProducto;

@Service
public class LogicaNegocioProductoImp implements LogicaNegocioProducto  {
	
	
	
	@Autowired
	private RepositorioProducto repositorioProducto;
	
	public static final int UMBRAL = 100;	
	public static final String MENSAJE_ALERTA_PRODUCTO_ESCASO = "Alerta: Este producto tiene pocas existencias en inventario.";
	public static final String MENSAJE_PRODUCTO_NO_ENCONTRADO = "El Producto con el codigo especificado no existe.";
	public static final String MENSAJE_PRODUCTO_INACTIVO = "El producto se encuentra en estado inactivo";
	public static final String MENSAJE_NO_ID = "El campo de código de producto está vacío";
	public static final String MENSAJE_NO_NOMBRE_PRODUCTO = "El campo de nombre de producto está vacío";
	public static final String MENSAJE_NO_CANTIDAD = "El campo de cantidad de producto está vacío";
	public static final String MENSAJE_CANTIDAD_NO_VALIDA = "La cantidad debe ser mayor de cero.";
	public static final String MENSAJE_NO_DESCRIPCION = "El campo de descripción de producto está vacío";
	public static final String MENSAJE_NO_CATEGORIA = "El campo de categoria de producto está vacío";
	public static final String MENSAJE_NO_PRECIO = "El campo de precio de producto está vacío";
	public static final String MENSAJE_PRECIO_NO_VALIDO = "El precio debe ser un campo mayor y diferente a cero";
	public static final String MENSAJE_NO_FECHA = "El campo de fecha de producto está vacío";
	public static final String MENSAJE_NO_ESTADO = "El campo de estado de producto está vacío";

	private static final String MENSAJE_NOINFO_PAGINACION = "No hay info para la paginación";

	private static final String MENSAJE_TAMANO_PAG_INCORRECTO = "La cantidad de items por pagina no es correcto";
	public static final String MENSAJE_ID_NO_COINCIDE = "El código del producto ingresado no coincide en la base de datos";
	
	
	@Override
	public void restarProducto(Producto producto, int cantidad) throws LogicaNegocioExcepcion {
		
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
	public Producto obtenerProductoActivo(String id) throws LogicaNegocioExcepcion {
		if(id==null || "".equals(id.trim())) throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);
		
		Producto p = repositorioProducto.findOne(id);
		
		if(p==null){throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);}
		if(!p.getEstadoProducto()) throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_INACTIVO);

		return p;
	}
	
	

	@Override
	public Page<Producto> obtenerProductosPorNombre(String nombre, Pageable pageable) throws LogicaNegocioExcepcion {
		if(nombre==null) throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);
		if(pageable==null) throw new LogicaNegocioExcepcion(MENSAJE_NOINFO_PAGINACION);
		if(pageable.getPageSize()<0) throw new LogicaNegocioExcepcion(MENSAJE_TAMANO_PAG_INCORRECTO);
		return repositorioProducto.findByNombreProductoContaining(nombre,pageable);
	}
	@Override
	public void modificarProducto(Producto producto) throws LogicaNegocioExcepcion {
		
		if(producto == null) throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);
		
		String id = producto.getIdProducto();
		if( id==null || "".equals(id.trim()) ) 
			throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);	
		if(repositorioProducto.findOne(id) == null)  
			throw new LogicaNegocioExcepcion(MENSAJE_ID_NO_COINCIDE);
		
		String nombre= producto.getNombreProducto();
		if(nombre == null || "".equals(nombre) ) 
			throw new LogicaNegocioExcepcion(MENSAJE_NO_NOMBRE_PRODUCTO);
		
		String descripcion = producto.getDescripcionProducto();
		if(descripcion == null || "".equals(descripcion) ) 
			throw new LogicaNegocioExcepcion(MENSAJE_NO_DESCRIPCION);
		
		Integer cantidad = producto.getCantidadProducto();
		if(cantidad < 0)
			throw new LogicaNegocioExcepcion(MENSAJE_NO_CANTIDAD);
		
		Float precio = producto.getPrecioProducto() ;
		if(precio <= 0)
			throw new LogicaNegocioExcepcion(MENSAJE_NO_PRECIO);
		
		Categoria categoria = producto.getCategoria();
		if(categoria == null)
			throw new LogicaNegocioExcepcion(MENSAJE_NO_CATEGORIA);
		
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		producto.setFechaModificacion(date);
		
		repositorioProducto.save(producto);
		
	}
	@Override
	public Producto obtenerProductoPorId(String id) throws LogicaNegocioExcepcion {
		
		if(id==null || "".equals(id.trim())) throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);
		Producto p = repositorioProducto.findOne(id);
		
		if(p==null){throw new LogicaNegocioExcepcion(MENSAJE_PRODUCTO_NO_ENCONTRADO);}
		
		return p;
	}

	@Override
	public Producto agregarProducto(Producto producto) throws LogicaNegocioExcepcion {
		
		String idProducto=producto.getIdProducto();
		if(idProducto==null || "".equals(idProducto.trim())) throw new LogicaNegocioExcepcion(MENSAJE_NO_ID);
	    String nombreProducto=producto.getNombreProducto();
		if(nombreProducto==null || "".equals(nombreProducto)) throw new LogicaNegocioExcepcion(MENSAJE_NO_NOMBRE_PRODUCTO);
		Float precioProducto = producto.getPrecioProducto();
		if(precioProducto==null || "".equals(precioProducto)) throw new LogicaNegocioExcepcion(MENSAJE_NO_PRECIO);
		if(precioProducto<=0) throw new LogicaNegocioExcepcion(MENSAJE_PRECIO_NO_VALIDO);
		String descripcionProducto = producto.getDescripcionProducto();
		if(descripcionProducto==null || "".equals(descripcionProducto)) throw new LogicaNegocioExcepcion(MENSAJE_NO_DESCRIPCION);
		Integer cantidadProducto =producto.getCantidadProducto();
		if(cantidadProducto==null || "".equals(cantidadProducto)) throw new LogicaNegocioExcepcion(MENSAJE_NO_CANTIDAD);
		if(cantidadProducto<0) throw new LogicaNegocioExcepcion(MENSAJE_CANTIDAD_NO_VALIDA);
		Categoria categoria = producto.getCategoria();
		if(categoria==null) throw new LogicaNegocioExcepcion(MENSAJE_NO_CATEGORIA);
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		producto.setFechaModificacion(date);
        return repositorioProducto.save(producto);
		
	}


	@Override
	public void eliminarLogicamenteProducto(String id) throws LogicaNegocioExcepcion {
		Producto p=obtenerProductoActivo(id);
		p.setFechaModificacion(new Date(Calendar.getInstance().getTimeInMillis()));
		p.setEstadoProducto(false);
		repositorioProducto.save(p);
	}

	@Override
	public Page<Producto> obtenerTodos(Pageable pageable) throws LogicaNegocioExcepcion {
		return repositorioProducto.findAll(pageable);
	}
	



	
	

		
}
