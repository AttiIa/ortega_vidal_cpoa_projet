package dao.mysql; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.interfaces.ClientDAO;
import dao.metier.Client;

public class MySQLClientDAO implements ClientDAO{
	private static MySQLClientDAO instance;
	private MySQLClientDAO() {}
	public static MySQLClientDAO getInstance() {
	if (instance==null) {
	instance = new MySQLClientDAO();
	}
	return instance;
	}
	
	@Override
	public boolean create(Client client) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("insert into Client (id_client, nom, prenom, no_rue, voie, code_postal, ville, pays)" + "VALUES (?, ?, ?, ? , ?, ?, ?, ?)");
			requete.setInt(1, client.getId_client());
			requete.setString(2, client.getNom());
			requete.setString(3, client.getPrenom());
			requete.setString(4, client.getNo_rue());
			requete.setString(5, client.getVoie());
			requete.setString(6, client.getCode_postal());
			requete.setString(7, client.getVille());
			requete.setString(8, client.getPays());
			
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			
			ok = true;			
		}
		catch(SQLException sqle){
			System.out.println("Pb Client.create " + sqle.getMessage());
			ok = false;
		}
		return ok;	
	}
	
	@Override
	public boolean delete(Client client) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client = ?");
			requete.setInt(1, client.getId_client());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Client.delete " + sqle.getMessage());
			ok = false;
		}
		return ok;		
	}
	
	@Override
	public boolean update(Client client) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("update Client set nom = ?, prenom = ?, no_rue = ?, voie = ?, code_postal = ?, ville = ?, pays = ?  where id_client = ?");
			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setString(3, client.getNo_rue());
			requete.setString(4, client.getVoie());
			requete.setString(5, client.getCode_postal());
			requete.setString(6, client.getVille());
			requete.setString(7, client.getPays());
			requete.setInt(8, client.getId_client());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Client.update " + sqle.getMessage());
			ok = false;
		}
		return ok;		
	}
	
	@Override
	public Client getById(int id) {
		Client client = null;
		
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Client where id_client = ?");
			requete.setInt(1, id);
			
			ResultSet res = requete.executeQuery();
			
			res.next();
			
			client = new Client(id, res.getString("nom"), res.getString("prenom"), res.getString("no_rue"), res.getString("voie"), res.getString("code_postal"), res.getString("ville"), res.getString("pays"));
			
			if (requete != null)
				requete.close();
			
			if (res != null)
				res.close();			
		}
		catch(SQLException sqle){
			System.out.println("Pb Client.getById " + sqle.getMessage());
		}
		
		return client;
		
		
	}
	@Override
	public ArrayList<Client> findAll() {
		ArrayList<Client> listeClient = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Client");
			
			ResultSet res = requete.executeQuery();
	
			while (res.next()) {
				listeClient.add(new Client(
						res.getInt("id_client"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getString("no_rue"),
						res.getString("voie"),
						res.getString("code_postal"),
						res.getString("ville"),
						res.getString("pays")
						));
			}
				
				if (requete != null)
					requete.close();
				
				if (res != null)
					res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.findAll " + sqle.getMessage());
		}
		
		return listeClient;	
	}

}
