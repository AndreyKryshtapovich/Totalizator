package by.epamtr.totalizator.service;

import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.service.exception.ServiceException;

public interface ClientOperationService {
	boolean registrationUser(String firstName, String lastName, String login, byte[] password,byte[] repPassword, String sex,
			String eMail,String country,String city,String address) throws ServiceException ;
	List<Event> showEvents() throws ServiceException;
}
