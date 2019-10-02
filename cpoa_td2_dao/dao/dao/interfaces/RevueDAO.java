package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Revue;

public interface RevueDAO extends IDAO<Revue>{
	@Override
	boolean create(Revue revue);

	@Override
	boolean delete(Revue revue);

	@Override
	boolean update(Revue revue);
	
	@Override
	Revue getById(int id);
	
	@Override
	ArrayList<Revue> findAll();

}
