package by.epamtr.totalizator.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.ClientDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;

public class ClientOperation implements ClientOperationService {

	@Override
	public boolean registrationUser(String firstName, String lastName, String login, byte[] password,
			byte[] repPassword, String sex, String eMail, String country, String city, String address)
			throws ServiceException {

		boolean result = true;
		if (!Validator.userInfoValidation(firstName, lastName, login, password, repPassword, sex, eMail, country, city,
				address)) {
			throw new ServiceException("invalid parameters.");
		}

		User user = new User();
		String encryptedPassword = DigestUtils.md5Hex(password);
		String encryptedRepPassword = DigestUtils.md5Hex(repPassword);
		
		Arrays.fill(password, (byte)0);
		Arrays.fill(repPassword, (byte)0);
		
		buildUser(user, firstName, lastName, login, encryptedPassword, encryptedRepPassword, sex, eMail, country, city,
				address);
		
		DAOFactory factory = DAOFactory.getInstance();
		ClientDAO clientDAO = factory.getDBClientDAO();

		try {
			if (!clientDAO.registrationUser(user, login, encryptedPassword)) {
				result = false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed registration.", e);
		}
		return result;

	}
	
	private static void buildUser (User user, String firstName, String lastName, String login, String password,
			String repPassword, String sex, String eMail, String country, String city, String address){
	
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.seteMail(eMail);
		user.setCountry(country);
		user.setSex(sex);
		if (city.isEmpty()) {
			user.setCity(null);
		}else{
			user.setCity(city);
		}

		if (address.isEmpty()) {
			user.setAddress(null);
		}else{
			user.setAddress(address);
		}
		user.setRole("user");
	}

	@Override
	public List<Event> showEvents() throws ServiceException {
		
		List<Event> eventsList = null;
		DAOFactory factory = DAOFactory.getInstance();
		ClientDAO clientDAO = factory.getDBClientDAO();
		
		try {
			eventsList = clientDAO.getCurrentEvents();
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return eventsList;
	}

}
