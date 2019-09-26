package dao.interfaces;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.metier.Abonnement;

public abstract class AbonnementDAO implements IDao<Abonnement> {
	private Connection laConnexion;

	
	public boolean create(Abonnement abonnement) {
		boolean nbLignes;
		try {
			Date d_deb = Date.valueOf(abonnement.getDate_debut());
			Date d_fin = Date.valueOf(abonnement.getDate_fin());
			
			int comp=d_deb.compareTo(d_fin);
			if(comp<0) {
			PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (id_client, id_revue, date_debut, date_fin)" + "VALUES (?, ?, ?, ?)");
			requete.setInt(1, abonnement.getId_client());
			requete.setInt(2, abonnement.getId_revue());
			requete.setDate(3, d_deb);
			requete.setDate(4, d_fin);
			
			nbLignes = true;
			}
			else {
				System.out.println("La date de début est supperieur à la date de fin, Veuillez recommencer");
				nbLignes = false;
			}
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.create" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;
	}
	
	public boolean delete(Abonnement abonnement) {
		boolean nbLignes;
		try {
			PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_client = ? and id_revue = ?");
			requete.setInt(1, abonnement.getId_client());
			requete.setInt(2, abonnement.getId_revue());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.delete" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;		
	}
	
	public boolean update(Abonnement abonnement) {
		boolean nbLignes;
		try {
			Date d_deb = Date.valueOf(abonnement.getDate_debut());
			Date d_fin = Date.valueOf(abonnement.getDate_fin());
			
			PreparedStatement requete = laConnexion.prepareStatement("update Abonnement set id_revue = ?, date_debut = ?, date_fin = ? where id_client = ?");
						
			requete.setInt(1, abonnement.getId_revue());
			requete.setDate(2, d_deb);
			requete.setDate(3, d_fin);
			requete.setInt(4, abonnement.getId_client());
		
			requete.executeUpdate();
			nbLignes = true;		
				
		}
		catch(SQLException sqle){
			System.out.println("Pb Abonnement.update" + sqle.getMessage());
			nbLignes = false;
		}
		return nbLignes;		
	}
	
	public Abonnement getById(int id) {
		return null;
	}
	
	//faire un getid avec deux id en parametres

}
