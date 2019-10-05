package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.metier.Revue;
import dao.mysql.MySQLRevueDAO;

class MySQLRevueDAOTest {

	@Test
	void testCreate() {
		MySQLRevueDAO mrev = MySQLRevueDAO.getInstance();

		 int id_revue =18;
		 String titre ="coder en Java";
		 String description="livre pour apprendre a coder en Java";
		 double tarif_numero= 3.4;
		 String visuel="logo.png";
		 int id_periodicite =19;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.create(rev)) {
		fail("Pas encore implemente");}
		else {
			mrev.delete(rev);
		}
	}

	@Test
	void testDelete() {
		MySQLRevueDAO mrev = MySQLRevueDAO.getInstance();

		 int id_revue =19;
		 String titre ="coder en Java";
		 String description="livre pour apprendre a coder en Java";
		 double tarif_numero= 3.4;
		 String visuel="logo.png";
		 int id_periodicite =20;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.delete(rev)) {
		fail("Pas encore implemente");}
		
	}

	@Test
	void testUpdate() {
		MySQLRevueDAO mrev = MySQLRevueDAO.getInstance();

		 int id_revue =20;
		 String titre ="coder en Java";
		 String description="livre pour apprendre a coder en Java";
		 double tarif_numero= 3.4;
		 String visuel="logo.png";
		 int id_periodicite =21;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.update(rev)) {
		fail("Pas encore implemente");}
		else {
			mrev.delete(rev);
		}
	}

	@Test
	void testGetById() {
		fail("Pas encore implémenté");
	}

	

}
