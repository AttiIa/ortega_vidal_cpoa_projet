package TD_1;

import java.sql.*;

public class Revue {

	private connexion connexion;
	private Connection laConnexion;
	
	public Revue() {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
	}
	
	public void ajout(int id_rev, String titre, String description, double tar, String visuel, int id_per) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (id_revue, titre, description, tarif_numero, visuel, id_periodicite)" + "VALUES (?, ?, ?, ?, ?, ?)");
			requete.setInt(1, id_rev);
			requete.setString(2, titre);
			requete.setString(3, description);
			requete.setDouble(4, tar);
			requete.setString(5, visuel);
			requete.setInt(6, id_per);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb de Revue.ajout" + sqle.getMessage());
		}
	}
	
	public void supp(int id_rev) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue = ?");
			requete.setInt(1, id_rev);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Revue.supp" + sqle.getMessage());
		}
	}
	
	public void modif(int id_rev, String titre, String description, double tar, String visuel, int id_per) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("update Revue set titre = ?, description = ?, tarif_numero = ?, visuel = ?, id_periodicite = ? where id_revue = ?");
			
			requete.setString(1, titre);
			requete.setString(2, description);
			requete.setDouble(3, tar);
			requete.setString(4, visuel);
			requete.setInt(5, id_per);
			requete.setInt(6, id_rev);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Revue.modif" + sqle.getMessage());
		}
	} 

}
