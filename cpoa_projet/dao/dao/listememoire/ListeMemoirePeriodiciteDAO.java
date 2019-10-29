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
	
	private ListeMemoirePeriodiciteDAO(){

		this.donnees = new ArrayList<Periodicite>();
		
		this.donnees.add(new Periodicite(1, "Quotidien"));
		this.donnees.add(new Periodicite(2, "Hebdomadaire"));
		this.donnees.add(new Periodicite(3, "Mensuel"));
		this.donnees.add(new Periodicite(4, "Bimestriel"));
		this.donnees.add(new Periodicite(5, "Trimestriel"));
	}


	@Override
	public boolean create(Periodicite periodicite){
		int i=1;

		periodicite.setId_periodicite(i);
		
		while(i<=this.donnees.size()){
			periodicite.setId_periodicite(this.donnees.get(i).getId_periodicite() + 1);
			i++;
		}
		boolean ok = this.donnees.add(periodicite);
		return ok;
	}
	
	@Override
	public boolean delete(Periodicite periodicite) {

		Periodicite supprime;		
		int idx = this.donnees.indexOf(periodicite);
		
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} 
		else {
			supprime = this.donnees.remove(idx);
		}
		
		return periodicite.equals(supprime);
	}

	@Override
	public boolean update(Periodicite periodicite){
		int i=1;
		boolean ok=false;
		
		while (i<=this.donnees.size()) {
			if(periodicite.getId_periodicite() == i) {
				this.donnees.set(i-1, periodicite);
				ok=true;
			}
			i++;
		}
		return ok;
	}

	  @Override
		public Periodicite getById(int id) {
			Periodicite periodicite = new Periodicite();
			boolean trouve=false;
			int i=0;
			while (trouve==false && i<this.donnees.size()){
				if (this.donnees.get(i).getId_periodicite() == id) {
					periodicite = this.donnees.get(i);
					trouve=true;
					}
				else
					i++;			
			}
			if(i>=this.donnees.size()){
				System.out.println("Pas de periodicite avec cet id_periodicite");
				periodicite=null;}
			return periodicite;
		}

	@Override
	public ArrayList<Periodicite> findAll() {
		return (ArrayList<Periodicite>) this.donnees;
	}
}
