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

import entities.Producto;
import entities.Producto_;

@Stateless
@LocalBean
public class ProductoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Producto getProducto(int id) {
		
		return em.find(Producto.class, id);
		
	}
	
	public Producto getProductoByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		
		Root<Producto> root = query.from(Producto.class);

		query.from(Producto.class);
		
		Predicate p;
		
		p = builder.equal(root.get(Producto_.nombre), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<Producto> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}
	
	public List<Producto> getAllProductos() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);

		query.from(Producto.class);
		
		TypedQuery<Producto> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addProducto(Producto producto) {
		try {
			em.persist(producto);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int update(Producto p) {

		try {
			em.merge(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(Producto p) {

		try {
			em.remove(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
}
