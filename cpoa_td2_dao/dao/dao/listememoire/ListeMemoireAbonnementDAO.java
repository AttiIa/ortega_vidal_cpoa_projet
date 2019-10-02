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

		abonnement.setId_client(3);
		while (this.donnees.contains(abonnement)) {

			abonnement.setId_client(abonnement.getId_client() + 1);
		}
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
		int idx = this.donnees.indexOf(new Abonnement(id, 3, "1999-09-18", "2000-11-21"));
		
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
		} 
		else{
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public ArrayList<Abonnement> findAll() {
		return (ArrayList<Abonnement>) this.donnees;
	}
}
