package dao.factory;

import dao.interfaces.*;

public class MySQLDAOFactory extends DAOFactory{
	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return null; //MySQLPeriodiciteDAO.getInstance();
	}
	@Override
	public AbonnementDAO getAbonnementDAO(){
		return null;// MySQLPromoDAO.getInstance();
	}
}
