package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositorioProductoTest {
	
	@Autowired
	RepositorioProducto productoRepo;

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
		Categoria cat = new Categoria();
		cat.setIdCategoria(1);
		cat.setNombreCategoria("Aseo");
		producto.setCategoria(cat);
		producto.setIdProducto("mmr771");
		producto.setDescripcionProducto("JAbon de baño");
		producto.setEstadoProducto(true);
		producto.setFechaModificacion(new Date(Calendar.getInstance().getTimeInMillis()));
		producto.setPrecioProducto((float) 1800);
		producto.setNombreProducto("Jabon");
		
		try {
			Producto p = productoRepo.save(producto);
			assertNotNull("No se guardo el producto",p);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	

}
