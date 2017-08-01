package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Proveedor;

@ManagedBean(name = "productoBean", eager = true)
@SessionScoped
public class ProductoBean {
	
	// ---------------- ATTRIBUTES -------------------- //

		private int id;
		private String nombre;
		private String marca;
		private String descripcion;
		private float precio;
		private float comision;
		private int stock;
		private Categoria categoria;
		private Proveedor proveedor;

		private List<Pedido> pedidos;

		private Producto producto;

		private List<Producto> productos;

		@EJB
		ProductoDAO pdao;
		
		@EJB
		CategoriaDAO cdao;
		
		@EJB
		ProveedorDAO pvdao;

		private boolean newProducto;

		private boolean isFiltered;

		private String filterBy;

		private String filter;

		private String nameCategoria;

		private String nameProveedor;
		
		// ---------------- METHODS -------------------- //

		public String newProducto() {

			newProducto = true;

			nameCategoria = new String("");

			nameProveedor = new String("");

			producto = new Producto();

			return "productos.xhtml";
		}

		public String getAll() {

			isFiltered = false;

			productos = pdao.getAllProductos();

			return "productos.xhtml";
		}

		public List<Producto> getAllProductos() {

			if (isFiltered) {

				productos = pdao.getByFilter(filter, filterBy);

			} else {

				productos = pdao.getAllProductos();

				if (newProducto) {

					Producto p = new Producto();

					p.setCanEdit(true);

					p.setNewProducto(true);

					if (productos.isEmpty()) {
						p.setId(1);
					} else {

						p.setId(productos.get(productos.size() - 1).getId() + 1);

					}

					productos.add(p);

				}

			}

			return productos;

		}

		public String getByFilter() {

			isFiltered = true;

			return "productos.xhtml";

		}

		public String editProducto(Producto producto) {
			saveAllProductoFields(producto);
			nameCategoria = producto.getCategoria().getNombre();
			nameProveedor = producto.getProveedor().getNombre();
			producto.setCanEdit(true);
			return null;
		}

		public void saveAllProductoFields(Producto producto) {

			this.categoria = producto.getCategoria();
			this.comision = producto.getComision();
			this.descripcion = producto.getDescripcion();
			this.id = producto.getId();
			this.marca = producto.getMarca();
			this.nombre = producto.getNombre();
			this.pedidos = producto.getPedidos();
			this.precio = producto.getPrecio();
			this.proveedor = producto.getProveedor();
			this.stock = producto.getStock();

		}

		public String notEditProducto(Producto producto) {

			if (newProducto) {
				productos.remove(productos.size() - 1);
			} else {

				producto.setCategoria(categoria);
				producto.setComision(comision);
				producto.setDescripcion(descripcion);
				producto.setId(id);
				producto.setMarca(marca);
				producto.setNombre(nombre);
				producto.setPedidos(pedidos);
				producto.setPrecio(precio);
				producto.setProveedor(proveedor);
				producto.setStock(stock);

				producto.setCanEdit(false);

				pdao.update(producto);

			}

			return null;
		}

		public String saveProductos() {

			for (Producto producto : productos) {
				if (producto.isCanEdit()) {

					if (producto.isNewProducto()) {

						categoria = cdao.getCategoriaByName(nameCategoria);
						proveedor = pvdao.getProveedorByName(nameProveedor);

						Producto p = new Producto(producto.getNombre(), producto.getMarca(), producto.getDescripcion(),
								producto.getPrecio(), producto.getComision(), producto.getStock(), categoria, proveedor);

						pdao.addProducto(p);

						this.getAllProductos();

					} else {

						this.update();

					}

				}
				;
				producto.setCanEdit(false);
			}

			nameCategoria = new String("");

			nameProveedor = new String("");

			newProducto = false;

			return null;
		}

		public List<Categoria> getCategorias() {

			return cdao.getAllCategorias();

		}
		
		public List<Proveedor> getProveedores() {

			return pvdao.getAllProveedores();

		}

		public void update() {

			producto.setCategoria(cdao.getCategoriaByName(nameCategoria));

			producto.setProveedor(pvdao.getProveedorByName(nameProveedor));

			pdao.update(producto);

		}
		
		public void deleteProducto(){
			pdao.delete(producto);
		}
		
		// ---------------- GETTERS AND SETTERS -------------------- //

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(float precio) {
			this.precio = precio;
		}

		public double getComision() {
			return comision;
		}

		public void setComision(float comision) {
			this.comision = comision;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public Proveedor getProveedor() {
			return proveedor;
		}

		public void setProveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}

		public Producto getProducto() {
			return producto;
		}

		public void setProducto(Producto producto) {
			this.producto = producto;
		}

		public List<Pedido> getPedidos() {
			return pedidos;
		}

		public void setPedidos(List<Pedido> pedidos) {
			this.pedidos = pedidos;
		}

		public boolean isNewProducto() {
			return newProducto;
		}

		public void setNewProducto(boolean newProducto) {
			this.newProducto = newProducto;
		}

		public boolean isFiltered() {
			return isFiltered;
		}

		public void setFiltered(boolean isFiltered) {
			this.isFiltered = isFiltered;
		}

		public List<Producto> getProductos() {
			return productos;
		}

		public void setProductos(List<Producto> productos) {
			this.productos = productos;
		}

		public String getFilterBy() {
			return filterBy;
		}

		public void setFilterBy(String filterBy) {
			this.filterBy = filterBy;
		}

		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}

		public String getNameCategoria() {
			return nameCategoria;
		}

		public void setNameCategoria(String nameCategoria) {
			this.nameCategoria = nameCategoria;
		}

		public String getNameProveedor() {
			return nameProveedor;
		}

		public void setNameProveedor(String nameProveedor) {
			this.nameProveedor = nameProveedor;
		}

}
