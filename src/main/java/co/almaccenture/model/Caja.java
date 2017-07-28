package co.almaccenture.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Se implementa el modelo de caja , setters and getters
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "caja")
public class Caja{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCaja;
	private String nombreCaja;
	// Para obtener una venta dada una caja
	@OneToMany(fetch=FetchType.EAGER, mappedBy="caja", cascade=CascadeType.MERGE)
	private Set<Venta> ventas;

	public Caja() {

	}

	public Caja(Integer idCaja, String nombreCaja) {

		this.idCaja = idCaja;
		this.nombreCaja = nombreCaja;
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

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

}