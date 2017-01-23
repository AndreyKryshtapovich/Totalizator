package by.epamtr.totalizator.dao;

import java.util.List;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.exception.DAOException;
/**
 * Interface contains methods which are required to provide user's
 * work with the data storage.
 * 
 * @author Andrey
 *
 */
public interface ClientDAO {
	/**
	 * Creates a new record representing a user in a data storage.
	 * 
	 * @param user {@link by.epamtr.totalizator.bean.entity.User} object representing new user.
	 * @param login user's login.
	 * @param password encoded user's password.
	 * @return {@code true} if user registered successfully.
	 * @throws DAOException if new user's registration fails.
	 */
	boolean registrationUser(User user, String login, String password)throws DAOException;
	/**
	 * Creates a record representing a bet of a particular user in a data storage.
	 * 
	 * @param makeBetDTO {@link by.epamtr.totalizator.bean.dto.MakeBetDTO} object.
	 * @param encryptedCardNumber {@link String} that represents encrypted user's credit card number.
	 * @return {@code true} if bet registered successfully.
	 * @throws DAOException if bet registration fails.
	 */
	boolean makeBet(MakeBetDTO makeBetDTO, String encryptedCardNumber) throws DAOException;
	/**
	 * Gets a game coupon with Opened status from the data storage.
	 * 
	 * @return {@link by.epamtr.totalizator.bean.entity.GameCupoun} object.
	 * @throws DAOException   if obtaining information from the data storage fails.
	 */
	GameCupoun getOpenedGame() throws DAOException;
	/**
	 * Gets all the events available for betting from the data storage. 
	 * 
	 * @param gameCouponId Id of the currently available game coupon(status: Opened).
	 * @return {@link List} of {@link by.epamtr.totalizator.bean.entity.Event} objects.
	 * @throws DAOException if obtaining information from the data storage fails.
	 */
	List<Event> getCurrentEvents(int gameCouponId) throws DAOException;

}
