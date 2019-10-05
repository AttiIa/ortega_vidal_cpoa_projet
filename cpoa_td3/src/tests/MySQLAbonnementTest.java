package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.metier.Abonnement;
class MySQLAbonnementTest {

	@Test

	void MySQLAbonnementtest() {
		Abonnement abo = new Abonnement();
		if (abo.getId_client() == 0 && abo.getId_revue() == 0 && abo.getDate_debut() == "" && abo.getDate_fin() == "") {
		fail("Champs vides");
		}
		else
		{
			//MySQLAbonnementDAO mabo = MySQLAbonnementDAO.getInstance();
			//mabo.create(abo);	
			System.out.println("donnees implementes");
		}
	}

}
