package tests.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.ClientDAO;
import dao.metier.Client;

class MySQLClientDAOTest {
	
	ClientDAO mcli = DAOFactory.getDAOFactory(Persistance.MySQL).getClientDAO();

	@Test
	void testCreate() throws Exception {
		String nom = "test create";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(nom, prenom, no_rue, voie, code_postal,
			 ville,  pays);
		
		if(!mcli.create(cli)) {
			fail("Pas encore implemente");
		}
		mcli.delete(cli);
	}

	@Test
	void testDelete() throws Exception {
		String nom = "test delete";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(nom, prenom, no_rue, voie, code_postal, ville, pays);
		
		if(!mcli.delete(cli)) {
			fail("Pas encore supprime");
		}		
	}

	@Test
	void testUpdate() throws Exception {
		String nom = "test updatetest ";
		String prenom = "Thomas";
		String no_rue = "5";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(nom, prenom, no_rue, voie, code_postal, ville, pays);
		
		if(!mcli.update(cli)) {
			fail("Pas encore modifie");
		}
		mcli.delete(cli);
	}

	@Test
	void testGetById() throws Exception {
		String nom = "test getbyid";
		String prenom = "Thomas";
		String no_rue = "5";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";		
		Client cli = new Client(nom, prenom, no_rue, voie, code_postal, ville, pays);
		mcli.create(cli);
		
		if(!mcli.getById(cli.getId_client()).equals(cli)) {
			mcli.delete(cli);
			fail("Pas trouve");
		}
		mcli.delete(cli);

	}
	
	@Test
	void testFindAll() throws Exception {
		String nom = "test findall";
		String prenom = "Thomas";
		String no_rue = "5";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";		
		Client cli = new Client(nom, prenom, no_rue, voie, code_postal, ville, pays);
		mcli.create(cli);
		
		if(mcli.findAll()==null) {
			mcli.delete(cli);
			fail("Pas trouve");
		}
		mcli.delete(cli);
	}

}
