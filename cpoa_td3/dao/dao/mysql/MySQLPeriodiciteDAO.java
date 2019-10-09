package dao.mysql; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.interfaces.PeriodiciteDAO;
import dao.metier.Periodicite;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{	
	private static MySQLPeriodiciteDAO instance;
	private MySQLPeriodiciteDAO() {}
	
	public static MySQLPeriodiciteDAO getInstance() {
		if (instance==null) {
			instance = new MySQLPeriodiciteDAO();
		}
		return instance;
	}
	
	@Override
	public boolean create(Periodicite periodicite) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("insert into Periodicite (id_periodicite, libelle)" + "VALUES (?, ?)");
			requete.setInt(1, periodicite.getId_periodicite());
			requete.setString(2, periodicite.getLibelle());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.create " + sqle.getMessage());
			ok = false;
		}
		return ok;
	}
	
	@Override
	public boolean delete(Periodicite periodicite) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite = ?");
			requete.setInt(1, periodicite.getId_periodicite());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.delete " + sqle.getMessage());
			ok = false;
		}
		return ok;
	}
	
	@Override
	public boolean update(Periodicite periodicite) {
		boolean ok;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle = ?  where id_periodicite = ?");
			requete.setString(1, periodicite.getLibelle());
			requete.setInt(2, periodicite.getId_periodicite());
		
			requete.executeUpdate();
			
			 if (requete != null)
					requete.close();
			 
			ok = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.update " + sqle.getMessage());
			ok = false;
		}
		return ok;	
	}
	
	@Override
	public Periodicite getById(int id) {
		Periodicite periodicite = null;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Periodicite where id_periodicite = ?");
			requete.setInt(1, id);
			
			ResultSet res = requete.executeQuery();
			
			res.next();
			
			periodicite = new Periodicite(id, res.getString("libelle"));
			
			if (requete != null)
				requete.close();
			
			if (res != null)
				res.close();
			
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.getById " + sqle.getMessage());
		}
		
		return periodicite;		
	}

	@Override
	public ArrayList<Periodicite> findAll() {
		ArrayList<Periodicite> listePeriodicite = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Periodicite");
			
			ResultSet res = requete.executeQuery();
					 
			while (res.next()) {
				listePeriodicite.add(new Periodicite(
						res.getInt("id_periodicite"),
						res.getString("libelle")
						));				
			}
			
			if (requete != null)
				requete.close();
			
			if (res != null)
				res.close();
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.findAll " + sqle.getMessage());
		}
		
		return listePeriodicite;	
	}

}
