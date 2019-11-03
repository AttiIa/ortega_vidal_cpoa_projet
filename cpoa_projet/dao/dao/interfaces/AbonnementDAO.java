package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Abonnement;

public interface AbonnementDAO extends IDAO<Abonnement> {

	@Override
	boolean create(Abonnement abonnement) throws Exception;

	@Override
	boolean delete(Abonnement abonnement);

	@Override
	boolean update(Abonnement abonnement);
	
	@Override
	Abonnement getById(int id);
	
	ArrayList<Abonnement> getById_client(int id);
	
	ArrayList<Abonnement> getById_revue(int id_revue);
	
	Abonnement getById(int id1, int id2);

	@Override
	ArrayList<Abonnement> findAll();	
}
