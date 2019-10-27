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
		
		this.donnees.add(new Revue(1, "Le monde", "journal du soir", 1.5, "lemonde.jpg", 0));
		this.donnees.add(new Revue(2, "Charlie Hebdo", "journal satirique", 2, "charliehebdo.jpg", 0));
		this.donnees.add(new Revue(3, "Spirou", "Humour et bandes dessinées, retrouvez tous les héros des éditions Dupuis", 2.2, "spirou.jpg", 0));         
		this.donnees.add(new Revue(4, "Programmez", "Revue d'informatique orientée vers le développement d'applications", 3.5, "programmez.jpg", 0));
	}
	
	@Override
	public boolean create(Revue revue){
		int i=1;

		revue.setId_revue(i);
		
		while (i<=this.donnees.size()){
			revue.setId_revue(revue.getId_revue() + 1);
			i++;
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
		Revue revue = new Revue();
		boolean trouve=false;
		int i=0;
		while (trouve==false && i<this.donnees.size()){
			if (this.donnees.get(i).getId_revue() == id) {
				revue = this.donnees.get(i);
				trouve=true;
				}
			else
				i++;			
		}
		if(i>=this.donnees.size()){
			System.out.println("Pas de revue avec cet id_revue");
			revue=null;}
		return revue;
	}
	
	@Override
	public ArrayList<Revue> findAll() {
		return (ArrayList<Revue>) this.donnees;
	}

}
