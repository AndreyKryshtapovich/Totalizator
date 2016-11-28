package by.epamtr.totalizator.service;


import java.sql.Timestamp;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.service.exception.ServiceException;

public interface AdminOperationService {
	boolean createNewGameCupoun(String startDate, String startTimeHours, String startTimeMinutes, String endDate, String endTimeHours, String endTimeMinutes,String minBetAmount) throws ServiceException ;
	boolean createNewEvent(String eventName ,String startDate, String startTimeHours, String startTimeMinutes, String endDate, String endTimeHours, String endTimeMinutes, String teamOne, String teamTwo) throws ServiceException ;
	boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId) throws ServiceException;
	boolean updateEvent(Event event) throws ServiceException;
	List<GameCupoun> showGamesInDevelopment() throws ServiceException;
	List<GameCupoun> showAllGames() throws ServiceException;
	//List<Event> showUnmatchedEvents(Timestamp gameStartDate, Timestamp gameEndDate) throws ServiceException;
	List<Event> showUnmatchedEvents(String parameters) throws ServiceException;
	List<Event> showEventsByGameCupounId(int gameCupounId) throws ServiceException;
//	List<Event> showEventsByGameCupounId(String parameters) throws ServiceException;
}
