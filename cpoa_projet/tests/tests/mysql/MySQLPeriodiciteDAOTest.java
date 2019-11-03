package tests.mysql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.PeriodiciteDAO;
import dao.metier.Periodicite;

class MySQLPeriodiciteDAOTest {
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
	PeriodiciteDAO mper = daos.getPeriodiciteDAO();

	@Test
	void testCreate() throws Exception {
		String libelle = "test";		
		Periodicite per = new Periodicite(libelle);
		
		if(!mper.create(per)) {
			fail("Pas implemente");
		}
		mper.delete(per);
	}

	@Test
	void testDelete() throws Exception {
		String libelle = "test";		
		Periodicite per = new Periodicite(libelle);
	
		if(!mper.delete(per)) {
			fail("Pas supprime");
		}
	}

	@Test
	void testUpdate() throws Exception {
		String libelle = "test";		
		Periodicite per = new Periodicite(libelle);
		
		if(!mper.update(per)) { 
			fail("Pas modifie");
		}
		mper.delete(per);
	}

	@Test
	void testGetById() throws Exception {
		String libelle = "test";		
		Periodicite per = new Periodicite(libelle);
		mper.create(per);
		
		if(!mper.getById(per.getId_periodicite()).equals(per)) {
			mper.delete(per);
			fail("Pas trouve");
		}
		mper.delete(per);

	}
	
	@Test
	void testFindAll() throws Exception {
		String libelle = "test";		
		Periodicite per = new Periodicite(libelle);
		
		if(mper.findAll()==null) {
			mper.delete(per);
			fail("Pas trouve");
		}
		mper.delete(per);
	}

}
