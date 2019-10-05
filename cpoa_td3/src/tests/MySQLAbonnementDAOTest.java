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
		fail("Pas encore implémenté");
	}

	@Test
	void testUpdate() {
		fail("Pas encore implémenté");
	}

	@Test
	void testGetByIdInt() {
		fail("Pas encore implémenté");
	}

	@Test
	void testGetByIdIntInt() {
		fail("Pas encore implémenté");
	}

	@Test
	void testFindAll() {
		fail("Pas encore implémenté");
	}

}
