package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@XmlRootElement
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id;
	
	@Column(name="nombre",nullable=false)
	@Size(min=1,max=50)
	private String nombre;
	
	@Column(name="apellido",nullable=false)
	@Size(min=1,max=50)
	private String apellido;
	
	@Column(name = "cuit_cuil",nullable=false,unique=true,length=8)
	@Size(min=1,max=20)
	private String cuit;
	
	@Column(name = "email",nullable=false,updatable=false,unique=true)
	@Size(min=1,max=40)
	private String email;
	
	@Column(name = "user_name",unique=true,nullable=false)
	@Size(min = 1, max = 50)
	private String user;
	
	@Column(name = "password",nullable=false)
	@Size(min = 1, max = 50)
	private String password;
	
	@Column(name = "telefono")
	@Size(max = 20)
	private String telefono;
	
	@Column(name = "pais")
	@Size(max = 50)
	private String pais;
	
	@Column(name = "provincia")
	@Size(max = 50)
	private String provincia;
	
	@Column(name = "localidad")
	@Size(max = 50)
	private String localidad;
	
	@Column(name = "codigo_postal")
	@Size(max = 10)
	private String codigoPostal;
	
	@Column(name = "direccion")
	@Size(max = 100)
	private String direccion;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol",nullable=false)
	private Rol rol;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Usuario() {

	}
	
	public Usuario(String nombre, String apellido, String cuit, String email, String user, String password, String telefono,
			String pais, String provincia, String localidad, String codigoPostal, String direccion, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit = cuit;
		this.email = email;
		this.user = user;
		this.password = password;
		this.telefono = telefono;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.rol = rol;
	}
	
	public Usuario(String nombre, String apellido, String cuit, String email, String user, String password, String telefono,
			String pais, String provincia, String localidad, String codigoPostal, String direccion, Rol rol,
			ArrayList<Pedido> pedidos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit = cuit;
		this.email = email;
		this.user = user;
		this.password = password;
		this.telefono = telefono;
		this.pais = pais;
		this.provincia = provincia;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
		this.rol = rol;
		this.pedidos = pedidos;
	}


	

	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
}
