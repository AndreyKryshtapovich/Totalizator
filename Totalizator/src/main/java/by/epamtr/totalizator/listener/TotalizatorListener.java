package by.epamtr.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.command.impl.DestroyConnectionPoolCommand;
import by.epamtr.totalizator.command.impl.InitConnectionPoolCommand;

public class TotalizatorListener implements ServletContextListener {
	private final static Logger Logger = LogManager.getLogger(TotalizatorListener.class.getName());

   
	public TotalizatorListener() {}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	DestroyConnectionPoolCommand command = new DestroyConnectionPoolCommand();
    	
    	try {
			command.execute();
		} catch (CommandException e) {
			Logger.error(e);
		}
    }

    public void contextInitialized(ServletContextEvent arg0){ 
    	
    	InitConnectionPoolCommand command = new InitConnectionPoolCommand();
    	
			try {
				command.execute();
			} catch (CommandException e) {
				Logger.error(e);
				throw new RuntimeException();
			}
    }

}
