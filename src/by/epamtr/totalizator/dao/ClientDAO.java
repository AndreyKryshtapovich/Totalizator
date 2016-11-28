package by.epamtr.totalizator.dao;

import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.exception.DAOException;

public interface ClientDAO {
	boolean registrationUser(User user, String login, String password)throws DAOException;
	List<Event> getCurrentEvents() throws DAOException;

}
