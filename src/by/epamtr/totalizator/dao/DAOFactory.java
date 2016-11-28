package by.epamtr.totalizator.dao;

import by.epamtr.totalizator.dao.impl.DBAdminDAO;
import by.epamtr.totalizator.dao.impl.DBClientDAO;
import by.epamtr.totalizator.dao.impl.DBGeneralDAO;

public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private DBGeneralDAO generalDAO = new DBGeneralDAO();
	private DBClientDAO clientDAO = new DBClientDAO();
	private DBAdminDAO adminDAO = new DBAdminDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return INSTANCE;
	}
	
	public DBGeneralDAO getDBGeneralDAO(){
		return generalDAO;
	}
	
	public DBClientDAO getDBClientDAO(){
		return clientDAO;
	}
	
	public DBAdminDAO getDBAdminDAO(){
		return adminDAO;
	}
}
