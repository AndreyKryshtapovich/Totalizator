package by.epamtr.totalizator.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.dao.SourceInit;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.exception.DAOException;
/**
 * This class is the implementation of the
 * {@link by.epamtr.totalizator.dao.SourceInit} for working with database.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DBInitDAO implements SourceInit {
	private final static Logger Logger = LogManager.getLogger(DBInitDAO.class.getName());

	@Override
	public void init() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
	     try {
	 		connectionPool.initPoolData();
	 	} catch (ConnectionPoolException e) {
	 		Logger.error(e);
	 		throw new DAOException("Failed connection pool initialization.",e);
	 	}
		
	}

	@Override
	public void destroy() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.dispose();
		} catch (ConnectionPoolException e) {
			Logger.error(e);
			throw new DAOException("Failed connection pool destroying", e);
		}
		
	}


}
