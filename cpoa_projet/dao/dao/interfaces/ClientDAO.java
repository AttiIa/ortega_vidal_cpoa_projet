package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Client;

public interface ClientDAO extends IDAO<Client>{
	
	@Override
	boolean create(Client client) throws Exception;

	@Override
	boolean delete(Client client) throws Exception;

	@Override
	boolean update(Client client) throws Exception;
	
	@Override
	Client getById(int id) throws Exception;

	@Override
	ArrayList<Client> findAll() throws Exception;

}
