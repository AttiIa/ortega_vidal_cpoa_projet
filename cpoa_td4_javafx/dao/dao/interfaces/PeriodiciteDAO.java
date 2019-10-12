package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Periodicite;

public interface PeriodiciteDAO extends IDAO<Periodicite>{
	
	@Override
	boolean create(Periodicite periodicite);

	@Override
	boolean delete(Periodicite periodicite);

	@Override
	boolean update(Periodicite periodicite);
	
	@Override
	Periodicite getById(int id);

	@Override
	ArrayList<Periodicite> findAll();
	
}
