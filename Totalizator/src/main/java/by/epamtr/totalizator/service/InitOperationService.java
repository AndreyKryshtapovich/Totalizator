package by.epamtr.totalizator.service;

import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Interface contains methods to provide initialization and destroying of the
 * data source. Data Source contains resources that are required for connecting to the data storage.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public interface InitOperationService {
	/**
	 * Initializes a data source.
	 * @throws ServiceException if initialization fails.
	 */
	void initConnectionPoolData() throws ServiceException;
	/**
	 * Destroys a data source.
	 * @throws ServiceException if destroying fails.
	 */
	void destroyConnectionPoolData() throws ServiceException;
}
