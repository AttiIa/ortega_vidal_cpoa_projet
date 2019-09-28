package dao.factory;

import dao.interfaces.AbonnementDAO;
import dao.interfaces.ClientDAO;
import dao.interfaces.PeriodiciteDAO;
import dao.interfaces.RevueDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return null;
	}

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return null;
	}

	@Override
	public ClientDAO getClientDAO() {
		return null;
	}

	@Override
	public RevueDAO getRevueDAO() {
		return null;
	}

}
