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
import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Categoria;
import entities.Producto;
import entities.Proveedor;

@Path("/productos")
@Stateless
@LocalBean
public class ProductoWS {
	
	@EJB
	private ProductoDAO productoDao;
	
	@EJB
	private ProveedorDAO proveedorDao;
	
	@EJB
	private CategoriaDAO categoriaDao;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Producto getProducto(@PathParam("id") int id) {
		return productoDao.getProducto(id);
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Producto getProducto(@PathParam("name") String name) {
		return productoDao.getProductoByName(name);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		return productoDao.getAllProductos();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto addProducto(@FormParam("nombre") String nombre, @FormParam("marca") String marca, @FormParam("descripcion") String descripcion, @FormParam("precio") float precio, @FormParam("comision") float comision, @FormParam("stock") int stock, @FormParam("categoria") String ncategoria, @FormParam("proveedor") String nproveedor, @Context HttpServletResponse servletResponse) {

		Categoria categoria = categoriaDao.getCategoriaByName(ncategoria);
		
		Proveedor proveedor = proveedorDao.getProveedorByName(nproveedor);
		
		Producto producto = new Producto(nombre,marca,descripcion,precio,comision,stock,categoria,proveedor);

		int result = productoDao.addProducto(producto);

		if (result == 1) {
			return producto;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto editProducto(@FormParam("id") int id, @FormParam("nombre") String nombre, @FormParam("marca") String marca, @FormParam("descripcion") String descripcion, @FormParam("precio") float precio, @FormParam("comision") float comision, @FormParam("stock") int stock, @FormParam("categoria") String ncategoria, @FormParam("proveedor") String nproveedor,
			@Context HttpServletResponse servletResponse) {

		Categoria categoria = categoriaDao.getCategoriaByName(ncategoria);
		
		Proveedor proveedor = proveedorDao.getProveedorByName(nproveedor);
		
		Producto producto = productoDao.getProducto(id);
		
		producto.setNombre(nombre);
		producto.setMarca(marca);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setComision(comision);
		producto.setStock(stock);
		producto.setCategoria(categoria);
		producto.setProveedor(proveedor);

		productoDao.update(producto);
		
		return producto;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto deleteProducto(@FormParam("id") int id, @Context HttpServletResponse servletResponse) {
		
		Producto producto = productoDao.getProducto(id);
		
		int result = productoDao.delete(producto);

		if (result == 1) {
			return producto;
		} else {
			return null;
		}
	}
	
	@POST
	@Path("/delete2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto deleteProductoByName(@FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) {
		
		Producto producto = productoDao.getProductoByName(nombre);
		
		int result = productoDao.delete(producto);

		if (result == 1) {
			return producto;
		} else {
			return null;
		}
	}

}
