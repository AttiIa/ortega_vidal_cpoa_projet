package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.AbonnementDAO;
import dao.metier.Abonnement;
import dao.metier.Periodicite;

public class ListeMemoireAbonnementDAO implements AbonnementDAO{
	private static ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;
	
	public static ListeMemoireAbonnementDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireAbonnementDAO();
		}

		return instance;
	}
	
	private ListeMemoireAbonnementDAO() {

		this.donnees = new ArrayList<Abonnement>();

	}

	@Override
	public boolean create(Abonnement abonnement) {

		abonnement.setId_client(id_client);(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(periodicite)) {

			periodicite.setId_periodicite(periodicite.getId_periodicite() + 1);
		}
		boolean ok = this.donnees.add(periodicite);
		
		return ok;
	}
}
