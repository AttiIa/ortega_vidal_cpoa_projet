package dao.mysql; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.interfaces.RevueDAO;
import dao.metier.Revue;

public class MySQLRevueDAO implements RevueDAO{
	private static MySQLRevueDAO instance;
	private MySQLRevueDAO() {}
	public static MySQLRevueDAO getInstance() {
	if (instance==null) {
	instance = new MySQLRevueDAO();
	}
	return instance;
	}

	@Override
	public boolean create(Revue revue) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (titre, description, tarif_numero, visuel, id_periodicite)" + "VALUES (?, ?, ?, ?, ?)");
			requete.setString(1, revue.getTitre());
			requete.setString(2, revue.getDescription());
			requete.setDouble(3, revue.getTarif_numero());
			requete.setString(4, revue.getVisuel());
			requete.setInt(5, revue.getId_periodicite());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.create " + sqle.getMessage());
			ok = false;
		}
		return ok;	
	}
	
	@Override
	public boolean delete(Revue revue) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue = ?");
			requete.setInt(1, revue.getId_revue());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.delete " + sqle.getMessage());
			ok = false;
		}
		return ok;		
	}
	
	@Override
	public boolean update(Revue revue) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("update Revue set titre = ?, description = ?, tarif_numero = ?, visuel = ?, id_periodicite = ? where id_revue = ?");
			requete.setString(1, revue.getTitre());
			requete.setString(2, revue.getDescription());
			requete.setDouble(3, revue.getTarif_numero());
			requete.setString(4, revue.getVisuel());
			requete.setInt(5, revue.getId_periodicite());
			requete.setInt(6, revue.getId_revue());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.update " + sqle.getMessage());
			ok = false;
		}
		return ok;		
	}
	
	@Override
	public Revue getById(int id) {
		Revue revue = null;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Revue where id_revue = ?");
			requete.setInt(1, id);
			
			ResultSet res = requete.executeQuery();	
			
			res.next();
			
			revue = new Revue(id, res.getString("titre"), res.getString("description"), res.getDouble("tarif_numero"), res.getString("visuel"), res.getInt("id_periodicite"));
			
			if (requete != null)
				requete.close();
			
			if (res != null)
				res.close();
			}
		catch(SQLException sqle){
			System.out.println("Pb Revue.getById " + sqle.getMessage());
		}
		
		return revue;			
	}
	@Override
	public ArrayList<Revue> findAll() {
		ArrayList<Revue> listeRevue = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Revue");
			
			ResultSet res = requete.executeQuery();
		
			while (res.next()) {
				listeRevue.add(new Revue(
						res.getInt("id_revue"),
						res.getString("titre"),
						res.getString("description"),
						res.getDouble("tarif_numero"),
						res.getString("visuel"),
						res.getInt("id_periodicite")
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
		
		return listeRevue;	
	}
}
