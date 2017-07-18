package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
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

	private static final long serialVersionUID = -6485132674087301295L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVenta;
	private Date fechaVenta;
	private Caja caja;
	private Float totalVenta;
	private Boolean estadoVenta;
	private List<DetalleVenta> detalles; 

	public Venta(Integer idVenta, Date fechaVenta, Caja caja, Float totalVenta, Boolean estadoVenta) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = new Date(Calendar.getInstance().getTimeInMillis());
		this.caja = caja;
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

}