 package by.epamtr.totalizator.service.impl;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBAdminDAO;
import by.epamtr.totalizator.service.AdminOperationService;
import by.epamtr.totalizator.service.exception.ServiceException;
import java.sql.Timestamp;
import java.util.List;

public class AdminOperation implements AdminOperationService {

	@Override
	public boolean createNewGameCupoun(String startDate, String startTimeHours, String startTimeMinutes, String endDate,
			String endTimeHours, String endTimeMinutes, String minBetAmount) throws ServiceException {
		
		boolean result = true;
		
		if (!Validator.newGameCupounInfoValidation(startDate, startTimeHours, startTimeMinutes, endDate, endTimeHours, endTimeMinutes, minBetAmount)) {
			throw new ServiceException("invalid parameters.");
		}
		
		
		/*String startYear = startDate.substring(0, 4);
		String startMonth = startDate.substring(5,7);
		String startDay = startDate.substring(8);*/
		
		Integer intMinBetAmount = Integer.parseInt(minBetAmount);
	
		String correctStartDate = startDate + " " + startTimeHours + ":" + startTimeMinutes + ":" + "00";
		String correctEndDate = endDate + " " + endTimeHours + ":" + endTimeMinutes + ":" + "00";
		Timestamp gameCuponStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp gameCuponEndDate =  Timestamp.valueOf(correctEndDate);
		
		GameCupoun gameCupon = new GameCupoun();
		
		gameCupon.setStartDate(gameCuponStartDate);
		gameCupon.setEndDate(gameCuponEndDate);
		gameCupon.setMinBetAmount(intMinBetAmount);
		
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		
		try {
			if(!adminDAO.createNewGameCupoun(gameCupon)){
				result = false;
			}	
			
		} catch (DAOException e) {
			throw new ServiceException("Failed creating new game cupoun.", e);
		}
		return result;
	}

	@Override
	public boolean createNewEvent(String eventName, String startDate, String startTimeHours, String startTimeMinutes,
			String endDate, String endTimeHours, String endTimeMinutes, String teamOne, String teamTwo) throws ServiceException {
		boolean result = true;
		
		if (!Validator.newEventInfoValidation(eventName,startDate, startTimeHours, startTimeMinutes, endDate, endTimeHours, endTimeMinutes, teamOne, teamTwo)) {
			throw new ServiceException("invalid parameters.");
		}
		
		String correctStartDate = startDate + " " + startTimeHours + ":" + startTimeMinutes + ":" + "00";
		String correctEndDate = endDate + " " + endTimeHours + ":" + endTimeMinutes + ":" + "00";
		Timestamp eventStartDate = Timestamp.valueOf(correctStartDate);
		Timestamp eventEndDate =  Timestamp.valueOf(correctEndDate);
		
		Event event = new Event();
		
		event.setEventName(eventName);
		event.setStartDate(eventStartDate);
		event.setEndDate(eventEndDate);
		event.setTeamOne(teamOne);
		event.setTeamTwo(teamTwo);
		
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		
		try {
			if(!adminDAO.createNewEvent(event)){
				result = false;
			}	
			
		} catch (DAOException e) {
			throw new ServiceException("Failed creating new event.", e);
		}
		
		
		return result;
	}

	@Override
	public List<GameCupoun> showGamesInDevelopment() throws ServiceException {
		List<GameCupoun> gamesList = null;
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
	public List<Event> showUnmatchedEvents(String parameters) throws ServiceException {
		List<Event> eventsList = null;

		if (!Validator.dropDownValidation(parameters)) {
			throw new ServiceException("invalid parameters.");
		}
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getDBAdminDAO();
		
		/*int gameCupounId =Integer.valueOf(parameters.substring(0, 1));*/ 
		String startDate = parameters.substring(3,25);
		String endDate = parameters.substring(27);
		Timestamp gameStartDate = Timestamp.valueOf(startDate);
		Timestamp gameEndDate = Timestamp.valueOf(endDate);
		
		try {
			eventsList = adminDAO.getUnmatchedEvents(gameStartDate, gameEndDate);
		} catch (DAOException e) {
			throw new ServiceException("Failed showing events.", e);
		}
		return eventsList;
	}

	@Override
	public boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId) throws ServiceException {
		boolean result = true;
		// validation is not required all parameters are preselected by another commands
		
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		try {
			if(!adminDAO.matchEventAndGame(selectedGameCupounId, selectedEventId)){
				result = false;
			}	
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed matching event and game.", e);
		}
		
		
		return result;
	}

	@Override
	public List<Event> showEventsByGameCupounId(int gameCupounId) throws ServiceException {
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
	public List<GameCupoun> showAllGames() throws ServiceException {
		List<GameCupoun> gamesList = null;
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
	public boolean updateEvent(Event event) throws ServiceException {
		boolean result = true;
		//TODO VALIDATION
		if (!Validator.updateEventValidation(event)) {
			throw new ServiceException("invalid dates.");
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		
		try {
			if(!adminDAO.updateEvent(event)){
				result = false;
			}	
			
		} catch (DAOException e) {
			throw new ServiceException("Failed updating an event.", e);
		}
		
		return result;
	}

}
