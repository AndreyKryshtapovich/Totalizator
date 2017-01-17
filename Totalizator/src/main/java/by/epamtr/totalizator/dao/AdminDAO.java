package by.epamtr.totalizator.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.exception.DAOException;

public interface AdminDAO {
	boolean createNewGameCupoun (GameCupoun gameCupoun)throws DAOException;
	boolean createNewEvent(Event event) throws DAOException;
	boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId)throws DAOException;
	boolean updateEvent(Event event) throws DAOException;
	boolean updateGame(GameCupoun game) throws DAOException;
	boolean unmatchEventAndGame(int selectedEventId)throws DAOException;
	boolean deleteEvent(int selectedEventId) throws DAOException;
	int closeGameCoupon(int gameCouponId) throws DAOException;
	List<GameCupoun> getGamesInDevelopment() throws DAOException;
	List<GameCupoun> getAllGames() throws DAOException;
	List<Event> getUnmatchedEvents(Timestamp gameStartDate, Timestamp gameEndDate) throws DAOException;
	List<Event> getEventsByGameCupounId(int gameCupounId) throws DAOException;
	List<GameCupoun> getGameByGameCupounId(int gameCupounId) throws DAOException; 
	Map<Integer,String> getResultDictionaryData() throws DAOException;
	Map<Integer,String> getStatusDictionaryData() throws DAOException;
}
