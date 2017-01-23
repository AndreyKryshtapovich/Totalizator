package by.epamtr.totalizator.service;

import by.epamtr.totalizator.service.impl.AdminOperation;
import by.epamtr.totalizator.service.impl.ClientOperation;
import by.epamtr.totalizator.service.impl.GeneralOperation;
import by.epamtr.totalizator.service.impl.InitOperation;
/**
 * This class is designed to represent a factory for obtaining Service objects. Service
 * objects are implementations of Service interfaces.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private GeneralOperationService generalOperatoinService = new GeneralOperation();
	private ClientOperationService clientOperatoinService = new ClientOperation();
	private AdminOperationService adminOperatoinService = new AdminOperation();
	private InitOperationService initOperationService = new InitOperation();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
	/**
	 * 
	 * @return {@link by.epamtr.totalizator.service.GeneralOperationService} object.
	 */
	public GeneralOperationService getGeneralOperationService() {
		return generalOperatoinService;
	}
	/**
	 * 
	 * @return {@link by.epamtr.totalizator.service.ClientOperationService} object.
	 */
	public ClientOperationService getClientOperationService() {
		return clientOperatoinService;
	}
	/**
	 * 
	 * @return {@link by.epamtr.totalizator.service.AdminOperationService} object.
	 */
	public AdminOperationService getAdminOperationService() {
		return adminOperatoinService;
	}
	/**
	 * 
	 * @return {@link by.epamtr.totalizator.service.InitOperationService} object.
	 */
	public InitOperationService getInitOperationService(){
		return initOperationService;
	}
}
