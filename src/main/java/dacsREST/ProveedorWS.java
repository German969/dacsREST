package dacsREST;

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

import dao.ProveedorDAO;
import entities.Proveedor;

@Path("/proveedores")
@Stateless
@LocalBean
public class ProveedorWS {
	
	@EJB
	private ProveedorDAO proveedorDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor getProveedor(@PathParam("id") int id) {
		return proveedorDao.getProveedor(id);
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor getProveedor(@PathParam("name") String name) {
		return proveedorDao.getProveedorByName(name);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Proveedor> getProveedores() {
		return proveedorDao.getAllProveedores();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor addProveedor(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {

		Proveedor proveedor = new Proveedor(nombre);

		int result = proveedorDao.addProveedor(proveedor);

		if (result == 1) {
			return proveedor;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor editProveedor(@FormParam("id") int id, @FormParam("nombre") String nombre,
			@Context HttpServletResponse servletResponse) {

		Proveedor proveedor = proveedorDao.getProveedor(id);

		proveedor.setNombre(nombre);

		proveedorDao.update(proveedor);
		
		return proveedor;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor deleteProveedor(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Proveedor proveedor = proveedorDao.getProveedor(id);
		
		int result = proveedorDao.delete(proveedor);

		if (result == 1) {
			return proveedor;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/delete2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Proveedor deleteByNameProveedor(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {
		
		Proveedor p = proveedorDao.getProveedorByName(nombre);
		
		int result = proveedorDao.delete(p);

		if (result == 1) {
			return p;
		} else {
			return null;
		}
	}

}
