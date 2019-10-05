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

	public boolean create(Revue revue) {
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (id_revue, titre, description, tarif_numero, visuel, id_periodicite)" + "VALUES (?, ?, ?, ?, ?, ?)");
			requete.setInt(1, revue.getId_revue());
			requete.setString(2, revue.getTitre());
			requete.setString(3, revue.getDescription());
			requete.setDouble(4, revue.getTarif_numero());
			requete.setString(5, revue.getVisuel());
			requete.setInt(6, revue.getId_periodicite());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.create" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;	
	}
	
	public boolean delete(Revue revue) {
		boolean nbLignes;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue = ?");
			requete.setInt(1, revue.getId_revue());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.delete" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;		
	}
	
	public boolean update(Revue revue) {
		boolean nbLignes;
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
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Revue.update" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;		
	}
	
	public Revue getById(int id) {
		Revue revue = null;
		try {
			Connection laConnexion = Connexion.getInstance().creeConnexion();			

			PreparedStatement requete = laConnexion.prepareStatement("select * from Revue where id_revue = ?");
			requete.setInt(1, id);
			
			ResultSet res = requete.executeQuery();
			res.next();
			
			revue = new Revue(id, res.getString("titre"), res.getString("description"), res.getDouble("tarif_numero"), res.getString("visuel"), res.getInt("id_periodicite"));
			}
		catch(SQLException sqle){
			System.out.println("Pb Revue.getById" + sqle.getMessage());
		}
		
		return revue;			
	}
	@Override
	public ArrayList<Revue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
