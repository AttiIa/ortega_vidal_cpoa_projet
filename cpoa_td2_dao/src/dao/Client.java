package dao;

import java.sql.*;

public class Client {
	private connexion connexion;
	private Connection laConnexion;
	private int id_cli;
	private String nom;
	private String prenom;
	private String rue;
	private String voie;
	private String c_post;
	private String ville;
	private String pays;

	
	public Client(int id_cli, String nom, String prenom, String rue, String voie, String c_post, String ville, String pays) {
		connexion = new connexion();
		laConnexion = connexion.creeConnexion();
	
		this.setId_cli(id_cli);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setRue(rue);
		this.setVoie(voie);
		this.setC_post(c_post);
		this.setVille(ville);
		this.setPays(pays);
	}
	
	public int getId_cli() {
		return id_cli;
	}

	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getC_post() {
		return c_post;
	}
	public void setC_post(String c_post) {
		this.c_post = c_post;
	}
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
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
