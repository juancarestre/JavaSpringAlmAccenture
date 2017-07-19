package co.almaccenture.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Caja;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositorioCajaTest {
	
	@Autowired
	RepositorioCaja cajaRepo;

	@Test
	public void testFindOneInteger() {
		int id = 3;
		
		try {
			Caja caja = cajaRepo.findOne(id);
			assertNotNull("No existe objeto", caja);
			System.out.println("Caja encontrada " + caja.getNombreCaja() );
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSave(){
		Caja caja = new Caja();
		caja.setNombreCaja("Caja 6");
		
		try {
			assertNotNull(cajaRepo.save(caja));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
