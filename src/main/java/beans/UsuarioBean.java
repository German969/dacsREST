package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.RolDAO;
import dao.UsuarioDAO;
import entities.Rol;
import entities.Usuario;

@ManagedBean(name = "usuarioBean", eager = true)
@SessionScoped
public class UsuarioBean {
	
private int id;
	
	private String name;
	
	private String apellido;
	
	private String cuit;
	
	private String email;
	
	private String user;
	
	private String password;
	
	private String telefono;
	
	private String pais;
	
	private String provincia;
	
	private String localidad;
	
	private String codigoPostal;
	
	private String direccion;
	
	private String nameRol;
	
	private Rol rol;
	
	private Usuario usuario;
	
	private Usuario usuario2;
	
	private boolean newUsuario = false;

	@EJB
	UsuarioDAO dao;
	
	@EJB
	RolDAO rdao;
	
	public List<Usuario> usuarios;
	
	private String filterBy;
	
	public String filter=new String("");
	
	public boolean isFiltered = false;
	
	// ---------------- METHODS -------------------- //

	public String create() {
		
		telefono = new String("");
		pais = new String("");
		provincia = new String("");
		localidad = new String("");
		codigoPostal = new String("");
		direccion = new String("");
		
		rol = rdao.getRolByName(nameRol);

		Usuario u = new Usuario(name,apellido,cuit,email,user,password,telefono,pais,provincia,localidad,codigoPostal,direccion,rol);

		dao.addUsuario(u);
		
		this.getAll();
		
		return "usuarios.xhtml";

	}
	
	public String newUsuario(){
		
		newUsuario = true;
		
		nameRol = new String("");
		
		usuario = new Usuario();
		
		return null;
		
	}
	
	public String getAll() {
		
		isFiltered = false;

		usuarios = dao.getAllUsuarios();
		
		return "usuarios.xhtml";

	}
	
	public List<Usuario> getAllUsers() {
		
		if(isFiltered){
			
			usuarios = dao.getByFilter(filter,filterBy);
			
			
		}else{
			
			usuarios = dao.getAllUsuarios();
			
			if(newUsuario){
				
				Usuario u = new Usuario();
				
				u.setCanEdit(true);
				
				u.setNewUser(true);
				
				if(usuarios.isEmpty()){
					u.setId(1);
				}else{
					
					u.setId(usuarios.get(usuarios.size()-1).getId()+1);
					
				}
				
				
				usuarios.add(u);
				
			}
			
			
		}
		
		return usuarios;

	}
	
	public String getByFilter() {
		
		isFiltered  = true;
		
		return "usuarios.xhtml";
	
	}
	
	public String editUsuario(Usuario usuario) {
		  saveAllFields(usuario);
		  nameRol = usuario.getRol().getNombre();
	      usuario.setCanEdit(true);
	      return null;
	   }
	
	private void saveAllFields(Usuario usuario) {
		
		this.id = usuario.getId();
		this.apellido = usuario.getApellido();
		this.codigoPostal = usuario.getCodigoPostal();
		this.cuit = usuario.getCuit();
		this.direccion = usuario.getDireccion();
		this.email = usuario.getEmail();
		this.localidad = usuario.getLocalidad();
		this.name = usuario.getNombre();
		this.pais = usuario.getPais();
		this.password = usuario.getPassword();
		this.provincia = usuario.getProvincia();
		this.rol = usuario.getRol();
		this.telefono = usuario.getTelefono();
		this.user = usuario.getUser();
		
	}

	public String notEditUsuario(Usuario usuario) {
		
		if(newUsuario){
			usuarios.remove(usuarios.size()-1);
		}else{
			
			usuario.setApellido(apellido);
			usuario.setCodigoPostal(codigoPostal);
			usuario.setCuit(cuit);
			usuario.setDireccion(direccion);
			usuario.setEmail(email);
			usuario.setId(id);
			usuario.setLocalidad(localidad);
			usuario.setNombre(name);
			usuario.setPais(pais);
			usuario.setPassword(password);
			usuario.setProvincia(provincia);
			usuario.setRol(rol);
			usuario.setTelefono(telefono);
			usuario.setUser(user);
			
			usuario.setCanEdit(false);
			
			dao.update(usuario);
			
		}
		return null;
	   }
	
	public String saveUsuarios() {
	      
	      for (Usuario usuario : usuarios) {
	    	  if(usuario.isCanEdit()){
	    		  
	    		  if(usuario.isNewUser()){
	    			  
	    			  rol = rdao.getRolByName(nameRol);
	    			  
	    			  Usuario u = new Usuario(usuario.getNombre(),
	    					  					usuario.getApellido(),
	    					  					usuario.getCuit(),
	    					  					usuario.getEmail(),
	    					  					usuario.getUser(),
	    					  					usuario.getPassword(),
	    					  					usuario.getTelefono(),
	    					  					usuario.getPais(),
	    					  					usuario.getProvincia(),
	    					  					usuario.getProvincia(),
	    					  					usuario.getCodigoPostal(),
	    					  					usuario.getDireccion(),
	    					  					rol);
	    			  
	    			  dao.addUsuario(u);
	    				
	    			  this.getAll();
	    			  
	    		  }else{
	    			  
	    			  this.update();
	    			  
	    		  }
	    		  
	    	  };
	         usuario.setCanEdit(false);
	      }
	      
	      nameRol = new String("");
	      
	      newUsuario = false;
	      
	      return null;
	   }
	
	public List<Rol> getRoles(){
		
		return rdao.getAllRoles();
		
	}
	
	public void update() {
		
		usuario.setRol(rdao.getRolByName(nameRol));
		
		dao.update(usuario);

	}
	
	public void deleteUsuario(){
		dao.delete(usuario);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	// ---------------- GETTERS AND SETTERS -------------------- //

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public void setCodigoPostal(String coigoPostal) {
		this.codigoPostal = coigoPostal;
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

	public String getNameRol() {
		return nameRol;
	}

	public void setNameRol(String nameRol) {
		this.nameRol = nameRol;
	}

	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	

}
