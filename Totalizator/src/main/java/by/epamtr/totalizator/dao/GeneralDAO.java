package by.epamtr.totalizator.dao;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.exception.DAOException;

/**
 * Interface contains methods that are required to provide general operations
 * with the data storage.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public interface GeneralDAO {
	/**
	 * Checks user's credentials in a data storage.
	 * 
	 * @param login
	 *            user's login.
	 * @param password
	 *            encrypted user's password.
	 * @return {@link by.epamtr.totalizator.bean.entity.User} if the credentials
	 *         are correct.
	 * @throws DAOException
	 *             if obtaining information from the data storage fails.
	 */
	User signInCheck(String login, String password) throws DAOException;
}
