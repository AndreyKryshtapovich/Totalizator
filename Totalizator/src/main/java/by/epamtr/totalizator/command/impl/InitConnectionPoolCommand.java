package by.epamtr.totalizator.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.service.InitOperationService;
import by.epamtr.totalizator.service.ServiceFactory;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Class is designed to process request for initializing data source for working
 * with DB.
 * 
 * @author Andrey
 *
 */
public class InitConnectionPoolCommand {
	private final static Logger Logger = LogManager.getLogger(InitConnectionPoolCommand.class.getName());

	/**
	 * Method calls required service method and provides logging.
	 * 
	 * @throws CommandException
	 *             If source wasn't destroyed.
	 */
	public void execute() throws CommandException {
		ServiceFactory factory = ServiceFactory.getInstance();
		InitOperationService initService = factory.getInitOperationService();

		try {
			initService.initConnectionPoolData();
			Logger.info("Source was initializad");
		} catch (ServiceException e) {
			Logger.error(e);
			throw new CommandException();
		}
	}

}
