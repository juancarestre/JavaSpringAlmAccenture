package co.almaccenture.model;

import java.sql.Date;

import javax.persistence.Entity;
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
@Table(name = "producto")

public class Producto {

	@Id
	private String idProducto;
	private String nombreProducto;
	private Boolean estadoProducto = true;
	private Integer cantidadProducto;
	private Float precioProducto;
	private String descripcionProducto;
	
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	private Date fechaModificacion;

	public Producto() {

	}

	public Producto(String idProducto, String nombreProducto, Boolean estadoProducto, Integer cantidadProducto,
			Float precioProducto, String descripcionProducto, Date fechaModificacion) {

		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.estadoProducto = estadoProducto;
		this.cantidadProducto = cantidadProducto;
		this.precioProducto = precioProducto;
		this.descripcionProducto = descripcionProducto;
		this.fechaModificacion = fechaModificacion;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Boolean getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(Boolean estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}