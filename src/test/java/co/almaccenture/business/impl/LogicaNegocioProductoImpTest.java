/**
 * 
 */
package co.almaccenture.business.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
import javax.transaction.Transactional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.business.LogicaNegocioProducto;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;
import co.almaccenture.repository.RepositorioCategoria;
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
