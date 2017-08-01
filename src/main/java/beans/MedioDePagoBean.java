package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.MedioDePagoDAO;
import entities.MedioDePago;

@ManagedBean(name = "mediodepagoBean", eager = true)
@SessionScoped
public class MedioDePagoBean {
	
	// ---------------- ATTRIBUTES -------------------- //

				private int id;
				private String nombre;

				private MedioDePago mp;

				private List<MedioDePago> mps;

				@EJB
				MedioDePagoDAO mpdao;

				private boolean newMedioDePago;

				private boolean isFiltered;

				private String filterBy;

				private String filter;
				
				// ---------------- METHODS -------------------- //

				public String newMedioDePago() {

					newMedioDePago = true;

					mp = new MedioDePago();

					return "mediosdepago.xhtml";
				}

				public String getAll() {

					isFiltered = false;

					mps = mpdao.getAllMediosDePago();

					return "mediosdepago.xhtml";
				}

				public List<MedioDePago> getAllMediosDePago() {

					if (isFiltered) {

						mps = mpdao.getByFilter(filter, filterBy);

					} else {

						mps = mpdao.getAllMediosDePago();

						if (newMedioDePago) {

							MedioDePago mdp = new MedioDePago();

							mdp.setCanEdit(true);

							mdp.setNewMedioDePago(true);

							if (mps.isEmpty()) {
								mdp.setId(1);
							} else {

								mdp.setId(mps.get(mps.size() - 1).getId() + 1);

							}

							mps.add(mdp);

						}

					}

					return mps;

				}

				public String getByFilter() {

					isFiltered = true;

					return "mediosdepago.xhtml";

				}

				public String editMedioDePago(MedioDePago mdp) {
					saveAllMedioDePagoFields(mdp);
					mdp.setCanEdit(true);
					return null;
				}

				public void saveAllMedioDePagoFields(MedioDePago mdp) {

					this.id = mdp.getId();
					this.nombre = mdp.getNombre();
					
				}

				public String notEditMedioDePago(MedioDePago mdp) {

					if (newMedioDePago) {
						mps.remove(mps.size() - 1);
					} else {

						mdp.setId(id);
						mdp.setNombre(nombre);
						
						mdp.setCanEdit(false);

						mpdao.update(mdp);

					}

					return null;
				}

				public String saveMediosDePago() {

					for (MedioDePago mdp : mps) {
						if (mdp.isCanEdit()) {

							if (mdp.isNewMedioDePago()) {

								MedioDePago medpa = new MedioDePago(mdp.getNombre());

								mpdao.addMedioDePago(medpa);

								this.getAllMediosDePago();

							} else {

								this.update();

							}

						}
						;
						mdp.setCanEdit(false);
					}

					
					newMedioDePago = false;

					return null;
				}

							
				
				public void update() {

					mpdao.update(mp);

				}
				
				public void deleteMedioDePago(){
					mpdao.delete(mp);
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

				

				public MedioDePago getMedioDePago() {
					return mp;
				}

				public void setMedioDePago(MedioDePago mdp) {
					this.mp = mdp;
				}

				

				public boolean isNewMedioDePago() {
					return newMedioDePago;
				}

				public void setNewMedioDePago(boolean newMedioDePago) {
					this.newMedioDePago = newMedioDePago;
				}

				public boolean isFiltered() {
					return isFiltered;
				}

				public void setFiltered(boolean isFiltered) {
					this.isFiltered = isFiltered;
				}

				public List<MedioDePago> getMediosDePago() {
					return mps;
				}

				public void setMediosDePago(List<MedioDePago> mps) {
					this.mps = mps;
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
 