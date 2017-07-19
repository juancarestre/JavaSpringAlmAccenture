package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Caja;
import co.almaccenture.model.Venta;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositorioVentaTest {

	@Autowired
	RepositorioVenta ventaRepo;
	@Autowired
	RepositorioCaja cajaRepo;

	@Test
	public void testFindByIdVentaAndEstadoVenta() {

		// Esto es para tomar una venta aleatoria
		List<Venta> ventas = (List<Venta>) ventaRepo.findAll();
		int id = ventas.get(ThreadLocalRandom.current().nextInt(ventas.size())).getIdVenta();

		try {
			assertNotNull("No se encontro venta con id " + id, ventaRepo.findByIdVentaAndEstadoVenta(id, true));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindOneInteger() {
		// Esto es para tomar una venta aleatoria
		List<Venta> ventas = (List<Venta>) ventaRepo.findAll();
		int id = ventas.get(ThreadLocalRandom.current().nextInt(ventas.size())).getIdVenta();

		try {
			assertNotNull("No se encontro la venta", ventaRepo.findOne(id));

			int idNull = 7; // este id no existe

			assertNull("La venta si existe", ventaRepo.findOne(idNull));

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindByCaja() {

		// Esto es para tomar una caja aleatoria
		List<Caja> cajas = (List<Caja>) cajaRepo.findAll();
		Caja caja = cajas.get(ThreadLocalRandom.current().nextInt(cajas.size()));

		try {
			ventaRepo.findByCaja(caja);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveVenta() {

		// Esto es para tomar una caja aleatoria
		List<Caja> cajas = (List<Caja>) cajaRepo.findAll();
		Caja caja = cajas.get(ThreadLocalRandom.current().nextInt(cajas.size()));

		Venta venta = new Venta();
		venta.setCaja(caja);
		venta.setFechaVenta(new Date(Calendar.getInstance().getTimeInMillis()));
		venta.setTotalVenta((float) 3782348);
		
		try {
			Venta ventaS = ventaRepo.save(venta);
			assertNotNull("No se guardo venta", ventaS);
			System.out.println("Venta guardada con estado " + venta.getEstadoVenta());
			System.out.println("y ID " + venta.getIdVenta());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
