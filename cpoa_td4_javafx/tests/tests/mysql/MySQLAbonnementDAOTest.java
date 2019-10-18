package tests.mysql;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.AbonnementDAO;
import dao.metier.Abonnement;

class MySQLAbonnementDAOTest {
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
	AbonnementDAO mabo = daos.getAbonnementDAO();

	@Test
	void testCreate() {
		int id_client = 11;
		int  id_revue = 22;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!mabo.create(abo)) {
			fail("Pas implemente");
		}
		mabo.delete(abo);
	}

	@Test
	void testDelete() {
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
		int id_client = 55;
		int  id_revue = 66;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!mabo.update(abo)) {
			fail("Pas modifie");
		}	
		mabo.delete(abo);
	}

	@Test
	void testGetById() {
		int id_client = 77;
		int  id_revue = 88;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);	
		mabo.create(abo);
	 	
		if(!mabo.getById(abo.getId_client(), abo.getId_revue()).equals(abo)) {
			mabo.delete(abo);
			fail("Pas trouve");
		}
		mabo.delete(abo);

	}
	
	@Test
	void testFindAll() {
		int id_client = 99;
		int  id_revue = 111;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);	
		mabo.create(abo);
		
		if(mabo.findAll()==null) {
			mabo.delete(abo);
			fail("Pas trouve");
		}
		mabo.delete(abo);
	}

}
