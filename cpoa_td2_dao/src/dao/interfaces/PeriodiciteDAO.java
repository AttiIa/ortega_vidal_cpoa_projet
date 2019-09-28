package dao.interfaces;

import java.sql.*;
import dao.metier.Periodicite;

public abstract class PeriodiciteDAO implements DAO<Periodicite>{
	private Connection laConnexion;
	
	public boolean create(Periodicite periodicite) {
		boolean nbLignes;
		try {
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
