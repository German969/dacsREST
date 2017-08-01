package dacsREST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.MedioDePagoDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import entities.MedioDePago;
import entities.Pedido;
//import entities.Producto;
import entities.Usuario;

@Path("/pedidos")
@Stateless
@LocalBean
public class PedidoWS {
	
	@EJB
	private PedidoDAO pedidoDao;
	
	@EJB
	private UsuarioDAO usuarioDao;
	
	@EJB
	private ProductoDAO productoDao;
	
	@EJB
	private MedioDePagoDAO mpDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido getPedido(@PathParam("id") int id) {
		return pedidoDao.getPedido(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> getPedidos() {
		return pedidoDao.getAllPedidos();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido addPedido(@FormParam("fecha") String sfecha, @FormParam("estado") String estado, @FormParam("usuario") String nusuario, @FormParam("fechapago") String sfechapago, @FormParam("mediodepago") String nmediodepago, @Context HttpServletResponse servletResponse) {

		Usuario usuario = usuarioDao.getUsuarioByName(nusuario);
		
		MedioDePago mp = mpDao.getMedioDePagoByName(nmediodepago);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = null;
		Date fechapago = null;
		try {
			fecha = formatter.parse(sfecha);
			fechapago = formatter.parse(sfechapago);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Pedido pedido = new Pedido(fecha,estado,usuario,fechapago,mp);

		int result = pedidoDao.addPedido(pedido);

		if (result == 1) {
			return pedido;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido editPedido(@FormParam("id") int id, @FormParam("fecha") String sfecha, @FormParam("estado") String estado, @FormParam("usuario") String nusuario, @FormParam("fechapago") String sfechapago, @FormParam("mediodepago") String nmediodepago,
			@Context HttpServletResponse servletResponse) {

		Pedido pedido = pedidoDao.getPedido(id);
		
		Usuario usuario = usuarioDao.getUsuarioByName(nusuario);
		
		MedioDePago mp = mpDao.getMedioDePagoByName(nmediodepago);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = null;
		Date fechapago = null;
		try {
			fecha = formatter.parse(sfecha);
			fechapago = formatter.parse(sfechapago);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		pedido.setFecha(fecha);
		pedido.setEstado(estado);
		pedido.setFechaPago(fechapago);
		pedido.setUsuario(usuario);
		pedido.setMediodepago(mp);
		

		pedidoDao.update(pedido);
		
		return pedido;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido deletePedido(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Pedido pedido = pedidoDao.getPedido(id);
		
		int result = pedidoDao.delete(pedido);

		if (result == 1) {
			return pedido;
		} else {
			return null;
		}
	}
	
//	@POST
//	@Path("/{id}/productos")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Pedido addProducto(@PathParam("id") int id, @FormParam("producto") String producto,
//			@Context HttpServletResponse servletResponse) {
//
//		Pedido pedido = pedidoDao.getPedido(id);
//
//		Producto p = productoDao.getProductoByName(producto);
//		
////		Producto p2 = new Producto(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getComision(),p.getStock(),p.getCategoria(),p.getProveedor());
//		
//		List<Producto> productos = pedido.getProductos();
//		
//		productos.add(p);
//
//		Pedido pedido2 = new Pedido(pedido.getFecha(),pedido.getEstado(),pedido.getUsuario(),pedido.getFechaPago(),pedido.getMediodepago(),productos);
//		
//		pedidoDao.addPedido(pedido2);
//		
//		return pedido;
//	}
//	
//	@GET
//	@Path("/{id}/productos")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Producto> getProductos(@PathParam("id") int id) {
//		
//		Pedido pedido = pedidoDao.getPedido(id);
//		
//		List<Producto> productos = pedido.getProductos();
//		
//		return productos;
//	}

}
