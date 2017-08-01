package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.MedioDePago;
import entities.MedioDePago_;

public class MedioDePagoFilter {
	
public MedioDePagoFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<MedioDePago,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return MedioDePago_.id;
		
		}
		
		if(cod.equals("2")){
			
			return MedioDePago_.nombre;
		
		}
		
		
		
		return null;
		
	}

}
