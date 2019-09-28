package dao.interfaces;

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
}
