package by.epamtr.totalizator.service.impl;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.bean.dto.GameCouponDTO;
import by.epamtr.totalizator.bean.dto.EventsListDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCoupon;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBAdminDAO;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;
import by.epamtr.totalizator.util.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * This class is the implementation of the
 * {@link by.epamtr.totalizator.service.AdminOperationService} for working with
 * database.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class AdminOperation implements AdminOperationService {

	@Override
	public boolean createNewGameCupoun(GameCouponDTO gameCupounDTO) throws ServiceException {

		boolean result = true;

		if (!Validator.newGameCupounInfoValidation(gameCupounDTO)) {
			result = false;
			return result;
		}

		Integer intMinBetAmount = Integer.parseInt(gameCupounDTO.getMinBetAmount());

		String correctStartDate = Utils.concatStringDate(gameCupounDTO.getStartDate(),
				gameCupounDTO.getStartTimeHours(), gameCupounDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(gameCupounDTO.getEndDate(), gameCupounDTO.getEndTimeHours(),
				gameCupounDTO.getEndTimeMinutes());
		Timestamp gameCuponStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameCuponEndDate = Timestamp.valueOf(correctEndDate);

		GameCoupon gameCupon = new GameCoupon();

		gameCupon.setStartDate(gameCuponStartDate);
		gameCupon.setEndDate(gameCuponEndDate);
		gameCupon.setMinBetAmount(intMinBetAmount);

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();

		try {
			if (!adminDAO.createNewGameCupoun(gameCupon)) {
				result = false;
			}

		} catch (DAOException e) {
			throw new ServiceException("Failed creating new game cupoun.", e);
		}
		return result;
	}

	@Override
	public boolean createNewEvent(EventDTO eventDTO) throws ServiceException {
		boolean result = true;

		if (!Validator.newEventInfoValidation(eventDTO)) {
			result = false;
			return result;
		}

		String correctStartDate = Utils.concatStringDate(eventDTO.getStartDate(), eventDTO.getStartTimeHours(),
				eventDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(eventDTO.getEndDate(), eventDTO.getEndTimeHours(),
				eventDTO.getEndTimeMinutes());
		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate = Timestamp.valueOf(correctEndDate);

		Event event = new Event();

		event.setEventName(eventDTO.getEventName());
		event.setStartDate(eventStartDate);
		event.setEndDate(eventEndDate);
		event.setTeamOne(eventDTO.getTeamOne());
		event.setTeamTwo(eventDTO.getTeamTwo());

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();

		try {
			if (!adminDAO.createNewEvent(event)) {
				result = false;
			}

		} catch (DAOException e) {
			throw new ServiceException("Failed creating new event.", e);
		}
		return result;
	}

	@Override
	public List<GameCoupon> getGamesInDevelopment() throws ServiceException {
		List<GameCoupon> gamesList = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();

		try {
			gamesList = adminDAO.getGamesInDevelopment();
		} catch (DAOException e) {
			throw new ServiceException("Failed showing games.", e);
		}
		return gamesList;
	}

	@Override
	public EventsListDTO getUnmatchedEvents(String parameters) throws ServiceException {
		List<Event> eventsList = null;
		EventsListDTO unmatchedEventsDTO = new EventsListDTO();

		if (!Validator.dropDownValidation(parameters)) {
			throw new ServiceException("invalid parameters.");
		}

		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();

		int gameCupounId = Integer.valueOf(Utils.parseParamGameCupounId(parameters));
		Timestamp gameStartDate = Timestamp.valueOf(Utils.parseParamGameCupounStartDate(parameters));
		Timestamp gameEndDate = Timestamp.valueOf(Utils.parseParamGameCupounEndDate(parameters));

		try {
			eventsList = adminDAO.getUnmatchedEvents(gameStartDate, gameEndDate);
			unmatchedEventsDTO.setEventList(eventsList);
			unmatchedEventsDTO.setGameCupounId(gameCupounId);
			unmatchedEventsDTO.setGameStartDate(gameStartDate);
			unmatchedEventsDTO.setGameEndDate(gameEndDate);
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return unmatchedEventsDTO;
	}

	@Override
	public boolean matchEventAndGame(String gameCupounId, String eventId) throws ServiceException {
		boolean result = true;

		if (!Validator.digitParameterValidation(gameCupounId)) {
			result = false;
			return result;
		}

		if (!Validator.digitParameterValidation(gameCupounId)) {
			result = false;
			return result;
		}

		int selectedEventId = Integer.valueOf(eventId);
		int selectedGameCupounId = Integer.valueOf(gameCupounId);

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			if (!adminDAO.matchEventAndGame(selectedGameCupounId, selectedEventId)) {
				result = false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed matching event and game.", e);
		}
		return result;
	}

	@Override
	public List<Event> getEventsByGameCupounId(int gameCupounId) throws ServiceException {
		List<Event> eventsList = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			eventsList = adminDAO.getEventsByGameCupounId(gameCupounId);
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return eventsList;
	}

	@Override
	public List<GameCoupon> getAllGames() throws ServiceException {
		List<GameCoupon> gamesList = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			gamesList = adminDAO.getAllGames();
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return gamesList;
	}

	@Override
	public boolean updateEvent(EventDTO eventDTO) throws ServiceException {
		boolean result = true;
		if (!Validator.updateEventValidation(eventDTO)) {
			result = false;
			return result;
		}

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();

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
			if (!adminDAO.updateEvent(event)) {
				result = false;
			}

		} catch (DAOException e) {
			throw new ServiceException("Failed updating an event.", e);
		}
		return result;
	}

	@Override
	public Map<Integer, String> getResultDictionaryData() throws ServiceException {
		Map<Integer, String> resultMap = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			resultMap = adminDAO.getResultDictionaryData();
		} catch (DAOException e) {
			throw new ServiceException("Failed showing results data.", e);
		}
		return resultMap;
	}

	@Override
	public Map<Integer, String> getStatusDictionaryData() throws ServiceException {
		Map<Integer, String> statusMap = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			statusMap = adminDAO.getStatusDictionaryData();
		} catch (DAOException e) {
			throw new ServiceException("Failed showing status data.", e);
		}
		return statusMap;
	}

	@Override
	public boolean unmatchEventAndGame(String eventId) throws ServiceException {
		boolean result = true;

		if (!Validator.digitParameterValidation(eventId)) {
			result = false;
			return result;
		}

		int selectedEventId = Integer.valueOf(eventId);

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			if (!adminDAO.unmatchEventAndGame(selectedEventId)) {
				result = false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed unmatching event and game.", e);
		}

		return result;
	}

	@Override
	public boolean deleteEvent(String eventId) throws ServiceException {
		boolean result = true;
		if (!Validator.digitParameterValidation(eventId)) {
			result = false;
			return result;
		}

		int selectedEventId = Integer.valueOf(eventId);
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			if (!adminDAO.deleteEvent(selectedEventId)) {
				result = false;
			}
		} catch (DAOException e) {
			throw new ServiceException("Failed to delete an event.", e);
		}

		return result;
	}

	@Override
	public GameCoupon getGameByGameCupounId(int gameCupounId) throws ServiceException {
		List<GameCoupon> gamesList = null;
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			gamesList = adminDAO.getGameByGameCupounId(gameCupounId);
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return gamesList.get(0);
	}

	@Override
	public int closeGameCoupon(String gameCouponId) throws ServiceException {
		int spResult = 0;
		if (!Validator.digitParameterValidation(gameCouponId)) {
			throw new ServiceException("Invalid parameters.");
		}

		int selectedGameCouponId = Integer.valueOf(gameCouponId);
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();

		try {
			spResult = adminDAO.closeGameCoupon(selectedGameCouponId);
		} catch (DAOException e) {
			throw new ServiceException("Failed to close game coupon.", e);
		}

		return spResult;
	}

	@Override
	public boolean updateGame(GameCouponDTO gameDTO) throws ServiceException {
		boolean result = true;
		if (!Validator.updateGameCouponValidation(gameDTO)) {
			result = false;
			return result;
		}

		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();

		String correctStartDate = Utils.concatStringDate(gameDTO.getStartDate(), gameDTO.getStartTimeHours(),
				gameDTO.getStartTimeMinutes());
		String correctEndDate = Utils.concatStringDate(gameDTO.getEndDate(), gameDTO.getEndTimeHours(),
				gameDTO.getEndTimeMinutes());

		Timestamp gameStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameEndDate = Timestamp.valueOf(correctEndDate);

		GameCoupon game = new GameCoupon();
		game.setGameCupounId(Integer.valueOf(gameDTO.getGameCupounId()));
		game.setStartDate(gameStartDate);
		game.setEndDate(gameEndDate);
		game.setMinBetAmount(Integer.valueOf(gameDTO.getMinBetAmount()));
		game.setJackpot(Integer.valueOf(gameDTO.getJackpot()));
		game.setStatus(Integer.valueOf(gameDTO.getStatus()));

		try {
			if (!adminDAO.updateGame(game)) {
				result = false;
			}

		} catch (DAOException e) {
			throw new ServiceException("Failed updating a game.", e);
		}
		return result;
	}

}
