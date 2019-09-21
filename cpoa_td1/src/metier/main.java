package metier;

import java.util.Scanner;

public class main {
	static Scanner sc = new Scanner(System.in);
	
	static Boolean b_per=false;
	static Boolean b_abo=false;
	static Boolean b_cli=false;
	static Boolean b_rev=false;
	
	static Boolean ajout=false;
	static Boolean supp=false;
	static Boolean modif=false;
	

	public static void main(String[] args) {
																//Debut du programmme
		//recuperation de la table voulue
		System.out.println("opération sur quel table? "
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
		System.out.println("quel op�ration? "
				+ "1.Ajouter une ligne "
				+ "2.Supprimer une ligne "
				+ "3.Modifier un ligne ");
		int op = sc.nextInt();
		switch(op)
		{
		case 1 : ajout=true;
		case 2 : supp=true;
		case 3 : modif=true;
		}
		
		//table Periodicite, m�thode d'ajout
		if(b_per&&ajout) {
			Periodicite per = new Periodicite();
			
			System.out.println("quel id_periode?");
			int id = sc.nextInt();
			System.out.println("quel libelle?");
			sc.nextLine();
			String libelle = sc.nextLine();
			
			per.ajout(id, libelle);
			Init();
		}

		//table Periodicite, m�thode de suppression
		else if(b_per&&supp) {
			Periodicite per = new Periodicite();
			
			System.out.println("supprimer quelle ligne?");
			int id = sc.nextInt();
			
			per.supp(id);
			Init();
		}

		//table Periodicite, m�thode de modification
		else if(b_per&&modif) {
			Periodicite per = new Periodicite();
			
			System.out.println("modifier quelle ligne?");
			int id = sc.nextInt();
			System.out.println("quel libelle?");
			sc.nextLine();
			String libelle = sc.nextLine();
			
			per.modif(id, libelle);
			Init();
		}

		//table Abonnement, m�thode d'ajout
		else if(b_abo&&ajout) {
			Abonnement abo = new Abonnement();
			
			System.out.println("quel id_client?");
			int id1 = sc.nextInt();
			System.out.println("quel id_revue?");
			int id2 = sc.nextInt();
			System.out.println("quelle date de debut?");
			sc.nextLine();
			String date_debut = sc.nextLine();
			System.out.println("quelle date de fin?");
			String date_fin = sc.nextLine();
			
			abo.ajout(id1, id2, date_debut, date_fin);
			Init();
		}

		//table Abonnement, m�thode de suppression
		else if(b_abo&&supp) {
			Abonnement abo = new Abonnement();
			
			System.out.println("supprimer quel client?");
			int id1 = sc.nextInt();
			System.out.println("supprimer pour quelle revue?");
			int id2 = sc.nextInt();
			
			abo.supp(id1, id2);
			Init();
		}

		//table Abonnement, m�thode de modification
		else if(b_abo&&modif) {
			Abonnement abo = new Abonnement();
			
			System.out.println("modifier quelle ligne?");
			int id1 = sc.nextInt();
			System.out.println("quel id_revue?");
			int id2 = sc.nextInt();
			System.out.println("quelle date de debut?");
			sc.nextLine();
			String date_debut = sc.nextLine();
			System.out.println("quelle date de fin?");
			String date_fin = sc.nextLine();
			
			abo.modif(id1, id2, date_debut, date_fin);
			Init();
		}

		//table Client, m�thode d'ajout
		else if(b_cli&&ajout) {
			Client cli = new Client();
			
			System.out.println("quel id_client?");
			int id1 = sc.nextInt();
			System.out.println("quel nom?");
			sc.nextLine();
			String nom = sc.nextLine();
			System.out.println("quel prenom?");
			String prenom = sc.nextLine();
			System.out.println("quelle no de rue?");
			String rue = sc.nextLine();
			System.out.println("quelle voie?");
			String voie = sc.nextLine();
			System.out.println("quel code postal?");
			String c_post = sc.nextLine();
			System.out.println("quelle ville?");
			String ville = sc.nextLine();
			System.out.println("quel pays?");
			String pays = sc.nextLine();
			
			cli.ajout(id1, nom, prenom, rue, voie, c_post, ville, pays);
			Init();
		}

		//table Client, m�thode de suppression
		else if(b_cli&&supp) {
			Client cli = new Client();
			
			System.out.println("supprimer quelle ligne?");
			int id = sc.nextInt();
			
			cli.supp(id);
			Init();
		}

		//table Client, m�thode de modif
		else if(b_cli&&modif) {
			Client cli = new Client();
			
			System.out.println("modifier quelle ligne?");
			int id1 = sc.nextInt();
			System.out.println("quel nom?");
			sc.nextLine();
			String nom = sc.nextLine();
			System.out.println("quel prenom?");
			String prenom = sc.nextLine();
			System.out.println("quelle rue?");
			String rue = sc.nextLine();
			System.out.println("quelle voie?");
			String voie = sc.nextLine();
			System.out.println("quel code postal?");
			String c_post = sc.nextLine();
			System.out.println("quelle ville?");
			String ville = sc.nextLine();
			System.out.println("quel pays?");
			String pays = sc.nextLine();
			
			cli.modif(id1, nom, prenom, rue, voie, c_post, ville, pays);
			Init();
		}

		//table Revue, m�thode d'ajout
		else if(b_rev&&ajout) {
			Revue rev = new Revue();
			
			System.out.println("quel id_revue?");
			int id1 = sc.nextInt();
			System.out.println("quel titre?");
			sc.nextLine();
			String titre = sc.nextLine();
			System.out.println("quelle description?");
			String desc = sc.nextLine();
			System.out.println("quel tarif?");
			Double tar = sc.nextDouble();
			System.out.println("quel visuel?");
			sc.nextLine();
			String vis = sc.nextLine();
			System.out.println("quel id_periode?");
			int id_per = sc.nextInt();
			
			rev.ajout(id1, titre, desc, tar, vis, id_per);
			Init();
		}

		//table Revue, m�thode de suppression
		else if(b_rev&&supp) {
			Revue rev = new Revue();
			
			System.out.println("supprimer quelle ligne?");
			int id = sc.nextInt();
			
			rev.supp(id);
			Init();
		}

		//table Revue, m�thode de modif
		else if(b_rev&&modif) {
			Revue rev = new Revue();
			
			System.out.println("modifier quelle ligne?");
			int id1 = sc.nextInt();
			System.out.println("quel titre?");
			sc.nextLine();
			String titre = sc.nextLine();
			System.out.println("quelle description?");
			String desc = sc.nextLine();
			System.out.println("quel tarif?");
			Double tar = sc.nextDouble();
			System.out.println("quel visuel?");
			sc.nextLine();
			String vis = sc.nextLine();
			System.out.println("quel id_periode?");
			int id_per = sc.nextInt();
						
			rev.modif(id1, titre, desc, tar, vis, id_per);
			Init();
		}
		
		
		
	}
	public static void Init() {
		b_per=false;
		b_abo=false;
		b_cli=false;
		b_rev=false;
		
		ajout=false;
		supp=false;
		modif=false;
	}

}
