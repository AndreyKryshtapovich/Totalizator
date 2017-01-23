package by.epamtr.totalizator.service;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Interface contains methods that are required to provide general operations in
 * the application.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public interface GeneralOperationService {
	/**
	 * Method provides user authentication in the system.
	 * 
	 * @param login
	 *            user's login.
	 * @param password
	 *            user's password.
	 * @return {@link by.epamtr.totalizator.bean.entity.User} if the credentials
	 *         are correct.
	 * @throws ServiceException
	 *             if authentication fails.
	 */
	User signIn(String login, byte[] password) throws ServiceException;
}
