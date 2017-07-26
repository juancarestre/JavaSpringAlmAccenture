package co.almaccenture.repository;

import static org.junit.Assert.*;

import java.util.List;

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
		Venta v = ventaRepo.findOne(1);
		try {
			List<DetalleVenta> d = detalleRepo.findByVenta(v);
			assertNotNull("No se encontro detalle con venta" + v.getIdVenta(), d);
			System.out.println("Se encontraron " + d.size() +" detalles");
			for (DetalleVenta detalleVenta : d) {
				System.out.println("Producto encontrado "+ detalleVenta.getProducto().getNombreProducto());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFindOneDetalle(){
		int idDetalle = 5;
		try {
			DetalleVenta d = detalleRepo.findOne(idDetalle);
			assertNotNull("No encontro detalle",d);
			Venta v = d.getVenta();
			System.out.println("detalle "+idDetalle+" asociado a venta "+v.getIdVenta());
			Producto p = d.getProducto();
			System.out.println("Producto " + p.getNombreProducto());
			
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
	
	@Test
	public void testFindAll(){
		try {
			Iterable<DetalleVenta> detalles = detalleRepo.findAll();
			for (DetalleVenta detalleVenta : detalles) {
				System.out.println("Detalle encontrado"+detalleVenta.getIdDetalle());
				System.out.println("Producto: "+detalleVenta.getProducto().getNombreProducto());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
