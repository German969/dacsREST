package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ProveedorDAO;
import entities.Proveedor;

@ManagedBean(name = "proveedorBean", eager = true)
@SessionScoped
public class ProveedorBean {
	
	// ---------------- ATTRIBUTES -------------------- //

				private long id;
				private String nombre;

				private Proveedor proveedor;

				private List<Proveedor> proveedores;

				@EJB
				ProveedorDAO pdao;

				private boolean newProveedor;

				private boolean isFiltered;

				private String filterBy;

				private String filter;
				
				// ---------------- METHODS -------------------- //

				public String newProveedor() {

					newProveedor = true;

					proveedor = new Proveedor();

					return "categoias.xhtml";
				}

				public String getAll() {

					isFiltered = false;

					proveedores = pdao.getAllProveedores();

					return "proveedores.xhtml";
				}

				public List<Proveedor> getAllProveedors() {

					if (isFiltered) {

						proveedores = pdao.getByFilter(filter, filterBy);

					} else {

						proveedores = pdao.getAllProveedores();

						if (newProveedor) {

							Proveedor p = new Proveedor();

							p.setCanEdit(true);

							p.setNewProveedor(true);

							if (proveedores.isEmpty()) {
								p.setId(1);
							} else {

								p.setId(proveedores.get(proveedores.size() - 1).getId() + 1);

							}

							proveedores.add(p);

						}

					}

					return proveedores;

				}

				public String getByFilter() {

					isFiltered = true;

					return "proveedores.xhtml";

				}

				public String editProveedor(Proveedor p) {
					saveAllProveedorFields(p);
					p.setCanEdit(true);
					return null;
				}

				public void saveAllProveedorFields(Proveedor p) {

					this.id = p.getId();
					this.nombre = p.getNombre();
					
				}

				public String notEditProveedor(Proveedor p) {

					if (newProveedor) {
						proveedores.remove(proveedores.size() - 1);
					} else {

						p.setId(id);
						p.setNombre(nombre);
						
						p.setCanEdit(false);

						pdao.update(p);

					}

					return null;
				}

				public String saveProveedors() {

					for (Proveedor p : proveedores) {
						if (p.isCanEdit()) {

							if (p.isNewProveedor()) {

								Proveedor pro = new Proveedor(p.getNombre());

								pdao.addProveedor(pro);

								this.getAllProveedors();

							} else {

								this.update();

							}

						}
						;
						p.setCanEdit(false);
					}

					
					newProveedor = false;

					return null;
				}

							
				
				public void update() {

					pdao.update(proveedor);

				}
				
				public void deleteProveedor(){
					pdao.delete(proveedor);
				}
				
				// ---------------- GETTERS AND SETTERS -------------------- //

				public long getId() {
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

				

				public Proveedor getProveedor() {
					return proveedor;
				}

				public void setProveedor(Proveedor proveedor) {
					this.proveedor = proveedor;
				}

				

				public boolean isNewProveedor() {
					return newProveedor;
				}

				public void setNewProveedor(boolean newProveedor) {
					this.newProveedor = newProveedor;
				}

				public boolean isFiltered() {
					return isFiltered;
				}

				public void setFiltered(boolean isFiltered) {
					this.isFiltered = isFiltered;
				}

				public List<Proveedor> getProveedors() {
					return proveedores;
				}

				public void setProveedors(List<Proveedor> proveedores) {
					this.proveedores = proveedores;
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

}
