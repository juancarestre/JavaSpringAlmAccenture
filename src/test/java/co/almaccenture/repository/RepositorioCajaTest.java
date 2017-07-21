package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.Caja;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;

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
			System.out.println("Caja encontrada " + caja.getNombreCaja());
			Set<Venta> ventas = caja.getVentas();
			
			
			for (Venta venta : ventas) {
				System.out.println("Venta con id" + venta.getIdVenta());
				List<DetalleVenta> d = venta.getDetalles();
				for (DetalleVenta detalleVenta : d) {
					System.out.println("detalle encontrado "+ detalleVenta.getProducto().getNombreProducto());
				}
			}
			System.out.println("Se encontraron "+ ventas.size()+ " ventas para "+caja.getNombreCaja());
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
	
	@Test
	public void testFindCajaByName(){
		try {
			String nombreCaja = "Caja 1";
			assertNotNull("No se encontro caja " + nombreCaja, cajaRepo.findCajaByNombreCaja(nombreCaja));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveCaja(){
		int id = 2;
		try {
			cajaRepo.delete(id);
			Caja caja = cajaRepo.findOne(id);
			assertNull("No se borro caja con id " + id,caja); //acierta si es nulo
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
