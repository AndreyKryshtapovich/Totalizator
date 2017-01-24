package by.epamtr.totalizator.dao;

import by.epamtr.totalizator.dao.impl.DBAdminDAO;
import by.epamtr.totalizator.dao.impl.DBClientDAO;
import by.epamtr.totalizator.dao.impl.DBGeneralDAO;
import by.epamtr.totalizator.dao.impl.DBInitDAO;

/**
 * This class is designed to represent a factory for obtaining DAO objects. DAO
 * objects are implementations of DAO interfaces.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

	private DBGeneralDAO generalDAO = new DBGeneralDAO();
	private DBClientDAO clientDAO = new DBClientDAO();
	private DBAdminDAO adminDAO = new DBAdminDAO();
	private DBInitDAO initDAO = new DBInitDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * @return {@link by.epamtr.totalizator.dao.GeneralDAO } object.
	 */
	public DBGeneralDAO getDBGeneralDAO() {
		return generalDAO;
	}

	/**
	 * 
	 * @return {@link by.epamtr.totalizator.dao.ClientDAO} object.
	 */
	public DBClientDAO getDBClientDAO() {
		return clientDAO;
	}

	/**
	 * 
	 * @return {@link by.epamtr.totalizator.dao.AdminDAO} object.
	 */
	public DBAdminDAO getDBAdminDAO() {
		return adminDAO;
	}

	/**
	 * 
	 * @return {@link by.epamtr.totalizator.dao.SourceInit} object.
	 */
	public DBInitDAO getDBInitDAO() {
		return initDAO;
	}
}
