package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Abonnement;

public interface AbonnementDAO extends IDAO<Abonnement> {

	@Override
	boolean create(Abonnement abonnement);

	@Override
	boolean delete(Abonnement abonnement);

	@Override
	boolean update(Abonnement abonnement);
	
	@Override
	Abonnement getById(int id);
	
	ArrayList<Abonnement> getById_client(int id);
	
	Abonnement getById(int id1, int id2);

	@Override
	ArrayList<Abonnement> findAll();	
}
