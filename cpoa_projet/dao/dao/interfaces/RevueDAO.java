package dao.interfaces;

import java.util.ArrayList;

import dao.metier.Revue;

public interface RevueDAO extends IDAO<Revue>{
	@Override
	boolean create(Revue revue);

	@Override
	boolean delete(Revue revue) throws Exception;

	@Override
	boolean update(Revue revue) throws Exception;
	
	@Override
	Revue getById(int id) throws Exception;
	
	@Override
	ArrayList<Revue> findAll() throws Exception;

}
