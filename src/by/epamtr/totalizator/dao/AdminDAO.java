package by.epamtr.totalizator.dao;

import java.sql.Timestamp;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.dao.exception.DAOException;

public interface AdminDAO {
	boolean createNewGameCupoun (GameCupoun gameCupoun)throws DAOException;
	boolean createNewEvent(Event event) throws DAOException;
	boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId)throws DAOException;
	boolean updateEvent(Event event) throws DAOException;
	List<GameCupoun> getGamesInDevelopment() throws DAOException;
	List<GameCupoun> getAllGames() throws DAOException;
	List<Event> getUnmatchedEvents(Timestamp gameStartDate, Timestamp gameEndDate) throws DAOException;
	List<Event> getEventsByGameCupounId(int gameCupounId) throws DAOException;
	List<GameCupoun> getGameByByGameCupounId(int gameCupounId) throws DAOException; 
}
