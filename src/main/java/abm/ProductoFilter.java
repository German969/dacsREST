package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Producto;
import entities.Producto_;

public class ProductoFilter {
	
	public ProductoFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
	}

	public SingularAttribute<Producto,? extends Object> getAttr(){
		
		if(cod.equals("1")){
			
			return Producto_.id;
		
		}
		
		if(cod.equals("2")){
			
			return Producto_.nombre;
		
		}
		
		if(cod.equals("3")){
			
			return Producto_.marca;
		
		}
		
		if(cod.equals("4")){
			
			return Producto_.categoria;
		
		}
		
		if(cod.equals("5")){
			
			return Producto_.proveedor;
		
		}
		
		
		
		return null;
		
	}
	

}
