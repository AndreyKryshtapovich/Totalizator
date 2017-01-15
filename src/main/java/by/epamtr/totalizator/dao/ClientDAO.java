package by.epamtr.totalizator.dao;

import java.util.List;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.exception.DAOException;

public interface ClientDAO {
	boolean registrationUser(User user, String login, String password)throws DAOException;
	boolean makeBet(MakeBetDTO makeBetDTO, String encryptedCardNumber) throws DAOException;
	GameCupoun getOpenedGame() throws DAOException;
	List<Event> getCurrentEvents(int gameCouponId) throws DAOException;

}
