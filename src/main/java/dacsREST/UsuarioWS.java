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
import dao.UsuarioDAO;
import entities.Rol;
import entities.Usuario;

@Path("/usuarios")
@Stateless
@LocalBean
public class UsuarioWS {

	@EJB
    private UsuarioDAO usuarioDao;
	
	@EJB
    private RolDAO rolDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("id") int id) {
        return usuarioDao.getUsuario(id);
    }
	
	@GET
    @Path("/name/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioByName(@PathParam("nombre") String nombre) {
        return usuarioDao.getUsuarioByName(nombre);
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		return usuarioDao.getAllUsuarios();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
    public Usuario addUsuario(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido,
			@FormParam("cuit") String cuit, @FormParam("email") String email, @FormParam("user") String user,
			@FormParam("password") String password, @FormParam("telefono") String telefono,
			@FormParam("pais") String pais, @FormParam("provincia") String provincia,
			@FormParam("localidad") String localidad, @FormParam("codigoPostal") String codigoPostal,
			@FormParam("direccion") String direccion, @FormParam("rol") String nrol,
			@Context HttpServletResponse servletResponse) {
		
		Rol rol = rolDao.getRolByName(nrol);
		
		Usuario usuario = new Usuario(nombre, apellido, cuit, email, user, password, telefono, pais, provincia,
				localidad, codigoPostal, direccion, rol);
        
		usuarioDao.addUsuario(usuario);
		
		return usuario;
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario editUsuario(@FormParam("id") int id, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido,
			@FormParam("cuit") String cuit, @FormParam("email") String email, @FormParam("user") String user,
			@FormParam("password") String password, @FormParam("telefono") String telefono,
			@FormParam("pais") String pais, @FormParam("provincia") String provincia,
			@FormParam("localidad") String localidad, @FormParam("codigoPostal") String codigoPostal,
			@FormParam("direccion") String direccion, @FormParam("rol") String nrol,
			@Context HttpServletResponse servletResponse) {

		Usuario u = usuarioDao.getUsuario(id);
		
		Rol r =rolDao.getRolByName(nrol);
		
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setCuit(cuit);
		u.setEmail(email);
		u.setUser(user);
		u.setPais(pais);
		u.setProvincia(provincia);
		u.setLocalidad(localidad);
		u.setCodigoPostal(codigoPostal);
		u.setDireccion(direccion);
		u.setRol(r);

		usuarioDao.update(u);
		
		return u;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario deleteUsuario(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Usuario u = usuarioDao.getUsuario(id);
		
		int result = usuarioDao.delete(u);

		if (result == 1) {
			return u;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/delete2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario deleteUsuarioByName(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {
		
		Usuario u = usuarioDao.getUsuarioByName(nombre);
		
		int result = usuarioDao.delete(u);

		if (result == 1) {
			return u;
		} else {
			return null;
		}
	}
	
}
