package co.almaccenture.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Caja;
import co.almaccenture.model.DetalleVenta;
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
		int id = 3;

		try {
			Venta v = ventaRepo.findOne(id);
			assertNotNull("No se encontro la venta", v);
			
			List<DetalleVenta> detalles = v.getDetalles();
			for (DetalleVenta detalleVenta : detalles) {
				System.out.println("Detalle encontrado "+ detalleVenta.getIdDetalle());
			}
			System.out.println("Se encontro " + detalles.size()+ " de venta " + id);
			System.out.println("Venta " + id + " pertenece a caja " + v.getCaja().getNombreCaja());
			
			
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
