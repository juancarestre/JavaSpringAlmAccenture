package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;

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
	private Boolean idEstado;

	/**
	 * @param idVenta
	 * @param fechaVenta
	 * @param idCaja
	 * @param totalVenta
	 */
	public Venta(Integer idVenta, Date fechaVenta, Integer idCaja, Float totalVenta, Boolean idEstado) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.idCaja = idCaja;
		this.totalVenta = totalVenta;
		this.idEstado = idEstado;
	}

	/**
	 * 
	 */
	public Venta() {

	}

	/**
	 * @return the idVenta
	 */
	public Integer getIdVenta() {
		return idVenta;
	}

	/**
	 * @param idVenta
	 *            the idVenta to set
	 */
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @return the fechaVenta
	 */
	public Date getFechaVenta() {
		return fechaVenta;
	}

	/**
	 * @param fechaVenta
	 *            the fechaVenta to set
	 */
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	/**
	 * @return the idCaja
	 */
	public Integer getIdCaja() {
		return idCaja;
	}

	/**
	 * @param idCaja
	 *            the idCaja to set
	 */
	public void setIdCaja(Integer idCaja) {
		this.idCaja = idCaja;
	}

	/**
	 * @return the totalVenta
	 */
	public Float getTotalVenta() {
		return totalVenta;
	}

	/**
	 * @param totalVenta
	 *            the totalVenta to set
	 */
	public void setTotalVenta(Float totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Boolean getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado
	 *            the idEstado to set
	 */
	public void setIdEstado(Boolean idEstado) {
		this.idEstado = idEstado;
	}

}