package test;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Rol;
import entities.Usuario;

@Stateless
@LocalBean
public class UsuarioTest {

	@PersistenceContext
	private EntityManager em;
	
	public void main(){
		
		Rol rol = new Rol("cliente");
		
		Usuario usuario = new Usuario("juan", "gomez", "12345", "juan@gmail.com", "juancito", "1234", "12345678", "arg", "ctes",
				"cap", "3400", "junin 1234", rol);
		
		em.persist(usuario);
		
		
	}
	
}
