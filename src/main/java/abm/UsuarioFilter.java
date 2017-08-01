package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Usuario;
import entities.Usuario_;

public class UsuarioFilter {
	
	public UsuarioFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<Usuario,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return Usuario_.id;
		
		}
		
		if(cod.equals("2")){
			
			return Usuario_.nombre;
		
		}
		
		if(cod.equals("3")){
			
			return Usuario_.apellido;
		
		}
		
		if(cod.equals("4")){
			
			return Usuario_.cuit;
		
		}
		
		if(cod.equals("5")){
			
			return Usuario_.user;
		
		}
		
		if(cod.equals("6")){
			
			return Usuario_.password;
		
		}
		
		if(cod.equals("7")){
			
			return Usuario_.telefono;
		
		}
		
		if(cod.equals("8")){
			
			return Usuario_.pais;
		
		}
		
		if(cod.equals("9")){
			
			return Usuario_.provincia;
		
		}
		
		if(cod.equals("10")){
			
			return Usuario_.localidad;
		
		}
		
		if(cod.equals("11")){
			
			return Usuario_.codigoPostal;
		
		}
		
		if(cod.equals("12")){
			
			return Usuario_.direccion;
		
		}
		
		if(cod.equals("13")){
			
			return Usuario_.rol;
		
		}
		
		return null;
		
	}
	
}
