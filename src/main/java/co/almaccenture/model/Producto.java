package co.almaccenture.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;

/**
 * Se implementa el modelo , set and get
 * 
 * @author Uditeam
 *
 */
@Entity
@Table(name = "produto")

public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String idProducto;
	private String nombreProducto;
	private boolean estadoProducto = true;
	private Integer cantidadProducto;
	private float precioProducto;
	private String descripcionProducto;
	private Categoria categoria = null;
	private Date fechaModificacion = null;

	public Producto(String idProducto, String nombreProducto, int cantidadProducto,
			float precioProducto, String descripcionProducto, Categoria categoria) {

		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidadProducto = cantidadProducto;
		this.precioProducto = precioProducto;
		this.descripcionProducto = descripcionProducto;
		this.categoria = categoria;
		this.fechaModificacion = new Date(Calendar.getInstance().getTimeInMillis());
		
	}

	public Producto() {

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