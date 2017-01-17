package by.epamtr.totalizator.dao;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.exception.DAOException;

public interface GeneralDAO {

	User signInCheck(String login, String password) throws DAOException;
}
