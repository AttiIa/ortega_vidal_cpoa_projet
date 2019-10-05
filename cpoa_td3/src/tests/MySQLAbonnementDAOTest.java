package tests;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import dao.metier.Abonnement;
import dao.mysql.MySQLAbonnementDAO;

class MySQLAbonnementDAOTest {

	@Test
	void testCreate() {
		MySQLAbonnementDAO mabo = MySQLAbonnementDAO.getInstance();
		
		int id_client = 11;
		int  id_revue = 22;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!mabo.create(abo)) {
			fail("Pas implemente");
		}
		else { 
			mabo.delete(abo);
		}
	}

	@Test
	void testDelete() {
		MySQLAbonnementDAO mabo = MySQLAbonnementDAO.getInstance();
		
		int id_client = 33;
		int  id_revue = 44;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!mabo.delete(abo)) {
			fail("Pas supprime");
		}	
	}

	@Test
	void testUpdate() {
		MySQLAbonnementDAO mabo = MySQLAbonnementDAO.getInstance();
		
		int id_client = 55;
		int  id_revue = 66;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!mabo.update(abo)) {
			fail("Pas modifie");
		}	
		else { 
			mabo.delete(abo);
		}
	}

	@Test
	void testGetById() {
		MySQLAbonnementDAO mabo = MySQLAbonnementDAO.getInstance();
		
		int id_client = 77;
		int  id_revue = 88;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);	
		mabo.create(abo);
		
		if(mabo.getById(id_client, id_revue).equals(abo)) {
			mabo.delete(abo);
			fail("Pas trouve");
		}
		else { 
			mabo.delete(abo);
		}
	}

}
