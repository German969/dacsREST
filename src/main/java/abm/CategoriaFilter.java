package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Categoria;
import entities.Categoria_;

public class CategoriaFilter {
	
public CategoriaFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<Categoria,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return Categoria_.id;
		
		}
		
		if(cod.equals("2")){
			
			return Categoria_.nombre;
		
		}
		
		
		
		return null;
		
	}

}
