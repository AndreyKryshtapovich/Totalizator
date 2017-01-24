package by.epamtr.totalizator.service;

import java.util.List;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Interface contains methods that are required to provide user's work with
 * application.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public interface ClientOperationService {
	/**
	 * Creates a new user in the system with role "user".
	 * 
	 * @param userDTO
	 *            {@link by.epamtr.totalizator.bean.dto.UserDTO } object
	 *            representing new user with all information about him.
	 * @param password
	 *            user's password for encrypting.
	 * @param repPassword
	 *            user's repeated password needed to confirm his password in the
	 *            system.
	 * @return {@code true} if new user registered successfully. {@code false}
	 *         if validation or DAO method fails.
	 * @throws ServiceException
	 *             if registration fails.
	 */
	boolean registrationUser(UserDTO userDTO, byte[] password, byte[] repPassword) throws ServiceException;

	/**
	 * Gets all the information about events which are available for betting at
	 * the moment. Prepare the information for displaying.
	 * 
	 * @param gameCouponId
	 *            Id of currently Opened game coupon in the system.
	 * @return {@link List} of the
	 *         {@link by.epamtr.totalizator.bean.entity.Event} object.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	List<Event> showEvents(int gameCouponId) throws ServiceException;

	/**
	 * Gets all information about currently opened game coupon in the system
	 * (status - Opened).
	 * 
	 * @return {@link by.epamtr.totalizator.bean.entity.GameCupoun} object.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	GameCupoun getOpenedGame() throws ServiceException;

	/**
	 * Creates a bet of the particular user in the system. Provides encryption
	 * of the credit card user's number.
	 * 
	 * @param makeBetDTO
	 *            {@link by.epamtr.totalizator.bean.dto.MakeBetDTO} object.
	 * @param creditCardNumber
	 *            user's credit card number.
	 * @return {@code true} if new bet registered successfully. {@code false} if
	 *         validation or DAO method fails.
	 * @throws ServiceException
	 *             if bet registration fails.
	 */
	boolean makeBet(MakeBetDTO makeBetDTO, byte[] creditCardNumber) throws ServiceException;
}
