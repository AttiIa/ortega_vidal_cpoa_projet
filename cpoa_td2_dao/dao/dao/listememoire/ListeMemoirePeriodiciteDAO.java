package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.PeriodiciteDAO;
import dao.metier.Periodicite;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {

	private static ListeMemoirePeriodiciteDAO instance;

	private List<Periodicite> donnees;


	public static ListeMemoirePeriodiciteDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoirePeriodiciteDAO();
		}

		return instance;
	}

	private ListeMemoirePeriodiciteDAO() {

		this.donnees = new ArrayList<Periodicite>();

		this.donnees.add(new Periodicite(1, "Mensuel"));
		this.donnees.add(new Periodicite(2, "Quotidien"));
	}


	@Override
	public boolean create(Periodicite periodicite) {

		periodicite.setId_periodicite(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(periodicite)) {

			periodicite.setId_periodicite(periodicite.getId_periodicite() + 1);
		}
		boolean ok = this.donnees.add(periodicite);
		
		return ok;
	}

	@Override
	public boolean update(Periodicite periodicite) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(periodicite);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, periodicite);
		}
		
		return true;
	}

	@Override
	public boolean delete(Periodicite periodicite) {

		Periodicite supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(periodicite);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return periodicite.equals(supprime);
	}

	@Override
	public Periodicite getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Periodicite(id, "test"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Periodicite> findAll() {
		return (ArrayList<Periodicite>) this.donnees;
	}
}
