package dao.factory;

import dao.interfaces.*;
import dao.mysql.MySQLAbonnementDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLPeriodiciteDAO;
import dao.mysql.MySQLRevueDAO;

public class ListeMemoireDAOFactory extends DAOFactory{
	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return MySQLPeriodiciteDAO.getInstance();
	}
	@Override
	public AbonnementDAO getAbonnementDAO() {
		return  MySQLAbonnementDAO.getInstance();
	}
	@Override
	public ClientDAO getClientDAO() {
		return  MySQLClientDAO.getInstance();
	}
	@Override
	public RevueDAO getRevueDAO() {
		return  MySQLRevueDAO.getInstance();
	}


}
