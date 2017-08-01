package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.MedioDePagoDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import entities.MedioDePago;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;

@Stateless
@LocalBean
public class PedidoTest {
	
	@PersistenceContext
	private EntityManager em;
	

	
	@EJB
	ProductoDAO pdao;
	
	@EJB
	UsuarioDAO udao;
	
	@EJB
	MedioDePagoDAO mpdao;
	
	public void main(){
		
		Usuario u = udao.getUsuario(1);
		
		Producto p = pdao.getProducto(2);
		
		MedioDePago mp = mpdao.getMedioDePago(1);
		
		List<Producto> productos = new ArrayList<Producto>();
		
		productos.add(p);
		
		String estado = new String("ok");
		
		String sfecha = new String("Jan 12, 1952");
		
		DateFormat df = DateFormat.getDateInstance();
		
		Date fecha = null;
		
		try {
			fecha = df.parse(sfecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Pedido ped = new Pedido(fecha,estado,u,fecha,mp,productos);
		
		em.persist(ped);
		
		
	}

}
