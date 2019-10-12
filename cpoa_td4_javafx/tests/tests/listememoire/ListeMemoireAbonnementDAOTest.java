package tests.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.AbonnementDAO;
import dao.metier.Abonnement;

class ListeMemoireAbonnementDAOTest {
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	AbonnementDAO labo = daos.getAbonnementDAO();

	@Test
	void testCreate() {                
		int id_client = 11;
		int  id_revue = 22;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		if(!labo.create(abo)) {
			fail("Pas implemente");
		}
		labo.delete(abo);
	}

	@Test
	void testDelete() {
		int id_client = 33;
		int  id_revue = 44;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		labo.create(abo);
		
		if(!labo.delete(abo)) {
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
		labo.create(abo);
		
		if(!labo.update(abo)) {
			fail("Pas modifie");
		}	
		labo.delete(abo);
	}

	@Test
	void testGetById() {
		int id_client = 77;
		int  id_revue = 88;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);
		labo.create(abo);
		
		if(!labo.getById(abo.getId_client(), abo.getId_revue()).equals(abo)) {
			labo.delete(abo);
			fail("Pas trouve");			
		}
		labo.delete(abo);
	}

	@Test
	void testFindAll() {
		int id_client = 99;
		int  id_revue = 111;
		String date_debut = "2018-09-18";
		String date_fin = "2019-11-21";		
		Abonnement abo = new Abonnement(id_client, id_revue, date_debut, date_fin);	
		labo.create(abo);
		
		if(labo.findAll()==null) {
			labo.delete(abo);
			fail("Pas trouve");
		}
		labo.delete(abo);
	}

}
