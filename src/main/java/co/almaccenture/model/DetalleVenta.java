package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDetalle;
	// Al actualizar un producto se actualiza el detalle
	@ManyToOne
	@JoinColumn(name="idProducto")
	private Producto producto;	
	// Al actualizar/borrar una venta se borra/actualiza respectivo detalle venta
	@ManyToOne
	@JoinColumn(name="idVenta")
	private Venta venta;
	private Integer cantidad;
	private Float valorUnitario;
	
	public DetalleVenta() {

	}

	public DetalleVenta(Integer cantidad, Float valorUnitario) {
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
	}

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
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