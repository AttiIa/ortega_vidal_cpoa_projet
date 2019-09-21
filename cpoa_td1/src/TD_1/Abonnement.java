package TD_1;
   
import java.sql.*;

public class Abonnement {
	private connexion connexion;
	private Connection laConnexion;
	
	public Abonnement() {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
	}
	
	public void ajout(int id_cli, int id_rev, String s_deb, String s_fin) {
		try {
			Date d_deb = Date.valueOf(s_deb);
			Date d_fin = Date.valueOf(s_fin);
			
			int comp=d_deb.compareTo(d_fin);
			if(comp<0) {
				
				PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (id_client, id_revue, date_debut, date_fin)" + "VALUES (?, ?, ?, ?)");
				requete.setInt(1, id_cli);
				requete.setInt(2, id_rev);
				requete.setDate(3, d_deb);
				requete.setDate(4, d_fin);
				
				int nbLignes = requete.executeUpdate();
				System.out.println(nbLignes);
				
				if (requete != null) {
					requete.close();
				}
			}
			else {
				System.out.println("La date de début est supperieur à la date de fin, Veuillez recommencer");
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.ajout" + sqle.getMessage());
		}
	}
	
	public void supp(int id_cli, int id_rev) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_client = ? and id_revue = ?");
			requete.setInt(1, id_cli);
			requete.setInt(2, id_rev);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.supp" + sqle.getMessage());
		}
	}
	
	public void modif(int id_cli, int id_rev, String s_deb, String s_fin) {
		try {
			PreparedStatement requete = laConnexion.prepareStatement("update Abonnement set id_revue = ?, date_debut = ?, date_fin = ? where id_client = ?");
			Date d_deb = Date.valueOf(s_deb);
			Date d_fin = Date.valueOf(s_fin);
			
			requete.setInt(1, id_rev);
			requete.setDate(2, d_deb);
			requete.setDate(3, d_fin);
			requete.setInt(4, id_cli);
			
			int nbLignes = requete.executeUpdate();
			System.out.println(nbLignes);
			
			if (requete != null) {
				requete.close();
			}

		}
		
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.modif" + sqle.getMessage());
		}
	} 


}
