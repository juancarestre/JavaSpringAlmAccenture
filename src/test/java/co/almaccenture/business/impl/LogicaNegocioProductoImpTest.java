/**
 * 
 */
package co.almaccenture.business.impl;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
			assertTrue("No se encontraron producto con nombre like " + nombre,prodBl.obtenerProductoPorNombre(nombre).size()>0);
			assertTrue("No se encontraron todos los productos",prodBl.obtenerProductoPorNombre("").size()>0);
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	

	@Transactional
	@Test
	public void testEliminarLogicamenteProducto() {
		String id="abc123";
		try {
			prodBl.eliminarLogicamenteProducto(id);
			System.out.println("Producto con nombre: " + prodBl.obtenerProductoPorId(id).getNombreProducto() + 
					" se ha actualizado con estado: "+prodBl.obtenerProductoPorId(id).getEstadoProducto());
			System.out.println("Fecha de modificacion: " + prodBl.obtenerProductoPorId(id).getFechaModificacion());
			assertFalse("Se elimino logicamente el producto", prodBl.obtenerProductoPorId(id).getEstadoProducto());
		} catch(LogicaNegocioExcepcion e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	

	@Test
	public void testModificarProducto() {
		
		Producto producto = new Producto();
		String id = "mmr732";
		try {
			producto = prodBl.obtenerProductoPorId(id);
			producto.setNombreProducto("Producto Capilar anti-calvicie");
			producto.setCantidadProducto(400);
			prodBl.modificarProducto(producto);
			
			assertEquals("Se modificó el producto exitosamente", "Producto Capilar anti-calvicie", producto.getNombreProducto());
		} catch (LogicaNegocioExcepcion e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	
	


}
