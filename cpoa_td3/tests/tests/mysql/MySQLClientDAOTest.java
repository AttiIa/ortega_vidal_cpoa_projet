package tests.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.ClientDAO;
import dao.metier.Client;

class MySQLClientDAOTest {
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
	ClientDAO mcli = daos.getClientDAO();

	@Test
	void testCreate() {
		int id_client = 18;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		
		if(!mcli.create(cli)) {
			fail("Pas encore implemente");
		}
		else {
			mcli.delete(cli);
		}
	}

	@Test
	void testDelete() {
		int id_client = 19;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		
		if(!mcli.delete(cli)) {
			fail("Pas encore supprime");
		}
		
	}

	@Test
	void testUpdate() {
		int id_client = 18;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "5";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		
		if(!mcli.update(cli)) {
			fail("Pas encore modifie");
		}
		else {
			mcli.delete(cli);
		}
	}

	@Test
	void testGetById() {
		int id_client = 18;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "5";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";		
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
		 ville,  pays);
		mcli.create(cli);
		
		if(mcli.getById(id_client).equals(cli)) {
			mcli.delete(cli);
			fail("Pas trouve");
		}
		else { 
			mcli.delete(cli);
		}
	}

}
