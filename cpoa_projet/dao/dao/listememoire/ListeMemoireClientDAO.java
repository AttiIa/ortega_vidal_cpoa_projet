package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.ClientDAO;
import dao.metier.Client;
import dao.metier.Periodicite;

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
		
		this.donnees.add(new Client(1, "Vidal", "Thomas", "4", "Rue Paul Doumer", "10150", "Pont-Sainte-Marie", "France"));
	}
	
	@Override
	public boolean create(Client client){
		int i=0;

		client.setId_client(1);
		
		while (i<this.donnees.size()){
			client.setId_client(this.donnees.get(i).getId_client() + 1);
			i++;
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
		int i=1;
		boolean ok=false;
		
		while (i<=this.donnees.size()) {
			if(client.getId_client() == i) {
				this.donnees.set(i-1, client);
				ok=true;
			}
			i++;
		}
		return ok;
	}
	
	@Override
	public Client getById(int id) {
		Client client = new Client();
		boolean trouve=false;
		int i=0;
		while (trouve==false && i<this.donnees.size()){
			if (this.donnees.get(i).getId_client() == id) {
				client = this.donnees.get(i);
				trouve=true;
				}
			else
				i++;			
		}
		if(i>=this.donnees.size()){
			System.out.println("Pas de client avec cet id_client");
			client=null;}
		return client;
	}
	
	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}

}
