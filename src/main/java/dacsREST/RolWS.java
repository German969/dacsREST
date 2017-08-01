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

import dao.RolDAO;
import entities.Rol;

@Path("/roles")
@Stateless
@LocalBean
public class RolWS {

	@EJB
	private RolDAO rolDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Rol getRol(@PathParam("id") int id) {
		return rolDao.getRol(id);
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Rol getRol(@PathParam("name") String name) {
		return rolDao.getRolByName(name);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rol> getRoles() {
		return rolDao.getAllRoles();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Rol addRol(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {

		Rol rol = new Rol(nombre);

		int result = rolDao.addRol(rol);

		if (result == 1) {
			return rol;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Rol editRol(@FormParam("id") int id, @FormParam("nombre") String nombre,
			@Context HttpServletResponse servletResponse) {

		Rol rol = rolDao.getRol(id);

		rol.setNombre(nombre);

		rolDao.update(rol);
		
		return rol;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Rol deleteRol(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Rol rol = rolDao.getRol(id);
		
		int result = rolDao.delete(rol);

		if (result == 1) {
			return rol;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/delete2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Rol deleteByNameRol(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {
		
		Rol rol = rolDao.getRolByName(nombre);
		
		int result = rolDao.delete(rol);

		if (result == 1) {
			return rol;
		} else {
			return null;
		}
	}

}
