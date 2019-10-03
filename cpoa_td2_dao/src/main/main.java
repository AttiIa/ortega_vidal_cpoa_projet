package main;

import java.util.Scanner;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.metier.*;

public class main {
	static Scanner sc = new Scanner(System.in);
	
	static Boolean b_per=false;
	static Boolean b_abo=false;
	static Boolean b_cli=false;
	static Boolean b_rev=false;
	
	static Boolean create=false;
	static Boolean delete=false;
	static Boolean update=false;
	

	public static void main(String[] args) {
																//Debut du programmme
		DAOFactory daos = null;
		System.out.println(
				 "1.MySQL "
				+ "2.ListeMemoire ");
		int i = sc.nextInt();
		if(i ==1)
		{
			daos = DAOFactory.getDAOFactory(Persistance.MySQL);
		}
		else if (i == 2)
		{
			 daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);	
		}
		
		//recuperation de la table voulue
		System.out.println("operation sur quel table? "
				+ "1.Periodicite "
				+ "2.Abonnement "
				+ "3.Client "
				+ "4.Revue ");
		int table = sc.nextInt();
		switch(table)
		{
		case 1 : b_per=true;
		case 2 : b_abo=true;
		case 3 : b_cli=true;
		case 4 : b_rev=true;
		}
		
		//recuperation de l'operation desire
		System.out.println("quel operation? "
				+ "1.Ajouter une ligne "
				+ "2.Supprimer une ligne "
				+ "3.Modifier un ligne ");
		int op = sc.nextInt();
		switch(op)
		{
		case 1 : create=true;
		case 2 : delete=true;
		case 3 : update=true;
		}
		
		
		//table Periodicite, methode d'ajout
		if(b_per&&create) {
			System.out.println("quel id_periode?");
			int id_periodicite = sc.nextInt();
			System.out.println("quel libelle?");
			sc.nextLine();
			String libelle = sc.nextLine();
			
			Periodicite per = new Periodicite(id_periodicite, libelle);			
			daos.getPeriodiciteDAO().create(per);
			Init();
		}

		//table Periodicite, methode de suppression
		else if(b_per&&delete) {			
			System.out.println("supprimer quelle ligne?");
			int id_periodicite = sc.nextInt();
			
			Periodicite per = new Periodicite(id_periodicite, "");
			daos.getPeriodiciteDAO().delete(per);
			Init();
		}

		//table Periodicite, methode de modification
		else if(b_per&&update) {
			System.out.println("modifier quelle ligne?");
			int id_periodicite = sc.nextInt();
			System.out.println("quel libelle?");
			sc.nextLine();
			String libelle = sc.nextLine();
			
			Periodicite per = new Periodicite(id_periodicite, libelle);
			daos.getPeriodiciteDAO().update(per);
			Init();
		}

		//table Abonnement, methode d'ajout
		else if(b_abo&&create) {
			System.out.println("quel id_client?");
			int id_client = sc.nextInt();
			System.out.println("quel id_revue?");
			int id_revue = sc.nextInt();
			System.out.println("quelle date de debut?");
			sc.nextLine();
			String date_debut = sc.nextLine();
			System.out.println("quelle date de fin?");
			String date_fin = sc.nextLine();
			
			Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
			daos.getAbonnementDAO().create(abo);
			Init();
		}

		//table Abonnement, methode de suppression
		else if(b_abo&&delete) {
			System.out.println("supprimer quel client?");
			int id_client = sc.nextInt();
			System.out.println("supprimer pour quelle revue?");
			int id_revue = sc.nextInt();
			
			Abonnement abo = new Abonnement(id_client, id_revue, "", "");
			daos.getAbonnementDAO().delete(abo);
			Init();
		}

		//table Abonnement, methode de modification
		else if(b_abo&&update) {
			System.out.println("modifier quelle ligne?");
			int id_client = sc.nextInt();
			System.out.println("quel id_revue?");
			int id_revue = sc.nextInt();
			System.out.println("quelle date de debut?");
			sc.nextLine();
			String date_debut = sc.nextLine();
			System.out.println("quelle date de fin?");
			String date_fin = sc.nextLine();
			
			Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
			daos.getAbonnementDAO().update(abo);
			Init();
		}

		//table Client, methode d'ajout
		else if(b_cli&&create) {
			System.out.println("quel id_client?");
			int id_client = sc.nextInt();
			System.out.println("quel nom?");
			sc.nextLine();
			String nom = sc.nextLine();
			System.out.println("quel prenom?");
			String prenom = sc.nextLine();
			System.out.println("quelle no de rue?");
			String no_rue = sc.nextLine();
			System.out.println("quelle voie?");
			String voie = sc.nextLine();
			System.out.println("quel code postal?");
			String code_postal = sc.nextLine();
			System.out.println("quelle ville?");
			String ville = sc.nextLine();
			System.out.println("quel pays?");
			String pays = sc.nextLine();
			
			Client cli = new Client(id_client, nom, prenom, no_rue, voie, code_postal, ville, pays);
			daos.getClientDAO().create(cli);
			Init();
		}

		//table Client, methode de suppression
		else if(b_cli&&delete) {
			System.out.println("supprimer quelle ligne?");
			int id_client = sc.nextInt();
			
			Client cli = new Client(id_client, "", "", "", "", "", "", "");
			daos.getClientDAO().delete(cli);
			Init();
		}

		//table Client, methode de modif
		else if(b_cli&&update) {
			System.out.println("modifier quelle ligne?");
			int id_client = sc.nextInt();
			System.out.println("quel nom?");
			sc.nextLine();
			String nom = sc.nextLine();
			System.out.println("quel prenom?");
			String prenom = sc.nextLine();
			System.out.println("quelle rue?");
			String no_rue = sc.nextLine();
			System.out.println("quelle voie?");
			String voie = sc.nextLine();
			System.out.println("quel code postal?");
			String code_postal = sc.nextLine();
			System.out.println("quelle ville?");
			String ville = sc.nextLine();
			System.out.println("quel pays?");
			String pays = sc.nextLine();
			
			Client cli = new Client(id_client, nom, prenom, no_rue, voie, code_postal, ville, pays);
			daos.getClientDAO().update(cli);
			Init();
		}

		//table Revue, methode d'ajout
		else if(b_rev&&create) {
			System.out.println("quel id_revue?");
			int id_revue = sc.nextInt();
			System.out.println("quel titre?");
			sc.nextLine();
			String titre = sc.nextLine();
			System.out.println("quelle description?");
			String description = sc.nextLine();
			System.out.println("quel tarif?");
			Double tarif_numero = sc.nextDouble();
			System.out.println("quel visuel?");
			sc.nextLine();
			String visuel = sc.nextLine();
			System.out.println("quel id_periode?");
			int id_periodicite = sc.nextInt();
			
			Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel, id_periodicite);
			daos.getRevueDAO().create(rev);
			Init();
		}

		//table Revue, methode de suppression
		else if(b_rev&&delete) {
			System.out.println("supprimer quelle ligne?");
			int id_revue = sc.nextInt();
			
			Revue rev = new Revue(id_revue, "", "", 0, "", 1);
			daos.getRevueDAO().delete(rev);
			Init();
		}

		//table Revue, methode de modif
		else if(b_rev&&update) {
			System.out.println("modifier quelle ligne?");
			int id_revue = sc.nextInt();
			System.out.println("quel titre?");
			sc.nextLine();
			String titre = sc.nextLine();
			System.out.println("quelle description?");
			String description = sc.nextLine();
			System.out.println("quel tarif?");
			Double tarif_numero = sc.nextDouble();
			System.out.println("quel visuel?");
			sc.nextLine();
			String visuel = sc.nextLine();
			System.out.println("quel id_periode?");
			int id_periodicite = sc.nextInt();
						
			Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel, id_periodicite);
			daos.getRevueDAO().update(rev);
			Init();
		}
		
		
		
	}
	public static void Init() {
		b_per=false;
		b_abo=false;
		b_cli=false;
		b_rev=false;
		
		create=false;
		delete=false;
		update=false;
	}

}
