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

import dao.CategoriaDAO;
import entities.Categoria;

@Path("/categorias")
@Stateless
@LocalBean
public class CategoriaWS {
	
	@EJB
	CategoriaDAO categoriaDao;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria getCategoria(@PathParam("id") int id) {
		return categoriaDao.getCategoria(id);
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria getCategoria(@PathParam("name") String name) {
		return categoriaDao.getCategoriaByName(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> getCategorias() {
		return categoriaDao.getAllCategorias();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria addCategoria(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {

		Categoria c = new Categoria(nombre);

		int result = categoriaDao.addCategoria(c);

		if (result == 1) {
			return c;
		} else {
			return null;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria editCategoria(@FormParam("id") int id, @FormParam("nombre") String nombre,
			@Context HttpServletResponse servletResponse) {

		Categoria c = categoriaDao.getCategoria(id);

		c.setNombre(nombre);

		categoriaDao.update(c);
		
		return c;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria deleteCategoria(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Categoria c = categoriaDao.getCategoria(id);
		
		int result = categoriaDao.delete(c);

		if (result == 1) {
			return c;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/delete2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria deleteByNameCategoria(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {
		
		Categoria c = categoriaDao.getCategoriaByName(nombre);
		
		int result = categoriaDao.delete(c);

		if (result == 1) {
			return c;
		} else {
			return null;
		}
	}

}
