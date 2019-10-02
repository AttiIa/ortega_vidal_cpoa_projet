package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.RevueDAO;
import dao.metier.Revue;

public class ListeMemoireRevueDAO implements RevueDAO{
	private static ListeMemoireRevueDAO instance;
	private List<Revue> donnees;
	
	public static ListeMemoireRevueDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireRevueDAO();
		}
		return instance;
	}
	
	private ListeMemoireRevueDAO(){

		this.donnees = new ArrayList<Revue>();
	}
	
	@Override
	public boolean create(Revue revue){

		revue.setId_revue(3);
		
		while (this.donnees.contains(revue)){
			revue.setId_periodicite(revue.getId_revue() + 1);
		}		
		boolean ok = this.donnees.add(revue);
		return ok;
	}
	
	@Override
	public boolean delete(Revue revue) {

		Revue supprime;		
		int idx = this.donnees.indexOf(revue);
		
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} 
		else {
			supprime = this.donnees.remove(idx);
		}
		
		return revue.equals(supprime);
	}
	
	@Override
	public boolean update(Revue revue){
		
		int idx = this.donnees.indexOf(revue);
		
		if (idx == -1){
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} 
		else{			
			this.donnees.set(idx, revue);
		}
		
		return true;
	}
	
	@Override
	public Revue getById(int id) {
		int idx = this.donnees.indexOf(new Revue(id, "titre", "description", 1, "visuel", 3));
		
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
		} 
		else{
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public ArrayList<Revue> findAll() {
		return (ArrayList<Revue>) this.donnees;
	}

}
