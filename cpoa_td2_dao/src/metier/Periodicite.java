package metier;

import java.sql.*;
import connexion.connexion;

public class Periodicite {
	private connexion connexion;
	private Connection laConnexion;
	
	int id_per;
	String libelle;
	
	public Periodicite() {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
		
		this.setId_per(id_per);
		this.setLibelle(libelle);
	}
	
	public int getId_per() {
		return id_per;
	}

	public void setId_per(int id_per) {
		this.id_per = id_per;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Periodicite [id_per=" + id_per + ", libelle=" + libelle + "]";
	}

	public void ajout(int id_per, String libelle) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("insert into Periodicite (id_periodicite, libelle)" + "VALUES (?, ?)");
			requete.setInt(1, id_per);
			requete.setString(2, libelle);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.ajout" + sqle.getMessage());
		}
	}
	
	public void supp(int id_per) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite = ?");
			requete.setInt(1, id_per);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.supp" + sqle.getMessage());
		}
	}
	
	public void modif(int id_per, String libelle) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle = ?  where id_periodicite = ?");
			requete.setInt(2, id_per);
			requete.setString(1, libelle);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Periodicite.modif" + sqle.getMessage());
		}
	} 

}
