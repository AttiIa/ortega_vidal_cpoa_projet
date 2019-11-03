package tests.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.ClientDAO;
import dao.metier.Client;

class ListeMemoireClientDAOTest {

	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	ClientDAO lcli = daos.getClientDAO();

	@Test
	void testCreate() throws Exception {
		int id_client = 15;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		
		if(!lcli.create(cli)) {
			fail("Pas encore implemente");
		}
		lcli.delete(cli);
	}

	@Test
	void testDelete() throws Exception {
		int id_client = 16;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		lcli.create(cli);
		
		if(!lcli.delete(cli)) {
			fail("Pas encore supprime");
		}
	}

	@Test
	void testUpdate() throws Exception {
		int id_client = 17;
		String nom = "Vidal";
		String prenom = "Thomas";
		String no_rue = "4";
		String voie ="rue paul doumer";
		String code_postal= "10150";
		String ville = "pont sainte marie";
		String pays = "France";
		 
		Client cli = new Client(id_client,  nom,  prenom,  no_rue,  voie,  code_postal,
			 ville,  pays);
		lcli.create(cli);
		
		if(!lcli.update(cli)) {
			fail("Pas encore modifie");
		}
		lcli.delete(cli);
	}
	
	@Test
	void testGetById() throws Exception {
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
		lcli.create(cli);
		
		if(!lcli.getById(cli.getId_client()).equals(cli)) {
			lcli.delete(cli);
			fail("Pas trouve");			
		}
		lcli.delete(cli);
	}

	@Test
	void testFindAll() throws Exception {

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
		lcli.create(cli);
		
		if(lcli.findAll()==null) {
			lcli.delete(cli);
			fail("Pas trouve");
		}
		lcli.delete(cli);
	}	
}
