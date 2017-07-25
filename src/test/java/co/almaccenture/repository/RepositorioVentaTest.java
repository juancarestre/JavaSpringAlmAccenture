package co.almaccenture.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	@Autowired
	RepositorioDetalleVenta detaRepo;

	@Test
	public void testfindByFechaVentaBetween()  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		String iniDate = "2017/07/10";
		String endDate = "2017/07/24";
		
		int pagSize = 5; //numero de ventas por pagina
		
		
		try {
			
			java.util.Date date1 = sdf.parse(iniDate);
			long millisDate1 = date1.getTime();
			
			
			java.util.Date date2 = sdf.parse(endDate);
			long millisDate2 = date2.getTime();
			
			
			Date sqldate1=new Date(millisDate1);
			Date sqldate2=new Date(millisDate2);
			
			
			Page<Venta> pages = ventaRepo.findByFechaVentaBetween(sqldate1, sqldate2, new PageRequest(0,pagSize));
			
			System.out.println("Entre las fechas: " + date1 + " y " + date2 + " se ENCONTRARON: " + pages.getTotalElements() 
			+ " registros de venta");
			
			for(int i = 0;i<pages.getTotalPages();i++){
				for (Venta venta : pages) {
					System.out.println("Venta encontrada registrada con ID: " + venta.getIdVenta());
				}
				pages = ventaRepo.findByFechaVentaBetween(sqldate1, sqldate2, pages.nextPageable());
				assertNotNull("Lista vacia", pages);
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	
	
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
		int id = 56;

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
		
		
		List<DetalleVenta> detalles = ventaRepo.findOne(3).getDetalles();
		for (DetalleVenta detalleVenta : detalles) {
			detalleVenta.setVenta(venta);
		}
		detaRepo.save(detalles);
		System.out.println(detalles.size()+" Detalles de venta obtenidos de id 3");
		venta.setDetalles(detalles);
		
		
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
