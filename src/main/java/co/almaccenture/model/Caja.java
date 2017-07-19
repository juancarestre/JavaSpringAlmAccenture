package co.almaccenture.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "caja")
public class Caja implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCaja;
	private String nombreCaja;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "caja", orphanRemoval = true, targetEntity=Venta.class)
	private List<Venta> ventas;

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

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

}