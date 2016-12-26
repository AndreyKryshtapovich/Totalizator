package by.epamtr.totalizator.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.bean.dto.GameCupounDTO;
import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;

public class Validator {

	private final static String CLOSED = "Closed";
	private final static String CANSELLED = "Canselled";
	private final static int UNKNOWN = 4;
	private final static int EVENTS_COUNT = 15;
	
	public static boolean loginValidation(String login, byte[] password) {

		if (login.isEmpty()) {
			return false;
		}
		if (password.length == 0) {
			return false;
		}

		return true;
	}

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
		return true;
	}

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
		return true;
	}

	public static boolean updateEventValidation(Event event) {
		boolean result = true;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		List<GameCupoun> gamesList = null;
		Map<Integer, String> status;

		try {
			gamesList = adminDAO.getGameByGameCupounId(event.getGameCuponId());
		} catch (DAOException e) {
			result = false;
			return result;
		}

		try {
			status = adminDAO.getStatusDictionaryData();
		} catch (DAOException e) {
			result = false;
			return result;
		}
		
		GameCupoun game = gamesList.get(0);
		String eventStatus = status.get(event.getStatus());

		if (event.getEndDate().before(event.getStartDate())) {
			result = false;
		}

		if (!(event.getStartDate().after(game.getEndDate()))) {
			result = false;
			return result;
		}

		Calendar cal = Calendar.getInstance();
		// event should end in 2 days since game cupoun end date
		cal.setTimeInMillis(game.getEndDate().getTime());
		cal.add(Calendar.DAY_OF_MONTH, 2);
		Timestamp gameEndDatePlus2 = new Timestamp(cal.getTime().getTime());

		if (!(event.getEndDate().before(gameEndDatePlus2))) {
			result = false;
			return result;
		}

		cal = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());
	
		// if event has passed we are able to set statuses Closed(result can not be Unknown-4) or Canselled(result should be Unknown-4)
		if (event.getEndDate().before(currentTimestamp)) {
			if (! eventStatus.equals(CLOSED) && ! eventStatus.equals(CANSELLED)) {
				result = false;
			}
			if (eventStatus.equals(CLOSED)) {
				if (event.getResultId() == UNKNOWN) {
					result = false;
					return result;
				}
			}
			if (eventStatus.equals(CANSELLED)) {
				if (event.getResultId() != UNKNOWN) {
					result = false;
					return result;
				}
			}
		}

		//event has't passed yet w are able to set any statuses except Closed(result - Unknown-4 only)
		if (event.getEndDate().after(currentTimestamp)) {
			if (eventStatus.equals(CLOSED)) {
				result = false;
				return result;
			}
			if (event.getResultId() != UNKNOWN) {
				result = false;
				return result;
			}
		}
		return result;
	}

	public static boolean dropDownValidation(String parameters) {
		boolean result = true;

		if (parameters.isEmpty()) {
			result = false;
		}
		return result;
	}

	public static boolean makeBetValidation(MakeBetDTO makeBetDTO, byte[] creditCardNumber) {
		if (creditCardNumber.length == 0) {
			return false;
		}
		if (makeBetDTO.getUserResultMap().size() < EVENTS_COUNT) {
			return false;
		}
		return true;
	}
}