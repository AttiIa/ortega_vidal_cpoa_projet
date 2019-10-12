package tests.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.PeriodiciteDAO;
import dao.metier.Periodicite;

class ListeMemoirePeriodiciteDAOTest {
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	PeriodiciteDAO lper = daos.getPeriodiciteDAO();

	@Test
	void testCreate() {
		int id_periodicite = 11;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
			
		if(!lper.create(per)) {
			fail("Pas implemente");
		}
		lper.delete(per);
	}

	@Test
	void testDelete() {
		int id_periodicite = 22;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		lper.create(per);
	
		if(!lper.delete(per)) {
			fail("Pas supprime");
		}
	}

	@Test
	void testUpdate() {
		int id_periodicite = 33;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		lper.create(per);
		
		if(!lper.update(per)) { 
			fail("Pas modifie");
		}
		lper.delete(per);
	}

	@Test
	void testGetById() {
		int id_periodicite = 44;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		lper.create(per);
		
		if(!lper.getById(per.getId_periodicite()).equals(per)) {
			lper.delete(per);
			fail("Pas trouve");			
		}
		lper.delete(per);
	}

	@Test
	void testFindAll() {
		int id_periodicite = 55;
		String libelle = "test";		
		Periodicite per = new Periodicite(id_periodicite, libelle);
		lper.create(per);
		
		if(lper.findAll()==null) {
			lper.delete(per);
			fail("Pas trouve");
		}
		lper.delete(per);
	}

}
