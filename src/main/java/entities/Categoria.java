package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement
@Table(name = "CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private long id;
	
	@Column(name="nombre",nullable=false)
	@Size(min=1,max=50)
	private String nombre;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria")
	private List<Producto> productos = new ArrayList<Producto>();
	
	@Transient
	private boolean canEdit;
	
	@Transient
	private boolean newCategoria;
	
	@Override
	public String toString(){
		
		return this.getNombre();
		
	}
	
	public Categoria(){
		
	}

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	
	public boolean isNewCategoria() {
		return newCategoria;
	}

	public void setNewCategoria(boolean newCategoria) {
		this.newCategoria = newCategoria;
	}
	
}
