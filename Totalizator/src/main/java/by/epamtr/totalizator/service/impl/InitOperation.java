package by.epamtr.totalizator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBInitDAO;
import by.epamtr.totalizator.service.InitOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;


public class InitOperation implements InitOperationService {
	private final static Logger Logger = LogManager.getLogger(InitOperation.class.getName());
	@Override
	public void initConnectionPoolData() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		DBInitDAO initDAO = factory.getDBInitDAO();
		
		try {
			initDAO.init();
		} catch (DAOException e) {
		Logger.error(e);
		throw new ServiceException("Failed initialization.",e);
		}
	}

	@Override
	public void destroyConnectionPoolData() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		DBInitDAO initDAO = factory.getDBInitDAO();
		
		try {
			initDAO.destroy();
		} catch (DAOException e) {
		Logger.error(e);
		throw new ServiceException("Failed destroying.",e);
		}
	}

}
