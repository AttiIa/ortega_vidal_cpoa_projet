package dao.mysql;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.interfaces.AbonnementDAO;
import dao.metier.Abonnement;

public class MySQLAbonnementDAO implements AbonnementDAO{
	private static MySQLAbonnementDAO instance;
	private MySQLAbonnementDAO() {}
	
	public static MySQLAbonnementDAO getInstance() {
		if (instance==null) {
			instance = new MySQLAbonnementDAO();
		}
		return instance;
	}
	
	@Override
	public boolean create(Abonnement abonnement) throws Exception{
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();
			Date d_deb = Date.valueOf(abonnement.getDate_debut());
			Date d_fin = Date.valueOf(abonnement.getDate_fin());
			
			int comp=d_deb.compareTo(d_fin);
			if(comp<0) {
			PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (id_client, id_revue, date_debut, date_fin)" + "VALUES (?, ?, ?, ?)");
			requete.setInt(1, abonnement.getId_client());
			requete.setInt(2, abonnement.getId_revue());
			requete.setDate(3, d_deb);
			requete.setDate(4, d_fin);
			
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;
			}
			else {
				System.out.println("La date de debut est superieur a la date de fin, Veuillez recommencer");
				ok = false;
				throw new Exception("La date de debut est superieur a la date de fin, Veuillez recommencer");
			}
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.create " + sqle.getMessage());
			ok = false;
			throw new Exception("Pb Abonnement.create " + sqle.getMessage());
			
		}
		return ok;
	}
	
	@Override
	public boolean delete(Abonnement abonnement) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_client = ? and id_revue = ?");
			requete.setInt(1, abonnement.getId_client());
			requete.setInt(2, abonnement.getId_revue());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.delete " + sqle.getMessage());
			ok = false;
		}
		return ok;		
	}
	
	@Override
	public boolean update(Abonnement abonnement){
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			
			Date d_deb = Date.valueOf(abonnement.getDate_debut());
			Date d_fin = Date.valueOf(abonnement.getDate_fin());
			
			int comp=d_deb.compareTo(d_fin);
			if(comp<0) {
			PreparedStatement requete = laConnexion.prepareStatement("update Abonnement set date_debut = ?, date_fin = ? where id_client = ? and id_revue = ?");
						
		
			requete.setDate(1, d_deb);
			requete.setDate(2, d_fin);
			requete.setInt(3, abonnement.getId_client());
			requete.setInt(4, abonnement.getId_revue());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			nbLignes = true;
			}
			else {
				System.out.println("La date de debut est supperieur a la date de fin, Veuillez recommencer");
				nbLignes = false;
			}
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.update " + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;		
	}
	
	@Override
	public Abonnement getById(int id) {
		return null;
	}
	
	public ArrayList<Abonnement> getById_client(int id_client) {
		ArrayList<Abonnement> abonnement = new ArrayList<>();
		
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Abonnement where id_client = ?");
			requete.setInt(1, id_client);
			
			ResultSet res = requete.executeQuery();
			
			while (res.next()) {
				abonnement.add(new Abonnement(id_client, res.getInt("id_revue"), res.getString("date_debut"), res.getString("date_fin")));
			}
				
				if (requete != null)
					requete.close();
				
				if (res != null)
					res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.getById_client " + sqle.getMessage());
		}
		
		return abonnement;
	}
	
	public ArrayList<Abonnement> getById_revue(int id_revue) {
		ArrayList<Abonnement> abonnement = new ArrayList<>();
		
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Abonnement where id_revue = ?");
			requete.setInt(1, id_revue);
			
			ResultSet res = requete.executeQuery();
			
			while (res.next()) {
				abonnement.add(new Abonnement(res.getInt("id_client"), id_revue, res.getString("date_debut"), res.getString("date_fin")));
			}
				
				if (requete != null)
					requete.close();
				
				if (res != null)
					res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.getById_revue " + sqle.getMessage());
		}
		
		return abonnement;
	}
	
	public Abonnement getById(int id_client, int id_revue) {
		Abonnement abonnement = null;
		
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Abonnement where id_client = ? and id_revue = ?");
			requete.setInt(1, id_client);
			requete.setInt(2, id_revue);
			
			ResultSet res = requete.executeQuery();
			
			res.next();
			
			abonnement = new Abonnement(id_client, id_revue, res.getString("date_debut"), res.getString("date_fin"));
			
			if (requete != null)
				requete.close();
			
			if (res != null)
				res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.getById " + sqle.getMessage());
		}
		
		return abonnement;
	}

	@Override
	public ArrayList<Abonnement> findAll() {
		ArrayList<Abonnement> listeAbonnement = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Abonnement");
			
			ResultSet res = requete.executeQuery();
		
			while (res.next()) {
				listeAbonnement.add(new Abonnement(
						res.getInt("id_client"),
						res.getInt("id_revue"),
						res.getString("date_debut"),
						res.getString("date_fin")
						));
			}
				
				if (requete != null)
					requete.close();
				
				if (res != null)
					res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.findAll " + sqle.getMessage());
		}
		
		return listeAbonnement;	
	}
}
