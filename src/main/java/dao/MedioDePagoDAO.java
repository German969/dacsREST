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

import entities.MedioDePago;
import entities.MedioDePago_;

@Stateless
@LocalBean
public class MedioDePagoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public MedioDePago getMedioDePago(int id) {
		
		return em.find(MedioDePago.class, id);
		
	}
	
	public MedioDePago getMedioDePagoByName(String name) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<MedioDePago> query = builder.createQuery(MedioDePago.class);
		
		Root<MedioDePago> root = query.from(MedioDePago.class);

		query.from(MedioDePago.class);
		
		Predicate p;
		
		p = builder.equal(root.get(MedioDePago_.nombre), name);
		
		query.select(root);
		
		query.where(p);

		TypedQuery<MedioDePago> typedQuery = em.createQuery(query);

		return typedQuery.getSingleResult();
		
	}
	
	public List<MedioDePago> getAllMediosDePago() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<MedioDePago> query = builder.createQuery(MedioDePago.class);

		query.from(MedioDePago.class);
		
		TypedQuery<MedioDePago> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addMedioDePago(MedioDePago mp) {
		try {
			em.persist(mp);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int update(MedioDePago mp) {

		try {
			em.merge(mp);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(MedioDePago mp) {

		try {
			em.remove(mp);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
