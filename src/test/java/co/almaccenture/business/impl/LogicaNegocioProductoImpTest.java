/**
 * 
 */
package co.almaccenture.business.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Producto;

/**
 * @author Administrator
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogicaNegocioProductoImpTest {
	
	
	@Autowired
	LogicaNegocioProductoImp prodBl;
	/**
	 * Test method for {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#restarProducto(co.almaccenture.model.Producto, int)}.
	 */
	@Test
	public void testRestarProducto() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#verificarCantidadProducto(co.almaccenture.model.Producto)}.
	 */
	@Test
	public void testVerificarCantidadProducto() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#obtenerProducto(java.lang.String)}.
	 */
	@Test
	public void testObtenerProducto() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link co.almaccenture.business.impl.LogicaNegocioProductoImp#obtenerProductoPorNombre(java.lang.String)}.
	 */
	@Test
	public void testObtenerProductoPorNombre() {
		
		String nombre = "baño";
		
		try {
			Page<Producto> productos = prodBl.obtenerProductosPorNombre(nombre, new PageRequest(0,5));
			assertTrue("No se encontraron producto con nombre like " + nombre,productos.getTotalElements()>0);
			productos = prodBl.obtenerProductosPorNombre("",new PageRequest(0,5));
			assertTrue("No se encontraron todos los productos",productos.getTotalElements()>0);
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testObtenerProductos(){
		PageRequest paginaReq = new PageRequest(0, 5);
		try {
			Page<Producto> productos = prodBl.obtenerTodos(paginaReq);
			for(int i=0;i<productos.getTotalElements();i++){
				//Recorro las paginas
				for (Producto producto : productos) {
					//Recorro los productos de la pagina dada
					System.out.println("Producto encontrado "+producto.getNombreProducto());
				}
				//Obtengo la siguiente pagina
				productos = prodBl.obtenerTodos(productos.nextPageable());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
