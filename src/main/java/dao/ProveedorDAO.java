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

import entities.Proveedor;
import entities.Proveedor_;

@Stateless
@LocalBean
public class ProveedorDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Proveedor getProveedor(int id) {
		
		return em.find(Proveedor.class, id);
		
	}
	
	public Proveedor getProveedorByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Proveedor> query = builder.createQuery(Proveedor.class);
		
		Root<Proveedor> root = query.from(Proveedor.class);

		query.from(Proveedor.class);
		
		Predicate p;
		
		p = builder.equal(root.get(Proveedor_.nombre), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<Proveedor> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}
	
	public List<Proveedor> getAllProveedores() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Proveedor> query = builder.createQuery(Proveedor.class);

		query.from(Proveedor.class);
		
		TypedQuery<Proveedor> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addProveedor(Proveedor proveedor) {
		try {
			em.persist(proveedor);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int update(Proveedor p) {

		try {
			em.merge(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(Proveedor p) {

		try {
			em.remove(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
