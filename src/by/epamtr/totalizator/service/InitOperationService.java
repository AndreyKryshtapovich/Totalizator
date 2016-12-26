package by.epamtr.totalizator.service;

import by.epamtr.totalizator.service.exception.ServiceException;

public interface InitOperationService {

	void initConnectionPoolData() throws ServiceException;
	void destroyConnectionPoolData() throws ServiceException;
}
