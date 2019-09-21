package metier;

import java.sql.*;

public class Client {
	private connexion connexion;
	private Connection laConnexion;
	
	public Client() {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
	}
	
	public void ajout(int id_cli, String nom, String prenom, String rue, String voie, String c_post, String ville, String pays) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("insert into Client (id_client, nom, prenom, no_rue, voie, code_postal, ville, pays)" + "VALUES (?, ?, ?, ? , ?, ?, ?, ?)");
			requete.setInt(1, id_cli);
			requete.setString(2, nom);
			requete.setString(3, prenom);
			requete.setString(4, rue);
			requete.setString(5, voie);
			requete.setString(6, c_post);
			requete.setString(7, ville);
			requete.setString(8, pays);
			
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Client.ajout" + sqle.getMessage());
		}
	}
	
	public void supp(int id_cli) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client = ?");
			requete.setInt(1, id_cli);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Client.supp" + sqle.getMessage());
		}
	}
	
	public void modif(int id_cli, String nom, String prenom, String rue, String voie, String c_post, String ville, String pays) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("update Client set nom = ?, prenom = ?, no_rue = ?, voie = ?, code_postal = ?, ville = ?, pays = ?  where id_client = ?");
			requete.setString(1, nom);
			requete.setString(2, prenom);
			requete.setString(3, rue);
			requete.setString(4, voie);
			requete.setString(5, c_post);
			requete.setString(6, ville);
			requete.setString(7, pays);
			requete.setInt(8, id_cli);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Client.modif" + sqle.getMessage());
		}
	} 

}
