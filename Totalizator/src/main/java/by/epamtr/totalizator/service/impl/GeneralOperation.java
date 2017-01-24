package by.epamtr.totalizator.service.impl;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.GeneralDAO;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.service.GeneralOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * This class is the implementation of the
 * {@link by.epamtr.totalizator.service.GeneralOperationService} for working with
 * database.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class GeneralOperation implements GeneralOperationService {

	@Override
	public User signIn(String login, byte[] password) throws ServiceException {
		User user;
		if (!Validator.loginValidation(login, password)) {
			return null;
		}

		DAOFactory factory = DAOFactory.getInstance();
		GeneralDAO generalDAO = factory.getDBGeneralDAO();

		String encrypedPassword = DigestUtils.md5Hex(password);
		Arrays.fill(password, (byte) 0);
		try {
			user = generalDAO.signInCheck(login, encrypedPassword);
		} catch (DAOException e) {
			throw new ServiceException("Failed signing in.", e);
		}
		return user;
	}

}
