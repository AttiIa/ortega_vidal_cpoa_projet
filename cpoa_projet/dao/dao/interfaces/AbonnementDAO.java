package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Abonnement;

public interface AbonnementDAO extends IDAO<Abonnement> {

	@Override
	boolean create(Abonnement abonnement) throws Exception;

	@Override
	boolean delete(Abonnement abonnement) throws Exception;

	@Override
	boolean update(Abonnement abonnement) throws Exception;
	
	@Override
	Abonnement getById(int id);
	
	ArrayList<Abonnement> getById_client(int id) throws Exception;
	
	ArrayList<Abonnement> getById_revue(int id_revue) throws Exception;
	
	Abonnement getById(int id1, int id2) throws Exception;

	@Override
	ArrayList<Abonnement> findAll() throws Exception;	
}
