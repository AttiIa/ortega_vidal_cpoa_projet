package dao.interfaces;

import dao.metier.Client;

public interface ClientDAO extends IDAO<Client>{
	
	@Override
	boolean create(Client client);

	@Override
	boolean delete(Client client);

	@Override
	boolean update(Client client);
	
	@Override
	Client getById(int id);

}
