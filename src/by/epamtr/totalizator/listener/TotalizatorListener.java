package by.epamtr.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;

public class TotalizatorListener implements ServletContextListener {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static Logger Logger = LogManager.getLogger(TotalizatorListener.class.getName());

   
	public TotalizatorListener() {}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	connectionPool.dispose();
    }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
       try {
		connectionPool.initPoolData();
	} catch (ConnectionPoolException e) {
		Logger.error(e);
	}
    }

}
