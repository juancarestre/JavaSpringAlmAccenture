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
@Table(name = "categoria")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCategoria;
	private String nombreCategoria;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categoria", orphanRemoval = true, targetEntity=Producto.class)
	private List<Producto> productos;

	public Categoria() {

	}

	public Categoria(Integer idCategoria, String nombreCategoria) {

		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
}
