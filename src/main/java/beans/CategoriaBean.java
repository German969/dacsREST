package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.CategoriaDAO;
import entities.Categoria;

@ManagedBean(name = "categoriaBean", eager = true)
@SessionScoped
public class CategoriaBean {
	
	// ---------------- ATTRIBUTES -------------------- //

			private long id;
			private String nombre;

			private Categoria categoria;

			private List<Categoria> categorias;

			@EJB
			CategoriaDAO cdao;

			private boolean newCategoria;

			private boolean isFiltered;

			private String filterBy;

			private String filter;
			
			// ---------------- METHODS -------------------- //

			public String newCategoria() {

				newCategoria = true;

				categoria = new Categoria();

				return "categoias.xhtml";
			}

			public String getAll() {

				isFiltered = false;

				categorias = cdao.getAllCategorias();

				return "categorias.xhtml";
			}

			public List<Categoria> getAllCategorias() {

				if (isFiltered) {

					categorias = cdao.getByFilter(filter, filterBy);

				} else {

					categorias = cdao.getAllCategorias();

					if (newCategoria) {

						Categoria c = new Categoria();

						c.setCanEdit(true);

						c.setNewCategoria(true);

						if (categorias.isEmpty()) {
							c.setId(1);
						} else {

							c.setId(categorias.get(categorias.size() - 1).getId() + 1);

						}

						categorias.add(c);

					}

				}

				return categorias;

			}

			public String getByFilter() {

				isFiltered = true;

				return "categorias.xhtml";

			}

			public String editCategoria(Categoria c) {
				saveAllCategoriaFields(c);
				c.setCanEdit(true);
				return null;
			}

			public void saveAllCategoriaFields(Categoria c) {

				this.id = c.getId();
				this.nombre = c.getNombre();
				
			}

			public String notEditCategoria(Categoria c) {

				if (newCategoria) {
					categorias.remove(categorias.size() - 1);
				} else {

					c.setId(id);
					c.setNombre(nombre);
					
					c.setCanEdit(false);

					cdao.update(c);

				}

				return null;
			}

			public String saveCategorias() {

				for (Categoria c : categorias) {
					if (c.isCanEdit()) {

						if (c.isNewCategoria()) {

							Categoria cat = new Categoria(c.getNombre());

							cdao.addCategoria(cat);

							this.getAllCategorias();

						} else {

							this.update();

						}

					}
					;
					c.setCanEdit(false);
				}

				
				newCategoria = false;

				return null;
			}

						
			
			public void update() {

				cdao.update(categoria);

			}
			
			public void deleteCategoria(){
				cdao.delete(categoria);
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

			

			public Categoria getCategoria() {
				return categoria;
			}

			public void setCategoria(Categoria categoria) {
				this.categoria = categoria;
			}

			

			public boolean isNewCategoria() {
				return newCategoria;
			}

			public void setNewCategoria(boolean newCategoria) {
				this.newCategoria = newCategoria;
			}

			public boolean isFiltered() {
				return isFiltered;
			}

			public void setFiltered(boolean isFiltered) {
				this.isFiltered = isFiltered;
			}

			public List<Categoria> getCategorias() {
				return categorias;
			}

			public void setCategorias(List<Categoria> categorias) {
				this.categorias = categorias;
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
