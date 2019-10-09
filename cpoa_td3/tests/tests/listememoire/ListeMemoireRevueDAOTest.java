package tests.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.RevueDAO;
import dao.metier.Revue;

class ListeMemoireRevueDAOTest {
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	RevueDAO lrev = daos.getRevueDAO();

	@Test
	void testCreate() {
		int id_revue =18;
		String titre ="coder en Java";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =19;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		if(!lrev.create(rev)) {
			fail("Pas encore implemente");
		}
		lrev.delete(rev);
	}

	@Test
	void testDelete() {
		int id_revue =19;
		String titre ="coder en Java";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =20;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		lrev.create(rev);
		if(!lrev.delete(rev)) {
			fail("Pas encore supprime");
		}
	}

	@Test
	void testUpdate() {
		int id_revue =20;
		String titre ="coder en Java";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =21;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		lrev.create(rev);
		
		if(!lrev.update(rev)) {
			fail("Pas encore modifie");
		}
		lrev.delete(rev);
	}

	@Test
	void testGetById() {
		int id_revue =21;
		String titre ="coder en Java";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =21;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
				id_periodicite);
		lrev.create(rev);
		
		if(!lrev.getById(rev.getId_revue()).equals(rev)) {
			lrev.delete(rev);
			fail("Pas trouve");			
		}
		lrev.delete(rev);
	}

	@Test
	void testFindAll() {
		int id_revue =22;
		String titre ="coder en Java";
		String description="livre pour apprendre a coder en Java";
		double tarif_numero= 3.4;
		String visuel="logo.png";
		int id_periodicite =21;
		 
		Revue rev = new Revue(id_revue, titre, description, tarif_numero, visuel,
					id_periodicite);
		lrev.create(rev);
		
		if(lrev.findAll()==null) {
			lrev.delete(rev);
			fail("Pas trouve");
		}
		lrev.delete(rev);
	}

}
