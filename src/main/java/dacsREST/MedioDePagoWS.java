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

import dao.MedioDePagoDAO;
import entities.MedioDePago;

@Path("/mediosdepago")
@Stateless
@LocalBean
public class MedioDePagoWS {
	
	@EJB
	private MedioDePagoDAO mpDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MedioDePago getMedioDePago(@PathParam("id") int id) {
		return mpDao.getMedioDePago(id);
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public MedioDePago getMedioDePago(@PathParam("name") String name) {
		return mpDao.getMedioDePagoByName(name);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MedioDePago> getMediosDePago() {
		return mpDao.getAllMediosDePago();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public MedioDePago addMedioDePago(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {

		MedioDePago mp = new MedioDePago(nombre);

		int result = mpDao.addMedioDePago(mp);

		if (result == 1) {
			return mp;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public MedioDePago editMedioDePago(@FormParam("id") int id, @FormParam("nombre") String nombre,
			@Context HttpServletResponse servletResponse) {

		MedioDePago mp = mpDao.getMedioDePago(id);

		mp.setNombre(nombre);

		mpDao.update(mp);
		
		return mp;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public MedioDePago deleteMedioDePago(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		MedioDePago mp = mpDao.getMedioDePago(id);
		
		int result = mpDao.delete(mp);

		if (result == 1) {
			return mp;
		} else {
			return null;
		}
	}

}
