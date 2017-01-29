package by.epamtr.totalizator.service;

import java.util.List;
import java.util.Map;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.bean.dto.GameCouponDTO;
import by.epamtr.totalizator.bean.dto.EventsListDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCoupon;
import by.epamtr.totalizator.service.exception.ServiceException;

/**
 * Interface contains methods that are required to provide administrator's work
 * with the application.
 * 
 * @author Andrey Kryshtapovich
 *
 */

public interface AdminOperationService {
	/**
	 * Creates new game coupon in the system.
	 * 
	 * @param gameCupounDTO
	 *            {@link by.epamtr.totalizator.bean.dto.GameCouponDTO } object
	 *            that represents new game coupon.
	 * @return {@code true} if new game coupon was created successfully.
	 *         {@code false} if validation or DAO method fails.
	 * @throws ServiceException
	 *             if creating of new game coupon fails.
	 */
	boolean createNewGameCupoun(GameCouponDTO gameCupounDTO) throws ServiceException;

	/**
	 * Creates new event in the system.
	 * 
	 * @param eventDTO
	 *            {@link by.epamtr.totalizator.bean.dto.EventDTO} object that
	 *            represents new event.
	 * @return {@code true} if new event was created successfully. {@code false}
	 *         if validation or DAO method fails.
	 * @throws ServiceException
	 *             if creating of new event fails.
	 */
	boolean createNewEvent(EventDTO eventDTO) throws ServiceException;

	/**
	 * Matches event to a particular game coupon.
	 * 
	 * Event will be associated with particular game coupon.
	 * 
	 * @param gameCupounId
	 *            {@link String} representing game coupon's Id in the system.
	 * @param eventId
	 *            {@link String} representing event's Id in the system.
	 * @return {@code true} if event was matched to the game coupon
	 *         successfully. {@code false} if validation or DAO method fails.
	 * @throws ServiceException
	 *             if matching fails.
	 */

	boolean matchEventAndGame(String gameCupounId, String eventId) throws ServiceException;

	/**
	 * Updates information about particular event in the system.
	 * 
	 * @param eventDTO
	 *            {@link by.epamtr.totalizator.bean.dto.EventDTO} object that
	 *            represents event with up date information about it.
	 * @return {@code true} if event was updated successfully. {@code false} if
	 *         validation or DAO method fails.
	 * @throws ServiceException
	 *             if update fails.
	 */
	boolean updateEvent(EventDTO eventDTO) throws ServiceException;

	/**
	 * Updates information about particular game coupon in the system.
	 * 
	 * @param gameDTO
	 *            {@link by.epamtr.totalizator.bean.dto.GameCouponDTO} object
	 *            that represents game coupon with up to date information about
	 *            it.
	 * @return {@code true} if game coupon was updated successfully.
	 *         {@code false} if validation or DAO method fails.
	 * @throws ServiceException
	 *             if update fails.
	 */

	boolean updateGame(GameCouponDTO gameDTO) throws ServiceException;

	/**
	 * Unmatches event and the particular game coupon.
	 * 
	 * @param eventId
	 *            {@link String} representing event's Id in the system.
	 * @return {@code true} if event was unmatched successfully. {@code false}
	 *         if validation or DAO method fails.
	 * @throws ServiceException
	 *             if unmatching fails.
	 */

	boolean unmatchEventAndGame(String eventId) throws ServiceException;

	/**
	 * Deletes particular event from the system.
	 * 
	 * @param eventId
	 *            even's Id in the system.
	 * @return {@code true} if event was deleted successfully. {@code false} if
	 *         validation or DAO method fails.
	 * @throws ServiceException
	 *             if deleting fails.
	 */
	boolean deleteEvent(String eventId) throws ServiceException;

	/**
	 * Calculates the prize for bets that won in the particular drawing. Changes
	 * status of the game coupon representing particular drawing to closed or
	 * cancelled.
	 * 
	 * @param gameCouponId
	 *            Id of the particular game coupon in the data storage.
	 * @return {@code int} value representing the result : 1 - game coupon
	 *         closed successfully. -3 - rollback occurred. -2 - game coupon was
	 *         cancelled. -1 - error there are less than 15 events in the game
	 *         coupon.
	 * @throws ServiceException
	 *             if closing game coupon fails.
	 */

	int closeGameCoupon(String gameCouponId) throws ServiceException;

	/**
	 * Gets the information about game coupons with status In developing.
	 * 
	 * @return {@link List} of
	 *         {@link by.epamtr.totalizator.bean.entity.GameCoupon} objects
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	List<GameCoupon> getGamesInDevelopment() throws ServiceException;

	/**
	 * Gets the information about all game coupons except Closed or Cancelled
	 * ones.
	 * 
	 * @return {@link List} of
	 *         {@link by.epamtr.totalizator.bean.entity.GameCoupon} objects.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	List<GameCoupon> getAllGames() throws ServiceException;

	/**
	 * Gets the information about particular game coupon.
	 * 
	 * @param gameCupounId
	 *            Id of the game coupon in the system.
	 * @return {@link by.epamtr.totalizator.bean.entity.GameCoupon} object.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	GameCoupon getGameByGameCupounId(int gameCupounId) throws ServiceException;

	/**
	 * Gets all events that could be matched to a particular game coupon.
	 * 
	 * @param parameters
	 *            {@link String} representing game coupon's Id in the system.
	 * @return {@link by.epamtr.totalizator.bean.dto.EventDTO} object
	 *         representing a list events that could be matched to the game
	 *         coupon.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	EventsListDTO getUnmatchedEvents(String parameters) throws ServiceException;

	/**
	 * Gets all events matching particular game coupon specified by it's Id .
	 * 
	 * @param gameCupounId
	 *            game coupons Id in the system.
	 * @return {@link List} of the
	 *         {@link by.epamtr.totalizator.bean.entity.Event} objects.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	List<Event> getEventsByGameCupounId(int gameCupounId) throws ServiceException;

	/**
	 * Gets all data about available results from the data storage.
	 * 
	 * @return {@link Map} {@code key} - number of result. {@code value} -
	 *         result's description.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	Map<Integer, String> getResultDictionaryData() throws ServiceException;

	/**
	 * Gets all data about available statuses from the data storage.
	 * 
	 * @return {@link Map} {@code key} - number of status. {@code value} -
	 *         status's description.
	 * @throws ServiceException
	 *             if obtaining information fails.
	 */
	Map<Integer, String> getStatusDictionaryData() throws ServiceException;
}
