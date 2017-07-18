package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = -6485132674087301295L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVenta;
	private Date fechaVenta;
	private Integer idCaja;
	private Float totalVenta;
	private Boolean estadoVenta;

	public Venta(Integer idVenta, Date fechaVenta, Integer idCaja, Float totalVenta, Boolean estadoVenta) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = new Date(Calendar.getInstance().getTimeInMillis());
		this.idCaja = idCaja;
		this.totalVenta = totalVenta;
		this.estadoVenta = estadoVenta;
	}

	public Venta() {

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

	public Integer getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Integer idCaja) {
		this.idCaja = idCaja;
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

}