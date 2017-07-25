/**
 * 
 */
package co.almaccenture.business.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioCategoria;

/**
 * @author Administrator
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogicaNegocioProductoImpTest {

	@Autowired
	LogicaNegocioProductoImp prodBl;

	@Autowired
	RepositorioCategoria repositorioCategoria;
	/**
	 * Test method for
	 * {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#restarProducto(co.almaccenture.model.Producto, int)}.
	 */
	@Test
	public void testRestarProducto() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#verificarCantidadProducto(co.almaccenture.model.Producto)}.
	 */
	@Test
	public void testVerificarCantidadProducto() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#obtenerProducto(java.lang.String)}.
	 */
	@Test
	public void testObtenerProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregarProducto() {
		
		Producto producto = new Producto();
		Scanner leer = new Scanner(System.in);
		
		System.out.println("Ingrese el nombre");
		String nombre=leer.nextLine();
	    producto.setNombreProducto(nombre);
	    
		System.out.println("Ingrese el código");
		String codigo=leer.nextLine();	    
		
		producto.setIdProducto(codigo);
		
		System.out.println("Ingrese la descripción");
		String descripcion=leer.nextLine();	
	
		producto.setDescripcionProducto(descripcion);
		
		System.out.println("Ingrese el precio");
		Float precio=leer.nextFloat();	
		
		producto.setPrecioProducto(precio);
		
		System.out.println("Ingrese la cantidad");
		Integer cantidad=leer.nextInt();
		
		producto.setCantidadProducto(cantidad);
		
		System.out.println("Ingrese la categoría ingrese 1 para Aseo, 2 para tecnología, 3 para hogar, 4 para moda");
		Integer categoria=leer.nextInt();
		
		Categoria cate = new Categoria();
		cate = repositorioCategoria.findOne(categoria);
		producto.setCategoria(cate);
		

		try{
			
			Producto producto1 = new Producto();
			
			producto1=prodBl.agregarProducto(producto);
			assertNotNull(producto1);
			
			
		}catch(LogicaNegocioExcepcion ex){

			fail("Hubo un error "+ex);
		}
	}

	/**
	 * Test method for
	 * {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#obtenerProductoPorNombre(java.lang.String)}.
	 */
	@Test
	public void testObtenerProductoPorNombre() {

		String nombre = "baño";

		try {
			assertTrue("No se encontraron producto con nombre like " + nombre,
					prodBl.obtenerProductoPorNombre(nombre).size() > 0);
			assertTrue("No se encontraron todos los productos", prodBl.obtenerProductoPorNombre("").size() > 0);
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
