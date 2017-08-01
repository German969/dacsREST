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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import abm.RolFilter;
import entities.Rol;
import entities.Rol_;

@Stateless
@LocalBean
public class RolDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Rol getRol(int id) {
		
		return em.find(Rol.class, id);
		
	}
	
	public Rol getRolByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Rol> query = builder.createQuery(Rol.class);
		
		Root<Rol> root = query.from(Rol.class);

		query.from(Rol.class);
		
		Predicate p;
		
		p = builder.equal(root.get(Rol_.nombre), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<Rol> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}
	
	public List<Rol> getAllRoles() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Rol> query = builder.createQuery(Rol.class);

		query.from(Rol.class);

		TypedQuery<Rol> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addRol(Rol rol) {
		try {
			em.persist(rol);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int update(Rol r) {

		try {
			em.merge(r);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(Rol r) {

		try {
			em.remove(r);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Rol> getByFilter(String name, String filterBy) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Rol> query = builder.createQuery(Rol.class);

		Root<Rol> root = query.from(Rol.class);

		Predicate p;
		
		RolFilter rf = new RolFilter();
		
		rf.setCod(filterBy);
		
		if(filterBy.equals(new String("1"))){
			
			int foo = Integer.parseInt(name);
			
			p = builder.equal(root.get(Rol_.id), foo);
			
		}else{
			
			p = builder.like((Expression<String>) root.get(rf.getAttr()), "%" + name + "%");			
			
		}

		query.where(p);

		TypedQuery<Rol> typedQuery = em.createQuery(query);

		List<Rol> rl = typedQuery.getResultList();

		return rl;

	}
	
}
