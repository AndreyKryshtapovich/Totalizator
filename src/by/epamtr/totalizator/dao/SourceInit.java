package by.epamtr.totalizator.dao;

import by.epamtr.totalizator.dao.exception.DAOException;

public interface SourceInit {

	void init() throws DAOException;
	void destroy() throws DAOException;
	
}
