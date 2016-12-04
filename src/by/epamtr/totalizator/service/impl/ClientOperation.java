package by.epamtr.totalizator.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.ClientDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.service.ClientOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;

public class ClientOperation implements ClientOperationService {

	@Override
	public boolean registrationUser(UserDTO userDTO, byte[] password, byte[] repPassword)throws ServiceException {

		boolean result = true;
		if (!Validator.userInfoValidation(userDTO, password, repPassword)) {
			result = false;
			return result;
		}

		User user = new User();
		String encryptedPassword = DigestUtils.md5Hex(password);
		//String encryptedRepPassword = DigestUtils.md5Hex(repPassword);

		Arrays.fill(password, (byte) 0);
		Arrays.fill(repPassword, (byte) 0);

		buildUser(user, userDTO);

		DAOFactory factory = DAOFactory.getInstance();
		ClientDAO clientDAO = factory.getDBClientDAO();

		try {
			if (!clientDAO.registrationUser(user, userDTO.getRegisterLogin(), encryptedPassword)) {
				result = false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed registration.", e);
		}
		return result;

	}

	private static void buildUser(User user, UserDTO userDTO) {

		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.seteMail(userDTO.geteMail());
		user.setCountry(userDTO.getCountry());
		user.setSex(userDTO.getSex());
		if (userDTO.getCity().isEmpty()) {
			user.setCity(null);
		} else {
			user.setCity(userDTO.getCity());
		}

		if (userDTO.getAddress().isEmpty()) {
			user.setAddress(null);
		} else {
			user.setAddress(userDTO.getAddress());
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
