package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;
import dao.interfaces.PeriodiciteDAO;
import dao.metier.Periodicite;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{
	
	public boolean create(Periodicite periodicite) {
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("insert into Periodicite (id_periodicite, libelle)" + "VALUES (?, ?)");
			requete.setInt(1, periodicite.getId_periodicite());
			requete.setString(2, periodicite.getLibelle());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.create" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;
	}
	
	public boolean delete(Periodicite periodicite) {
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite = ?");
			requete.setInt(1, periodicite.getId_periodicite());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.delete" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;
	}
	
	public boolean update(Periodicite periodicite) {
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle = ?  where id_periodicite = ?");
			requete.setString(1, periodicite.getLibelle());
			requete.setInt(2, periodicite.getId_periodicite());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.update" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;	
	}
	
	public Periodicite getById(int id) {
		Periodicite periodicite = null;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Periodicite where id_periodicite = ?");
			requete.setInt(1, id);
			
			requete.executeUpdate();
		}
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.getById" + sqle.getMessage());
		}
		
		return periodicite;		
	}

}
