package TD_1;

import java.sql.*;

public class Periodicite {
	private connexion connexion;
	private Connection laConnexion;
	
	public Periodicite() {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
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
