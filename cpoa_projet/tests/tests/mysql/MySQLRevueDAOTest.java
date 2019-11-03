package tests.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.RevueDAO;
import dao.metier.Revue;

class MySQLRevueDAOTest {
	
	RevueDAO  mrev = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO();
	
	@Test
	void testCreate() throws Exception {
		String titre ="test create";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =19;
		 
		Revue rev = new Revue(titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.create(rev)) {
			fail("Pas encore implemente");
		}
		mrev.delete(rev);
	}

	@Test
	void testDelete() throws Exception {
		String titre ="test delete";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =20;
		 
		Revue rev = new Revue(titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.delete(rev)) {
			fail("Pas encore supprime");
		}		
	}

	@Test
	void testUpdate() throws Exception {
		String titre ="test update";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =21;
		 
		Revue rev = new Revue(titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!mrev.update(rev)) {
			fail("Pas encore modifie");
		}
		mrev.delete(rev);
	}

	@Test
	void testGetById() throws Exception {
		String titre ="test getbyid";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =32;
		 
		Revue rev = new Revue(titre, description, tarif_numero, visuel,
					id_periodicite);
		mrev.create(rev);
				
		if(!mrev.getById(rev.getId_revue()).equals(rev)) {
			mrev.delete(rev);
			fail("Pas trouve");
		}
		mrev.delete(rev);
	}
	
	@Test
	void testFindAll() throws Exception {
		String titre ="test findall";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =21;
		 
		Revue rev = new Revue(titre, description, tarif_numero, visuel,
					id_periodicite);
		mrev.create(rev);
		
		if(mrev.findAll()==null) {
			mrev.delete(rev);
			fail("Pas trouve");
		}
		mrev.delete(rev);
	}
}
