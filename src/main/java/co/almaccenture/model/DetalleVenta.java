package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {

	private static final long serialVersionUID = 531582601623741115L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Producto producto= null;
	private Venta venta;
	private Integer cantidad;
	private Float valorUnitario;

	public DetalleVenta(Producto producto, Integer cantidad ) {
		super();
		this.producto = producto;
		this.venta = venta;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
	}

	public DetalleVenta() {

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}