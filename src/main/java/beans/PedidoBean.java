package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.MedioDePagoDAO;
import dao.PedidoDAO;
import dao.UsuarioDAO;
import entities.MedioDePago;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;

@ManagedBean(name = "pedidoBean", eager = true)
@SessionScoped
public class PedidoBean {
	
	// ---------------- ATTRIBUTES -------------------- //

		private int id;
		private Date fecha;
		private String estado;
		private List<Producto> productos;
		private Usuario usuario;
		private Date fechaPago;
		private MedioDePago mediodepago;
		
		private Pedido pedido;
		
		private List<Pedido> pedidos;
		
		@EJB
		PedidoDAO pdao;
		
		@EJB
		UsuarioDAO udao;
		
		@EJB
		MedioDePagoDAO mpdao;
		
		private boolean newPedido;

		private boolean isFiltered;

		private String filterBy;

		private String filter;
		
		private String nameUsuario;
		
		private String nameMediodepago;
		
		private String stringFecha;
		
		private String stringFechaPago;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		// Date date = formatter.parse(dateInString);
		// formatter.format(date)
		
		// ---------------- METHODS -------------------- //
		
		public String newPedido(){

			newPedido = true;

			nameUsuario = new String("");

			nameMediodepago = new String("");

			pedido = new Pedido();

			return "pedidos.xhtml";
		}
		
		public String getAll() {

			isFiltered = false;

			pedidos = pdao.getAllPedidos();

			return "pedidos.xhtml";
		}
		
		public List<Pedido> getAllPedidos() throws ParseException {

			if (isFiltered) {

				pedidos = pdao.getByFilter(filter, filterBy);

			} else {

				pedidos = pdao.getAllPedidos();

				if (newPedido) {

					Pedido p = new Pedido();

					p.setCanEdit(true);

					p.setNewPedido(true);

					if (pedidos.isEmpty()) {
						p.setId(1);
					} else {

						p.setId(pedidos.get(pedidos.size() - 1).getId() + 1);

					}

					pedidos.add(p);

				}

			}

			return pedidos;

		}
		
		public String getByFilter() {

			isFiltered = true;

			return "pedidos.xhtml";

		}
		
		public String editPedido(Pedido pedido) {
			saveAllPedidoFields(pedido);
			nameUsuario = pedido.getUsuario().getNombre();
			nameMediodepago = pedido.getMediodepago().getNombre();
			pedido.setCanEdit(true);
			return null;
		}
		
		public void saveAllPedidoFields(Pedido pedido) {

			this.estado = pedido.getEstado();
			this.fecha = pedido.getFecha();
			this.fechaPago = pedido.getFechaPago();
			this.id = pedido.getId();
			this.mediodepago = pedido.getMediodepago();
			this.productos = pedido.getProductos();
			this.usuario = pedido.getUsuario();

		}
		
		public String notEditPedido(Pedido pedido) {

			if (newPedido) {
				pedidos.remove(pedidos.size() - 1);
			} else {

				pedido.setEstado(estado);
				pedido.setFecha(fecha);
				pedido.setFechaPago(fechaPago);
				pedido.setId(id);
				pedido.setMediodepago(mediodepago);
				pedido.setProductos(productos);
				pedido.setUsuario(usuario);
				
				pedido.setCanEdit(false);

				pdao.update(pedido);

			}

			return null;
		}
		
		public String savePedidos() throws ParseException {

			for (Pedido pedido : pedidos) {
				if (pedido.isCanEdit()) {

					if (pedido.isNewPedido()) {
						
						Date date = formatter.parse(stringFecha);
						
						pedido.setFecha(date);
						
						pedido.setStringFecha(stringFecha);
						
						pedido.setStringFecha(stringFecha);
						
						Date dateP = formatter.parse(stringFechaPago);
						
						pedido.setFechaPago(dateP);
						
						pedido.setStringFechaPago(stringFechaPago);

						usuario = udao.getUsuario(1);
						mediodepago = mpdao.getMedioDePagoByName(nameMediodepago);

						Pedido p = new Pedido(pedido.getFecha(),pedido.getEstado(),usuario,pedido.getFechaPago(),mediodepago);

						pdao.addPedido(p);

						this.getAllPedidos();

					} else {

						this.update();

					}

				}
				
				pedido.setCanEdit(false);
			}

			nameUsuario = new String("");

			nameMediodepago = new String("");

			newPedido = false;

			return null;
		}
		
		public List<Usuario> getUsuarios() {

			return udao.getAllUsuarios();

		}
		
		public List<MedioDePago> getMediosdepago() {

			return mpdao.getAllMediosDePago();

		}

		public void update() throws ParseException {

			pedido.setUsuario(udao.getUsuario(1));

			pedido.setMediodepago(mpdao.getMedioDePagoByName(nameMediodepago));
			
			pedido.setStringFecha(stringFecha);
			
			pedido.setFecha(formatter.parse(stringFecha));
			
			pedido.setStringFechaPago(stringFechaPago);
			
			pedido.setFechaPago(formatter.parse(stringFechaPago));;

			pdao.update(pedido);

		}
		
		public void deletePedido(){
			pdao.delete(pedido);
		}
		
		public String showFecha(Pedido pedido){
			return formatter.format(pedido.getFecha());
		}
		
		public String showFechaPago(Pedido pedido){
			return formatter.format(pedido.getFechaPago());
		}
		
		// ---------------- GETTERS AND SETTERS -------------------- //

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public List<Producto> getProductos() {
			return productos;
		}

		public void setProductos(List<Producto> productos) {
			this.productos = productos;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Date getFechaPago() {
			return fechaPago;
		}

		public void setFechaPago(Date fechaPago) {
			this.fechaPago = fechaPago;
		}

		public MedioDePago getMediodepago() {
			return mediodepago;
		}

		public void setMediodepago(MedioDePago mediodepago) {
			this.mediodepago = mediodepago;
		}

		public Pedido getPedido() {
			return pedido;
		}

		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}

		public List<Pedido> getPedidos() {
			return pedidos;
		}

		public void setPedidos(List<Pedido> pedidos) {
			this.pedidos = pedidos;
		}

		public boolean isNewPedido() {
			return newPedido;
		}

		public void setNewPedido(boolean newPedido) {
			this.newPedido = newPedido;
		}

		public boolean isFiltered() {
			return isFiltered;
		}

		public void setFiltered(boolean isFiltered) {
			this.isFiltered = isFiltered;
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

		public String getNameUsuario() {
			return nameUsuario;
		}

		public void setNameUsuario(String nameUsuario) {
			this.nameUsuario = nameUsuario;
		}

		public String getNameMediodepago() {
			return nameMediodepago;
		}

		public void setNameMediodepago(String nameMediodepago) {
			this.nameMediodepago = nameMediodepago;
		}

		public String getStringFecha() {
			return stringFecha;
		}

		public String getStringFechaPago() {
			return stringFechaPago;
		}

		public void setStringFecha(String stringFecha) {
			this.stringFecha = stringFecha;
		}

		public void setStringFechaPago(String stringFechaPago) {
			this.stringFechaPago = stringFechaPago;
		}

}
