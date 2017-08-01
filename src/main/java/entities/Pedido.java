package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@XmlRootElement
@Table(name = "PEDIDO")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int id;
	
	@Column(name = "fecha",nullable=false)
	private Date fecha;
	
	@Column(name = "estado",nullable=false)
	private String estado;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario",nullable=false,updatable=false)
	private Usuario usuario;
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "pedidos")
	private List<Producto> productos;
	
	@Column(name = "fechaPago")
	private Date fechaPago;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mediodepago")
	private MedioDePago mediodepago;
	
	@Transient
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public Pedido(){
		
	}
	
	public Pedido(Date fecha, String estado, Usuario usuario,Date fechapago,MedioDePago mediodepago) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.usuario = usuario;
		this.fechaPago = fechapago;
		this.mediodepago = mediodepago;
		this.productos = new ArrayList<Producto>();
	}
	
	public Pedido(Date fecha, String estado, Usuario usuario,Date fechapago,MedioDePago mediodepago, List<Producto> productos) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.usuario = usuario;
		this.fechaPago = fechapago;
		this.mediodepago = mediodepago;
		this.productos = productos;
	}



	public Usuario getUsuario(){
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioDePago getMediodepago() {
		return mediodepago;
	}

	public void setMediodepago(MedioDePago mediodepago) {
		this.mediodepago = mediodepago;
	}
	
}
