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
@Table(name = "caja")
public class Caja implements Serializable {

	private static final long serialVersionUID = 6181775428546394955L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCaja;
	private String  nombreCaja;

	public Caja(Integer idCaja, String nombreCaja) {
		super();
		this.idCaja = idCaja;
		this.nombreCaja = nombreCaja;
	}

	public Caja() {
	}

	public Integer getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Integer idCaja) {
		this.idCaja = idCaja;
	}

	public String getNombreCaja() {
		return nombreCaja;
	}

	public void setNombreCaja(String nombreCaja) {
		this.nombreCaja = nombreCaja;
	}

}