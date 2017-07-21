package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Categoria;
import co.almaccenture.model.Producto;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositorioCategoriaTest {

	@Autowired
	RepositorioCategoria cateRepo;
	
	@Test
	public void testFindOne() {
		int id =  2;
		try {
			Categoria c = cateRepo.findOne(id);
			assertNotNull("No se encontro categoria con id " + id, c);
			List<Producto> prods = c.getProductos();
			for (Producto producto : prods) {
				System.out.println("Producto encontrado para categoria " + id
						+ ": " + producto.getNombreProducto());
			}
			System.out.println("Se encontraron " + prods.size() + " para categoria "+ id);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteID() {
		int id = 4;
		try {
			cateRepo.delete(id);
			assertNull("No se borro el objeto", cateRepo.findOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
