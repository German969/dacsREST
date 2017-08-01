package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entities.Usuario;
import entities.Usuario_;

@Stateless
@LocalBean
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public Usuario getUsuario(int id) {
		
		return em.find(Usuario.class, id);
		
	}
	
public Usuario getUsuarioByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = query.from(Usuario.class);

		query.from(Usuario.class);
		
		Predicate p;
		
		p = builder.equal(root.get(Usuario_.user), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<Usuario> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}

	public List<Usuario> getAllUsuarios() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);

		query.from(Usuario.class);

		TypedQuery<Usuario> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			em.persist(usuario);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addUsuario(Usuario usuario) {
		try {
			em.persist(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int update(Usuario u) {

		try {
			em.merge(u);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(Usuario u) {

		try {
			em.remove(u);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
