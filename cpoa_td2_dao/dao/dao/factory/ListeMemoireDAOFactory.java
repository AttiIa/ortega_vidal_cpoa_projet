package dao.factory;

import dao.interfaces.*;
import dao.listememoire.*;
import dao.mysql.MySQLAbonnementDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLPeriodiciteDAO;
import dao.mysql.MySQLRevueDAO;

public class ListeMemoireDAOFactory extends DAOFactory{
	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return ListeMemoirePeriodiciteDAO.getInstance();
	}
	@Override
	public AbonnementDAO getAbonnementDAO() {
		return  ListeMemoireAbonnementDAO.getInstance();
	}
	@Override
	public ClientDAO getClientDAO() {
		return  ListeMemoireClientDAO.getInstance();
	}
	@Override
	public RevueDAO getRevueDAO() {
		return  ListeMemoireRevueDAO.getInstance();
	}


}
