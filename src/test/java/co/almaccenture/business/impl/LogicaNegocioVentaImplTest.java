package co.almaccenture.business.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.net.SyslogAppender;
import co.almaccenture.business.impl.LogicaNegocioVentaImpl;
import co.almaccenture.exception.LogicaNegocioExcepcion;
import co.almaccenture.model.DetalleVenta;
import co.almaccenture.model.Venta;
import co.almaccenture.repository.RepositorioProducto;
import groovyjarjarcommonscli.ParseException;

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
				detalle.get(i).setVenta(venta);
			}
			venta.setTotalVenta(total);
			v = logicaNegocio.guardarVenta(venta);
			assertNotNull("No se guardo la venta",v);
			System.out.println("Venta guardada con id "+v.getIdVenta());
			
		} catch (LogicaNegocioExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void testEliminarVenta(){
		
		int id = 3;
		try {
			logicaNegocio.eliminarVenta(id);
			assertFalse("No se borro logicamente el elemento",logicaNegocio.obtenerVentaPorID(id).getEstadoVenta());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNuevaVenta(){
		try {
			Venta v = logicaNegocio.nuevaVenta();
			assertNotNull("No se genero nueva venta", v);
			System.out.println("Nueva venta con caja"+ v.getCaja().getNombreCaja()
					+ " y fecha "+ v.getFechaVenta()+ " y estado "+ v.getEstadoVenta());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testObtenerVentaFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		String iniDate = "2017/07/10";
		String endDate = "2017/07/24";
		
		try {
			
		java.util.Date date1 = sdf.parse(iniDate);
		long millisDate1 = date1.getTime();
		
		
		java.util.Date date2 = sdf.parse(endDate);
		long millisDate2 = date2.getTime();
		
		
		Date sqldate1=new Date(millisDate1);
		Date sqldate2=new Date(millisDate2);
		
		Page<Venta> page = logicaNegocio.obtenerVentasPorFecha(sqldate1, sqldate2,  new PageRequest(0,5));
		
		System.out.println("Entre las fechas: " + sqldate1 + " y " + sqldate2 + " se ENCONTRARON: " + page.getTotalElements() 
		+ " registros de venta");
		
		for(int i = 0;i<page.getTotalPages();i++){
			for (Venta venta : page) {
				System.out.println("Venta encontrada registrada con ID: " + venta.getIdVenta());
			}
			page = logicaNegocio.obtenerVentasPorFecha(sqldate1, sqldate2, page.nextPageable());
			assertNotNull("Lista vacia", page);
		}
		
		
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
		
	}


}
