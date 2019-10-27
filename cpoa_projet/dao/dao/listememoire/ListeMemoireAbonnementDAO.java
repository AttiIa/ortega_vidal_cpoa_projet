package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.AbonnementDAO;
import dao.metier.Abonnement;

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
		boolean ok = this.donnees.add(abonnement);
		
		return ok;
	}
	
	@Override
	public boolean delete(Abonnement abonnement) {

		Abonnement supprime;
		
		int idx = this.donnees.indexOf(abonnement);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} 
		else {
			supprime = this.donnees.remove(idx);
		}
		
		return abonnement.equals(supprime);
	}
	
	@Override
	public boolean update(Abonnement abonnement){
		
		int idx = this.donnees.indexOf(abonnement);
		
		if (idx == -1){
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} 
		else{			
			this.donnees.set(idx, abonnement);
		}
		
		return true;
	}
	
	@Override
	public Abonnement getById(int id) {
		return null;
	}
	
	 @Override
		public Abonnement getById(int id_client, int id_revue) {
			Abonnement abo = new Abonnement();
			boolean trouve = false;
			int i = 0;
			while (trouve == false && i < this.donnees.size()) {
				if (this.donnees.get(i).getId_client() == id_client && this.donnees.get(i).getId_revue() == id_revue) {
					abo = this.donnees.get(i);
					trouve = true;
				} else
					i++;
			}
			if (!trouve) {
				System.out.println("Pas d'abonnement avec ces id");
				abo = null;
			}
			return abo;
		}
	
	@Override
	public ArrayList<Abonnement> findAll() {
		return (ArrayList<Abonnement>) this.donnees;
	}
}
