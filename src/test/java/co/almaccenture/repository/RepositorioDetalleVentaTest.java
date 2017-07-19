package co.almaccenture.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Producto;
import co.almaccenture.model.Venta;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositorioDetalleVentaTest {
	
	@Autowired
	RepositorioDetalleVenta detalleRepo;
	@Autowired
	RepositorioProducto prodRepo;
	@Autowired
	RepositorioVenta ventaRepo;

	@Test
	public void testFindByVentaAndProducto() {

		Venta venta = ventaRepo.findOne(2);
		Producto prod = prodRepo.findOne("wxy045");
		
		try {
			DetalleVenta detalle = detalleRepo.findByVentaAndProducto(venta, prod);
			assertNotNull("No se encontro detalle venta",detalle);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindByVenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindOneDetalle(){
		int idDetalle = 3;
		try {
			assertNotNull("No encontro detalle",detalleRepo.findOne(idDetalle));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSaveDetalle(){
		Venta venta = ventaRepo.findOne(3);
		Producto prod = prodRepo.findOne("wxy045");
		
		DetalleVenta d = new DetalleVenta();
		d.setCantidad(20);
		d.setValorUnitario((float) 12333);
		d.setVenta(venta);
		d.setProducto(prod);
		
		try {
			DetalleVenta dd = detalleRepo.save(d);
			assertNotNull("No se guardo detalle", dd);
			System.out.println("Producto de detalle guardado: "+dd.getProducto().getNombreProducto());
			System.out.println("Con Venta: "+dd.getVenta().getTotalVenta());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	

}
