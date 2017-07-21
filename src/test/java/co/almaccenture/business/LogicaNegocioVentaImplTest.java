package co.almaccenture.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;
import co.almaccenture.repository.RepositorioProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogicaNegocioVentaImplTest {

	@Autowired
	LogicaNegocioVentaImpl logicaNegocio;
	@Autowired
	RepositorioProducto repositorioProducto;
	
	@Test
	public void testGuardarVenta() {

		String idProducto="abc123";
		Integer cantidad = 4;
		Venta venta,v;
		float total = 0;
		List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();
		
		
		try {
			detalle.add(logicaNegocio.agregarDetalleVenta(idProducto, cantidad));
			System.out.println("Detalle venta encontrado " + detalle.get(0).getProducto().getCategoria().getNombreCategoria());
			venta = logicaNegocio.nuevaVenta();
			venta.setDetalles(detalle);
			for(int i=0; i<detalle.size(); i++){
				total += detalle.get(i).getCantidad()*detalle.get(i).getValorUnitario();				
			}
			venta.setTotalVenta(total);
//			venta.setIdVenta();
			v = logicaNegocio.guardarVenta(venta);
			assertNotNull("No se guardo la venta",v);
			
		} catch (LogicaNegocioExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		//fail("Not yet implemented");
		
	}

}
