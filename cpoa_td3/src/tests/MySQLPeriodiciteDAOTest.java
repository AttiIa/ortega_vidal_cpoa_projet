package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.metier.Periodicite;
import dao.mysql.MySQLPeriodiciteDAO;

class MySQLPeriodiciteDAOTest {

	@Test
	void testCreate() {
		MySQLPeriodiciteDAO mper = MySQLPeriodiciteDAO.getInstance();
		
		int id_periodicite = 11;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		
		if(!mper.create(per)) {
			fail("Pas implemente");
		}
		else {
			mper.delete(per);
		}
	}

	@Test
	void testDelete() {
		MySQLPeriodiciteDAO mper = MySQLPeriodiciteDAO.getInstance();
		
		int id_periodicite = 22;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
	
		if(!mper.delete(per)) {
			fail("Pas supprime");
		}
	}

	@Test
	void testUpdate() {
		MySQLPeriodiciteDAO mper = MySQLPeriodiciteDAO.getInstance();
		
		int id_periodicite = 33;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		
		if(!mper.update(per)) {
			fail("Pas modifie");
		}
		else {
			mper.delete(per);
		}
	}

	@Test
	void testGetById() {
		MySQLPeriodiciteDAO mper = MySQLPeriodiciteDAO.getInstance();
		
		int id_periodicite = 44;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		mper.create(per);
		
		if(mper.getById(id_periodicite).equals(per)) {
			mper.delete(per);
			fail("Pas trouve");
		}
		else {
			mper.delete(per);
		}
	}

}
