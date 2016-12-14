package by.epamtr.totalizator.service;



import java.util.List;
import java.util.Map;

import by.epamtr.totalizator.bean.dto.EventDTO;
import by.epamtr.totalizator.bean.dto.GameCupounDTO;
import by.epamtr.totalizator.bean.dto.EventsListDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.service.exception.ServiceException;

public interface AdminOperationService {
	boolean createNewGameCupoun(GameCupounDTO gameCupounDTO) throws ServiceException ;
	boolean createNewEvent(EventDTO eventDTO) throws ServiceException ;
	boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId) throws ServiceException;
	boolean updateEvent(Event event) throws ServiceException;
	boolean unmatchEventAndGame(int selectedEventId) throws ServiceException;
	boolean deleteEvent(int selectedEventId) throws ServiceException;
	List<GameCupoun> getGamesInDevelopment() throws ServiceException;
	List<GameCupoun> getAllGames() throws ServiceException;
	EventsListDTO getUnmatchedEvents(String parameters) throws ServiceException;
	List<Event> getEventsByGameCupounId(int gameCupounId) throws ServiceException;
	Map<Integer,String> getResultDictionaryData() throws ServiceException;
	Map<Integer,String> getStatusDictionaryData() throws ServiceException;
}
