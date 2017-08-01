package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Proveedor;
import entities.Proveedor_;

public class ProveedorFilter {
	
public ProveedorFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<Proveedor,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return Proveedor_.id;
		
		}
		
		if(cod.equals("2")){
			
			return Proveedor_.nombre;
		
		}
		
		
		
		return null;
		
	}
	

}
