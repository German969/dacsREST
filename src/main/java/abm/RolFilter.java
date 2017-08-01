package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Rol;
import entities.Rol_;

public class RolFilter {
	
public RolFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<Rol,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return Rol_.id;
		
		}
		
		if(cod.equals("2")){
			
			return Rol_.nombre;
		
		}
		
		
		
		return null;
		
	}

}
