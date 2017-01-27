package by.epamtr.totalizator.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.bean.dto.GameCupounDTO;
import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.util.Utils;

/**
 * This class is designed to validate service method's parameters.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class Validator {

	private final static String CLOSED = "Closed";
	private final static String CANSELLED = "Canselled";
	private final static int UNKNOWN = 4;
	private final static int EVENTS_COUNT = 15;
	private final static String IS_DIGITS = "\\d+";

	/**
	 * Validates user's login and password for authentication. Parameters
	 * shouldn't be empty.
	 * 
	 * @param login
	 *            user's login.
	 * @param password
	 *            user's password.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean loginValidation(String login, byte[] password) {

		if (login.isEmpty()) {
			return false;
		}
		if (password.length == 0) {
			return false;
		}

		return true;
	}

	/**
	 * Validates information about new user before registration. Required
	 * information: first name, last name, login, sex, e-mail and country.
	 * Password and repeated password should be equals.
	 * 
	 * @param userDTO
	 *            {@link by.epamtr.totalizator.bean.dto.UserDTO} object that
	 *            represents information about new user.
	 * @param password
	 *            user's password.
	 * @param repPassword
	 *            user's repeated password.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean userInfoValidation(UserDTO userDTO, byte[] password, byte[] repPassword) {

		if (userDTO.getFirstName().isEmpty()) {
			return false;
		}
		if (userDTO.getLastName().isEmpty()) {
			return false;
		}
		if (userDTO.getRegisterLogin().isEmpty()) {
			return false;
		}
		if (password.length == 0 || repPassword.length == 0) {
			return false;
		}
		if (!Arrays.equals(password, repPassword)) {
			return false;
		}
		if (userDTO.getSex().isEmpty()) {
			return false;
		}
		if (userDTO.geteMail().isEmpty()) {
			return false;
		}
		if (userDTO.getCountry().isEmpty()) {
			return false;
		}

		return true;

	}

	/**
	 * Parameters shouldn't be empty. Game coupon's end date should be after
	 * it's start date. Minimum bet amount should be greater then zero.
	 * 
	 * @param gameCupounDTO
	 *            {@link by.epamtr.totalizator.bean.dto.GameCupounDTO} object
	 *            that represents particular game coupon.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */

	public static boolean newGameCupounInfoValidation(GameCupounDTO gameCupounDTO) {

		if (gameCupounDTO.getStartDate().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getStartTimeHours().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getStartTimeMinutes().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getEndDate().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getEndTimeHours().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getEndTimeMinutes().isEmpty()) {
			return false;
		}
		if (gameCupounDTO.getMinBetAmount().isEmpty()) {
			return false;
		}

		String correctStartDate = Utils.concatStringDate(gameCupounDTO.getStartDate(),
				gameCupounDTO.getStartTimeHours(), gameCupounDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(gameCupounDTO.getEndDate(), gameCupounDTO.getEndTimeHours(),
				gameCupounDTO.getEndTimeMinutes());

		Timestamp gameStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameEndDate = Timestamp.valueOf(correctEndDate);

		if (gameEndDate.before(gameStartDate)) {
			return false;
		}

		int minBetAmount = Integer.valueOf(gameCupounDTO.getMinBetAmount());

		if (minBetAmount <= 0) {

			return false;
		}
		return true;
	}

	/**
	 * Fields that represent information about new event shouldn't be empty.
	 * Event's end date should be after it's start date.
	 * 
	 * @param eventDTO
	 *            {@link by.epamtr.totalizator.bean.dto.EventDTO} object that
	 *            represents new event.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean newEventInfoValidation(EventDTO eventDTO) {

		if (eventDTO.getEventName().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartDate().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartTimeHours().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartTimeMinutes().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndDate().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndTimeHours().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndTimeMinutes().isEmpty()) {
			return false;
		}
		if (eventDTO.getTeamOne().isEmpty()) {
			return false;
		}
		if (eventDTO.getTeamTwo().isEmpty()) {
			return false;
		}

		String correctStartDate = Utils.concatStringDate(eventDTO.getStartDate(), eventDTO.getStartTimeHours(),
				eventDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(eventDTO.getEndDate(), eventDTO.getEndTimeHours(),
				eventDTO.getEndTimeMinutes());

		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate = Timestamp.valueOf(correctEndDate);

		if (eventEndDate.before(eventStartDate)) {
			return false;
		}

		return true;
	}

	/**
	 * Validates new information about event which is going to be updated.
	 * Fields that represent information about event shouldn't be empty. Event's
	 * end date should be after it's start date. Event's start date should be
	 * after game coupon's end date. Event should end in 2 days since game
	 * coupon's end date. If event has passed we are able to set statuses Closed
	 * (result can not be Unknown-4) or Cancelled (result should be Unknown-4).
	 * If event has't passed yet we are able to set any statuses except Closed
	 * (result - Unknown-4 only).
	 * 
	 * @param eventDTO
	 *            {@link by.epamtr.totalizator.bean.dto.EventDTO} object that
	 *            represents new event.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean updateEventValidation(EventDTO eventDTO) {

		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		List<GameCupoun> gamesList = null;
		Map<Integer, String> status;

		if (eventDTO.getEventId().isEmpty()) {
			return false;
		}
		if (eventDTO.getEventName().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartDate().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartTimeHours().isEmpty()) {
			return false;
		}
		if (eventDTO.getStartTimeMinutes().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndDate().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndTimeHours().isEmpty()) {
			return false;
		}
		if (eventDTO.getEndTimeMinutes().isEmpty()) {
			return false;
		}
		if (eventDTO.getTeamOne().isEmpty()) {
			return false;
		}
		if (eventDTO.getTeamTwo().isEmpty()) {
			return false;
		}
		if (eventDTO.getResultId().isEmpty()) {
			return false;
		}
		if (eventDTO.getGameCuponId().isEmpty()) {
			return false;
		}
		if (eventDTO.getStatus().isEmpty()) {
			return false;
		}

		String correctStartDate = Utils.concatStringDate(eventDTO.getStartDate(), eventDTO.getStartTimeHours(),
				eventDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(eventDTO.getEndDate(), eventDTO.getEndTimeHours(),
				eventDTO.getEndTimeMinutes());

		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate = Timestamp.valueOf(correctEndDate);

		Event event = new Event();
		event.setEventId(Integer.valueOf(eventDTO.getEventId()));
		event.setEventName(eventDTO.getEventName());
		event.setGameCuponId(Integer.valueOf(eventDTO.getGameCuponId()));
		event.setTeamOne(eventDTO.getTeamOne());
		event.setTeamTwo(eventDTO.getTeamTwo());
		event.setResultId(Integer.valueOf(eventDTO.getResultId()));
		event.setStartDate(eventStartDate);
		event.setEndDate(eventEndDate);
		event.setStatus(Integer.valueOf(eventDTO.getStatus()));

		try {
			gamesList = adminDAO.getGameByGameCupounId(event.getGameCuponId());
		} catch (DAOException e) {
			return false;
		}

		try {
			status = adminDAO.getStatusDictionaryData();
		} catch (DAOException e) {
			return false;
		}

		GameCupoun game = gamesList.get(0);
		String eventStatus = status.get(event.getStatus());

		if (event.getEndDate().before(event.getStartDate())) {
			return false;
		}
		if (!(event.getStartDate().after(game.getEndDate()))) {
			return false;
		}
	
		Calendar cal = Calendar.getInstance();

		// Event should end in 2 days since game coupon's end date.
		cal.setTimeInMillis(game.getEndDate().getTime());
		cal.add(Calendar.DAY_OF_MONTH, 2);
		Timestamp gameEndDatePlus2 = new Timestamp(cal.getTime().getTime());
		
		
		if (!(event.getEndDate().before(gameEndDatePlus2))) {
			return false;
		}
		
		
		cal = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());

		// if event has passed we are able to set statuses Closed(result can not
		// be Unknown-4) or Canselled(result should be Unknown-4)
		if (event.getEndDate().before(currentTimestamp)) {
			if (!eventStatus.equals(CLOSED) && !eventStatus.equals(CANSELLED)) {
				return false;
			}
			
			if (eventStatus.equals(CLOSED)) {
				if (event.getResultId() == UNKNOWN) {
					return false;
				}
			}
			
			if (eventStatus.equals(CANSELLED)) {
				if (event.getResultId() != UNKNOWN) {
					return false;
				}
			}
		}

		// event has't passed yet w are able to set any statuses except
		// Closed(result - Unknown-4 only)
		
		if (event.getEndDate().after(currentTimestamp)) {
			if (eventStatus.equals(CLOSED)) {
				return false;
			}
			if (event.getResultId() != UNKNOWN) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Validates new information about game coupon which is going to be updated.
	 * Fields that represent information about game coupon shouldn't be empty.
	 * Jackpot should be greater then zero or equal to it. Minimum bet amount
	 * should be greater then zero. Game coupon's end date should be after it's
	 * start date.
	 * 
	 * @param gameDTO
	 *            {@link by.epamtr.totalizator.bean.dto.GameCupounDTO} object
	 *            that represents particular game coupon.
	 * 
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean updateGameCouponValidation(GameCupounDTO gameDTO) {

		if (gameDTO.getGameCupounId().isEmpty()) {
			return false;
		}

		if (gameDTO.getStartDate().isEmpty()) {
			return false;
		}
		if (gameDTO.getStartTimeHours().isEmpty()) {
			return false;
		}
		if (gameDTO.getStartTimeMinutes().isEmpty()) {
			return false;
		}
		if (gameDTO.getEndDate().isEmpty()) {
			return false;
		}
		if (gameDTO.getEndTimeHours().isEmpty()) {
			return false;
		}
		if (gameDTO.getEndTimeMinutes().isEmpty()) {
			return false;
		}
		if (gameDTO.getMinBetAmount().isEmpty()) {
			return false;
		}
		if (gameDTO.getStatus().isEmpty()) {
			return false;
		}
		if (gameDTO.getJackpot().isEmpty()) {
			return false;
		}

		int jackpot = Integer.parseInt(gameDTO.getJackpot());
		int minBetAmount = Integer.parseInt(gameDTO.getMinBetAmount());

		if (jackpot < 0 || minBetAmount <= 0) {
			return false;
		}

		String correctStartDate = Utils.concatStringDate(gameDTO.getStartDate(), gameDTO.getStartTimeHours(),
				gameDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(gameDTO.getEndDate(), gameDTO.getEndTimeHours(),
				gameDTO.getEndTimeMinutes());

		Timestamp gameStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameEndDate = Timestamp.valueOf(correctEndDate);

		if (gameEndDate.before(gameStartDate)) {
			return false;
		}

		return true;

	}

	/**
	 * Validates value provided by the dropdown. Empty dropdown should't be
	 * submitted.
	 * 
	 * @param parameters
	 *            value provided by the dropdown.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean dropDownValidation(String parameters) {
		boolean result = true;

		if (parameters.isEmpty()) {
			result = false;
		}
		return result;
	}

	/**
	 * Validates information about user's bet. Credit card number shouldn't be
	 * empty. User should suggest results for all 15 events.
	 * 
	 * @param makeBetDTO
	 *            {@link by.epamtr.totalizator.bean.dto.MakeBetDTO} object that
	 *            represents information about user's bet.
	 * @param creditCardNumber
	 *            user's credit card number.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean makeBetValidation(MakeBetDTO makeBetDTO, byte[] creditCardNumber) {
		if (creditCardNumber.length == 0) {
			return false;
		}
		if (makeBetDTO.getUserResultMap().size() < EVENTS_COUNT) {
			return false;
		}
		return true;
	}

	/**
	 * Validates a parameter that should be a number that is greater than zero.
	 * 
	 * @param parameter
	 *            {@link String} that represents a parameter for validation.
	 * @return {@code true} if validation passed successfully. {@code false}
	 *         otherwise.
	 */
	public static boolean digitParameterValidation(String parameter) {

		if (parameter.isEmpty()) {
			return false;
		}

		Pattern p = Pattern.compile(IS_DIGITS);
		Matcher m = p.matcher(parameter);

		if (!m.matches()) {
			return false;
		}

		int selectedParameter = Integer.valueOf(parameter);

		if (selectedParameter <= 0) {
			return false;
		}

		return true;
	}
}