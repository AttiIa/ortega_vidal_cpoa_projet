package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Periodicite;

public interface PeriodiciteDAO extends IDAO<Periodicite>{
	
	@Override
	boolean create(Periodicite periodicite) throws Exception;

	@Override
	boolean delete(Periodicite periodicite) throws Exception;

	@Override
	boolean update(Periodicite periodicite) throws Exception;
	
	@Override
	Periodicite getById(int id) throws Exception;

	@Override
	ArrayList<Periodicite> findAll() throws Exception;
	
}
