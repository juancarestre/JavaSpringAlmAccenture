package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositorioProductoTest {
	
	@Autowired
	RepositorioProducto productoRepo;
	@Autowired
	RepositorioCategoria cateRepo;

	@Test
	public void testFindByIdProductoAndEstadoProducto() {	
		String idProd = "lmr180";
		
		try {
			assertNotNull("No se encontro producto", productoRepo.findByIdProductoAndEstadoProducto(idProd, true));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindOneString() {
		
		String codigo = " bne667";
		
		try {
			
			Producto prod = productoRepo.findByIdProductoAndEstadoProducto(codigo, true);
			assertNotNull("El producto no existe",prod);
			System.out.println("Categoria obtenida" + prod.getCategoria().getNombreCategoria());
				
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testSaveProduct(){
		Producto producto = new Producto();
		producto.setCantidadProducto(20);
		List<Categoria> categorias = (List<Categoria>) cateRepo.findAll();
		Categoria cat = categorias.get(ThreadLocalRandom.current().nextInt(categorias.size()));
		producto.setCategoria(cat);
		producto.setIdProducto("mmr732");
		producto.setDescripcionProducto("Producto Prueba");
		producto.setEstadoProducto(true);
		producto.setFechaModificacion(new Date(Calendar.getInstance().getTimeInMillis()));
		producto.setPrecioProducto((float) 1800);
		producto.setNombreProducto("Producto");
		
		try {
			Producto p = productoRepo.save(producto);
			assertNotNull("No se guardo el producto",p);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testUpdateProduct(){
		// Solo elimina si no existe detalleventa asociado
		// De igual manera no habrá borrado fisico de producto
		String id = "abc123";
		Producto p = productoRepo.findOne(id);
		p.setEstadoProducto(false);
				
		try {
			Producto p1 = productoRepo.save(p);
			assertFalse("No se actualizo producto " + id,p1.getEstadoProducto());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testBuscarProductoPorNombre(){
		String nombre = "baño";
		String nombre1 = "";
		try {
			List<Producto> p = productoRepo.findByNombreProductoContaining(nombre);
			assertTrue("No encontro producto con nombre like " + nombre, p.size()>0);
			assertNotNull("Encontro productos con string vacio",productoRepo.findByNombreProductoContaining(nombre1));
			assertTrue("No encontro productos con string vacio",productoRepo.findByNombreProductoContaining(nombre1).size()>0);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testBuscarProductosPageable(){
		int pagSize = 5;
		try{
			
			Page<Producto> prods = productoRepo.findAll(new PageRequest(1, pagSize));
			
			assertEquals("No se mostraron "+pagSize+" productos", pagSize,prods.getNumberOfElements());
			System.out.println("Numero de paginas de prods "+prods.getTotalPages());
			
			System.out.println("Mostrando primera pagina de 5 productos");
			
			for (Producto producto : prods.getContent()) {
				System.out.println("Producto "+producto.getNombreProducto());
			}
			
			prods = productoRepo.findAll(new PageRequest(2, pagSize));
			
			assertEquals("No se mostraron "+pagSize+" productos", pagSize,prods.getNumberOfElements());
			
			System.out.println("Mostrando segunda pagina de 5 productos");
			
			for (Producto producto : prods.getContent()) {
				System.out.println("Producto "+producto.getNombreProducto());
			}
		}catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFindAll(){
		try{
			Iterable<Producto> p = productoRepo.findAll();
			for (Producto producto : p) {
				System.out.println("Producto: " + producto.getIdProducto());
			}
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
