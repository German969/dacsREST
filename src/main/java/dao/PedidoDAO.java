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

import entities.Pedido;

@Stateless
@LocalBean
public class PedidoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Pedido getPedido(int id) {
		
		return em.find(Pedido.class, id);
		
	}
	
	public List<Pedido> getAllPedidos() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);

		query.from(Pedido.class);
		
		TypedQuery<Pedido> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addPedido(Pedido pedido) {
		try {
			em.persist(pedido);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int update(Pedido p) {

		try {
			em.merge(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public int delete(Pedido p) {

		try {
			em.remove(p);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

}
