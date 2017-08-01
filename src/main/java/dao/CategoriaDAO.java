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

import abm.CategoriaFilter;
import entities.Categoria;
import entities.Categoria_;

@Stateless
@LocalBean
public class CategoriaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Categoria getCategoria(int id) {
		
		return em.find(Categoria.class, id);
		
	}
	
public Categoria getCategoriaByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
		
		Root<Categoria> root = query.from(Categoria.class);

		query.from(Categoria.class);
		
		Predicate p;
		
		p = builder.equal(root.get(Categoria_.nombre), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<Categoria> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}

public List<Categoria> getAllCategorias() {

	CriteriaBuilder builder = em.getCriteriaBuilder();

	CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);

	query.from(Categoria.class);

	TypedQuery<Categoria> typedQuery = em.createQuery(query);

	return typedQuery.getResultList();
	
}

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public int addCategoria(Categoria categoria) {
	try {
		em.persist(categoria);
		return 1;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
}

public int update(Categoria c) {

	try {
		em.merge(c);
		return 1;
	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}

}

public int delete(Categoria c) {

	try {
		em.remove(c);
		return 1;
	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}

}

@SuppressWarnings("unchecked")
public List<Categoria> getByFilter(String name, String filterBy) {

	CriteriaBuilder builder = em.getCriteriaBuilder();

	CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);

	Root<Categoria> root = query.from(Categoria.class);

	Predicate p;
	
	CategoriaFilter cf = new CategoriaFilter();
	
	cf.setCod(filterBy);
	
	if(filterBy.equals(new String("1"))){
		
		int foo = Integer.parseInt(name);
		
		p = builder.equal(root.get(Categoria_.id), foo);
		
	}else{
		
		p = builder.like((Expression<String>) root.get(cf.getAttr()), "%" + name + "%");			
		
	}

	query.where(p);

	TypedQuery<Categoria> typedQuery = em.createQuery(query);

	List<Categoria> rl = typedQuery.getResultList();

	return rl;

}

}
