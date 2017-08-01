package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.RolDAO;
import entities.Rol;

@ManagedBean(name = "rolBean", eager = true)
@SessionScoped
public class RolBean {
	
	// ---------------- ATTRIBUTES -------------------- //

	private int id;
	private String nombre;

	private Rol rol;

	private List<Rol> roles;

	@EJB
	RolDAO rdao;

	private boolean newRol;

	private boolean isFiltered;

	private String filterBy;

	private String filter;
	
	// ---------------- METHODS -------------------- //

	public String newRol() {

		newRol = true;

		rol = new Rol();

		return "categoias.xhtml";
	}

	public String getAll() {

		isFiltered = false;

		roles = rdao.getAllRoles();

		return "roles.xhtml";
	}

	public List<Rol> getAllRols() {

		if (isFiltered) {

			roles = rdao.getByFilter(filter, filterBy);

		} else {

			roles = rdao.getAllRoles();

			if (newRol) {

				Rol r = new Rol();

				r.setCanEdit(true);

				r.setNewRol(true);

				if (roles.isEmpty()) {
					r.setId(1);
				} else {

					r.setId(roles.get(roles.size() - 1).getId() + 1);

				}

				roles.add(r);

			}

		}

		return roles;

	}

	public String getByFilter() {

		isFiltered = true;

		return "roles.xhtml";

	}

	public String editRol(Rol r) {
		saveAllRolFields(r);
		r.setCanEdit(true);
		return null;
	}

	public void saveAllRolFields(Rol r) {

		this.id = r.getId();
		this.nombre = r.getNombre();
		
	}

	public String notEditRol(Rol r) {

		if (newRol) {
			roles.remove(roles.size() - 1);
		} else {

			r.setId(id);
			r.setNombre(nombre);
			
			r.setCanEdit(false);

			rdao.update(r);

		}

		return null;
	}

	public String saveRols() {

		for (Rol r : roles) {
			if (r.isCanEdit()) {

				if (r.isNewRol()) {

					Rol rr = new Rol(r.getNombre());

					rdao.addRol(rr);

					this.getAllRols();

				} else {

					this.update();

				}

			}
			;
			r.setCanEdit(false);
		}

		
		newRol = false;

		return null;
	}

				
	
	public void update() {

		rdao.update(rol);

	}
	
	public void deleteRol(){
		rdao.delete(rol);
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

	

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	

	public boolean isNewRol() {
		return newRol;
	}

	public void setNewRol(boolean newRol) {
		this.newRol = newRol;
	}

	public boolean isFiltered() {
		return isFiltered;
	}

	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	public List<Rol> getRols() {
		return roles;
	}

	public void setRols(List<Rol> roles) {
		this.roles = roles;
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
