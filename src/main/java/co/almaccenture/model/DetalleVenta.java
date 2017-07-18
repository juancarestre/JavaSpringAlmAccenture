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
	private String idProducto;
	private Integer idVenta;
	private Integer cantidad;
	private Float valorUnitario;

	public DetalleVenta(String idProducto, Integer idVenta, Integer cantidad, Float valorUnitario) {
		super();
		this.idProducto = idProducto;
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
	}

	public DetalleVenta() {

	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
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