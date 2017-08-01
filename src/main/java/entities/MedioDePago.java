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
@Table(name = "MEDIO_DE_PAGO")
public class MedioDePago {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_mediodepago")
	private int id;
	
	@Column(name="nombre",nullable=false)
	@Size(min=1,max=50)
	private String nombre;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "mediodepago")
	List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@Transient
	private boolean canEdit;
	
	@Transient
	private boolean newMedioDePago;
	
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	
	public boolean isNewMedioDePago() {
		return newMedioDePago;
	}

	public void setNewMedioDePago(boolean newMedioDePago) {
		this.newMedioDePago = newMedioDePago;
	}
	
	public MedioDePago(){
		
	}

	public MedioDePago(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id2) {
		this.id = id2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
}
