package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.ClientDAO;
import dao.metier.Client;

public class ListeMemoireClientDAO implements ClientDAO{
	private static ListeMemoireClientDAO instance;
	private List<Client> donnees;


	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}
	
	private ListeMemoireClientDAO(){

		this.donnees = new ArrayList<Client>();
	}
	
	@Override
	public boolean create(Client client){

		client.setId_client(3);
		
		while (this.donnees.contains(client)){
			client.setId_client(client.getId_client() + 1);
		}		
		boolean ok = this.donnees.add(client);
		return ok;
	}
	
	@Override
	public boolean delete(Client client) {

		Client supprime;		
		int idx = this.donnees.indexOf(client);
		
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} 
		else {
			supprime = this.donnees.remove(idx);
		}
		
		return client.equals(supprime);
	}
	
	@Override
	public boolean update(Client client){
		
		int idx = this.donnees.indexOf(client);
		
		if (idx == -1){
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} 
		else{			
			this.donnees.set(idx, client);
		}
		
		return true;
	}
	
	@Override
	public Client getById(int id) {
		int idx = this.donnees.indexOf(new Client(id, "nom", "prenom", "9", "wad_billy", "57000", "Metz", "France"));
		
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
		} 
		else{
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}

}
