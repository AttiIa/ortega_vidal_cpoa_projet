package dao.listememoire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		
		this.donnees.add(new Abonnement(1, 2, "2019-09-18", "2019-11-21"));

	}

	@Override
	public boolean create(Abonnement abonnement) {
		int i=0;
		boolean ok=true;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date_d = LocalDate.parse(abonnement.getDate_debut(), formatter);
		LocalDate date_f = LocalDate.parse(abonnement.getDate_fin(), formatter);		
		int comp=date_d.compareTo(date_f);
		
		if(comp>0) ok=false;		
		else while(i<this.donnees.size()) {
			if(this.donnees.get(i).getId_client() == abonnement.getId_client() &&
					this.donnees.get(i).getId_revue() == abonnement.getId_revue()) ok=false;
				i++;
		}
		
		if(ok) this.donnees.add(abonnement);
		
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
		int i=1;
		int j=1;
		boolean ok=false;
		
		while (i<=this.donnees.size()) {
			while(j<=this.donnees.size()) {
				if(abonnement.getId_client() == i && abonnement.getId_revue() == j) {
					this.donnees.set(i-1, abonnement);
					ok=true;
				}
				j++;
			}			
			i++;
		}
		return ok;
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
