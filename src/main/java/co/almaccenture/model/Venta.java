package co.almaccenture.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "venta")
public class Venta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVenta;
	private Date fechaVenta;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCaja")
	private Caja caja;
	private Float totalVenta;
	private Boolean estadoVenta = true; //Valor default para estado venta
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "venta")
	private List<DetalleVenta> detalleVentas;

	public Venta() {

	}

	public Venta(Integer idVenta, Date fechaVenta, Float totalVenta, Boolean estadoVenta) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.totalVenta = totalVenta;
		this.estadoVenta = estadoVenta;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Float getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Float totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Boolean getEstadoVenta() {
		return estadoVenta;
	}

	public void setEstadoVenta(Boolean estadoVenta) {
		this.estadoVenta = estadoVenta;
	}

	public List<DetalleVenta> getDetalles() {
		return detalleVentas;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalleVentas = detalles;
	}

}