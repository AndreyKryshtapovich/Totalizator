package by.epamtr.totalizator.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.AdminDAO;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;

/**
 * This class is the implementation of the
 * {@link by.epamtr.totalizator.dao.AdminDAO} for working with database.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DBAdminDAO implements AdminDAO {
	private final static Logger Logger = LogManager.getLogger(DBClientDAO.class.getName());

	private final static String INSERT_NEW_GAME_CUPOUN = "INSERT INTO `totalizator`.`game_cupon`" + "(`start_date`,"
			+ "`end_date`," + "`min_bet_amount`," + "`game_cupon_pull`," + "`jackpot`," + "`status_id`)" + "VALUES"
			+ "(?,?,?,?,?,?);";

	private final static String INSERT_NEW_EVENT = "INSERT INTO `totalizator`.`event`" + "(`event_name`,"
			+ "`team_one`," + "`team_two`," + "`start_date`," + "`end_date`," + "`status_id`)" + "VALUES"
			+ "(?,?,?,?,?,?);";
	private final static String GET_GAMES_IN_DEVELOPMENT = "SELECT `game_cupon`.`game_cupon_id`,"
			+ " `game_cupon`.`start_date`," + "`game_cupon`.`end_date`," + "`game_cupon`.`min_bet_amount`,"
			+ "`game_cupon`.`game_cupon_pull`," + "`game_cupon`.`jackpot`," + "`game_cupon`.`status_id`"
			+ "FROM `totalizator`.`game_cupon`" + "WHERE  `game_cupon`.`status_id` = 5;";

	private final static String GET_UNMATCHED_EVENTS = "SELECT `event`.`event_id`," + "`event`.`event_name`,"
			+ "`event`.`team_one`," + "`event`.`team_two`," + "`event`.`result_id`," + "`event`.`start_date`,"
			+ "`event`.`end_date`," + "`event`.`status_id`" + " FROM `totalizator`.`event`"
			+ " WHERE `event`.`game_cupon_id` IS NULL" + " AND `event`.`start_date` > ? AND `event`.`end_date` <= ? ;";

	private final static String GAME_MATTCHED_EVENTS_COUNT = " SELECT COUNT(*) FROM totalizator.event"
			+ " WHERE `event`.`game_cupon_id` = ?;";

	private final static String EVENT_GAME_MATCHING = "UPDATE `totalizator`.`event`" + " SET" + " `game_cupon_id` = ?"
			+ " WHERE `event_id` = ?;";

	private final static String GET_EVENTS_BY_GAME_CUPOUN_ID = "SELECT ev.event_name," + " ev.team_one,"
			+ " ev.team_two," + " coalesce(r.result_abbreviation,'')," + " ev.start_date," + " ev.end_date,"
			+ " s.status_description," + " ev.status_id," + " ev.result_id," + " ev.event_id"
			+ " FROM `totalizator`.`event` as ev JOIN `totalizator`.status as s ON ev.status_id = s.status_id"
			+ " LEFT JOIN `totalizator`.result as r on ev.result_id = r.result_id" + " WHERE ev.game_cupon_id = ?"
			+ " ORDER BY ev.event_id;"; // added order by

	public final static String GET_ALL_GAMES = "SELECT `game_cupon`.`game_cupon_id`," + " `game_cupon`.`start_date`,"
			+ " `game_cupon`.`end_date`," + " `game_cupon`.`min_bet_amount`," + " `game_cupon`.`game_cupon_pull`,"
			+ " `game_cupon`.`jackpot`," + " `game_cupon`.`status_id`" + " FROM `totalizator`.`game_cupon`"
			+ " WHERE  `game_cupon`.`status_id` IN (1,2,5);"; // changed

	public final static String GET_GAME_BY_GAME_COUPON_ID = "SELECT `game_cupon`.`game_cupon_id`,"
			+ " `game_cupon`.`start_date`," + " `game_cupon`.`end_date`," + " `game_cupon`.`min_bet_amount`,"
			+ " `game_cupon`.`game_cupon_pull`," + " `game_cupon`.`jackpot`," + " `game_cupon`.`status_id`"
			+ " FROM `totalizator`.`game_cupon`" + " WHERE  `game_cupon`.`game_cupon_id` = ?;";

	public final static String UPDATE_EVENT = "UPDATE `totalizator`.`event`" + " SET" + " `event_name` = ?,"
			+ " `game_cupon_id` = ?," + " `team_one` = ?," + " `team_two` = ?," + " `result_id` = ?,"
			+ " `start_date` = ?," + " `end_date` = ?," + " `status_id` = ?" + " WHERE `event_id` = ?;";

	public final static String GET_RESULT_DICTIONARY_DATA = "SELECT `result`.`result_id`,"
			+ "  `result`.`result_abbreviation`," + " `result`.`result_note`" + " FROM `totalizator`.`result`;";

	public final static String GET_STATUS_DICTIONARY_DATA = "SELECT `status`.`status_id`,"
			+ " `status`.`status_description`" + " FROM `totalizator`.`status`;";

	private final static String UNMATCH_EVENT_AND_GAME = "UPDATE `totalizator`.`event`" + " SET"
			+ " `game_cupon_id` = null" + " WHERE `event_id` = ?;";

	private final static String DELETE_EVENT = "DELETE FROM `totalizator`.`event`"
			+ " WHERE `totalizator`.`event`.event_id = ?;";

	private final static String CLOSE_GAME_COUPON = "{ call close_game_coupon(?,?) }";

	private final static String UPDATE_GAME_COUPON = " UPDATE `totalizator`.`game_cupon`" + " SET"
			+ " `start_date` = ?," + " `end_date` = ?," + " `min_bet_amount` = ?," + " `jackpot` = ?,"
			+ " `status_id` = ?" + " WHERE `game_cupon_id` = ?;";

	@Override
	public boolean createNewGameCupoun(GameCupoun gameCupoun) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {

			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}

			ps = con.prepareStatement(INSERT_NEW_GAME_CUPOUN);
			ps.setTimestamp(1, gameCupoun.getStartDate());
			ps.setTimestamp(2, gameCupoun.getEndDate());
			ps.setInt(3, gameCupoun.getMinBetAmount());
			ps.setInt(4, gameCupoun.getGameCuponPull());
			ps.setInt(5, gameCupoun.getJackpot());
			ps.setInt(6, gameCupoun.getStatus());

			if (ps.executeUpdate() == 0) {
				result = false;
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed creation new game.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

		return result;
	}

	@Override
	public boolean createNewEvent(Event event) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {

			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}

			ps = con.prepareStatement(INSERT_NEW_EVENT);
			ps.setString(1, event.getEventName());
			ps.setString(2, event.getTeamOne());
			ps.setString(3, event.getTeamTwo());
			ps.setTimestamp(4, event.getStartDate());
			ps.setTimestamp(5, event.getEndDate());
			/**
			 * by default status = 1 (opened)
			 */
			ps.setInt(6, 1);

			if (ps.executeUpdate() == 0) {
				result = false;
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed creation new event.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

		return result;
	}

	@Override
	public List<GameCupoun> getGamesInDevelopment() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<GameCupoun> gamesList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_GAMES_IN_DEVELOPMENT);

			while (rs.next()) {
				GameCupoun gameCupoun = new GameCupoun();
				gameCupoun.setGameCupounId(rs.getInt(1));
				gameCupoun.setStartDate(rs.getTimestamp(2));
				gameCupoun.setEndDate(rs.getTimestamp(3));
				gameCupoun.setMinBetAmount(rs.getInt(4));
				gameCupoun.setGameCuponPull(rs.getInt(5));
				gameCupoun.setJackpot(rs.getInt(6));
				gameCupoun.setStatus(rs.getInt(7));
				gamesList.add(gameCupoun);
			}
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		return gamesList;
	}

	@Override
	public List<Event> getUnmatchedEvents(Timestamp gameStartDate, Timestamp gameEndDate) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Event> eventsList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			Calendar cal = Calendar.getInstance();
			/**
			 * event should end in 2 days since game coupon end date
			 * (gameCouponEndDate + 2 days). Event should start after
			 * gameCouponEndDate.
			 */
			cal.setTimeInMillis(gameEndDate.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 2);
			Timestamp gameEndDatePlus2 = new Timestamp(cal.getTime().getTime());
			ps = con.prepareStatement(GET_UNMATCHED_EVENTS);
			ps.setTimestamp(1, gameEndDate);
			ps.setTimestamp(2, gameEndDatePlus2);
			rs = ps.executeQuery();

			while (rs.next()) {

				Event event = new Event();
				event.setEventId(rs.getInt(1));
				event.setEventName(rs.getString(2));
				event.setTeamOne(rs.getString(3));
				event.setTeamTwo(rs.getString(4));
				event.setResultId(rs.getInt(5));
				event.setStartDate(rs.getTimestamp(6));
				event.setEndDate(rs.getTimestamp(7));
				event.setStatus(rs.getInt(8));
				eventsList.add(event);
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
		return eventsList;
	}

	@Override
	public boolean matchEventAndGame(int selectedGameCupounId, int selectedEventId) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			ps = con.prepareStatement(GAME_MATTCHED_EVENTS_COUNT);
			ps.setInt(1, selectedGameCupounId);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getInt(1) >= 15) {
					result = false;
					return result;
				}
			}
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.error(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					Logger.error(e);
				}
			}
		}

		try {
			ps = con.prepareStatement(EVENT_GAME_MATCHING);
			ps.setInt(1, selectedGameCupounId);
			ps.setInt(2, selectedEventId);
			if (ps.executeUpdate() == 0) {
				result = false;
			}
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed creation new event.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
		return result;
	}

	@Override
	public List<Event> getEventsByGameCupounId(int gameCupounId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Event> eventsList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			ps = con.prepareStatement(GET_EVENTS_BY_GAME_CUPOUN_ID);
			ps.setInt(1, gameCupounId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Event event = new Event();
				event.setEventName(rs.getString(1));
				event.setTeamOne(rs.getString(2));
				event.setTeamTwo(rs.getString(3));
				event.setResultAbbreviation(rs.getString(4));
				event.setStartDate(rs.getTimestamp(5));
				event.setEndDate(rs.getTimestamp(6));
				event.setStatusDescription(rs.getString(7));
				event.setStatus(rs.getInt(8));
				event.setResultId(rs.getInt(9));
				event.setEventId(rs.getInt(10));
				event.setGameCuponId(gameCupounId);
				eventsList.add(event);
			}
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
		return eventsList;
	}

	@Override
	public List<GameCupoun> getAllGames() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<GameCupoun> gamesList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_ALL_GAMES);

			while (rs.next()) {
				GameCupoun gameCupoun = new GameCupoun();
				gameCupoun.setGameCupounId(rs.getInt(1));
				gameCupoun.setStartDate(rs.getTimestamp(2));
				gameCupoun.setEndDate(rs.getTimestamp(3));
				gameCupoun.setMinBetAmount(rs.getInt(4));
				gameCupoun.setGameCuponPull(rs.getInt(5));
				gameCupoun.setJackpot(rs.getInt(6));
				gameCupoun.setStatus(rs.getInt(7));
				gamesList.add(gameCupoun);
			}
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		return gamesList;
	}

	@Override
	public boolean updateEvent(Event event) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}

			ps = con.prepareStatement(UPDATE_EVENT);
			ps.setString(1, event.getEventName());
			ps.setInt(2, event.getGameCuponId());
			ps.setString(3, event.getTeamOne());
			ps.setString(4, event.getTeamTwo());
			ps.setInt(5, event.getResultId());
			ps.setTimestamp(6, event.getStartDate());
			ps.setTimestamp(7, event.getEndDate());
			ps.setInt(8, event.getStatus());
			ps.setInt(9, event.getEventId());

			if (ps.executeUpdate() == 0) {
				result = false;
			}

			return result;
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed updating an event.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
	}

	@Override
	public List<GameCupoun> getGameByGameCupounId(int gameCupounId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<GameCupoun> gamesList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {

			ps = con.prepareStatement(GET_GAME_BY_GAME_COUPON_ID);
			ps.setInt(1, gameCupounId);
			rs = ps.executeQuery();

			while (rs.next()) {
				GameCupoun gameCupoun = new GameCupoun();
				gameCupoun.setGameCupounId(rs.getInt(1));
				gameCupoun.setStartDate(rs.getTimestamp(2));
				gameCupoun.setEndDate(rs.getTimestamp(3));
				gameCupoun.setMinBetAmount(rs.getInt(4));
				gameCupoun.setGameCuponPull(rs.getInt(5));
				gameCupoun.setJackpot(rs.getInt(6));
				gameCupoun.setStatus(rs.getInt(7));
				gamesList.add(gameCupoun);
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
		return gamesList;
	}

	@Override
	public Map<Integer, String> getResultDictionaryData() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Map<Integer, String> resultMap = new HashMap<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {

			st = con.createStatement();
			rs = st.executeQuery(GET_RESULT_DICTIONARY_DATA);

			while (rs.next()) {
				if (rs.getInt(1) == 4) {
					resultMap.put(rs.getInt(1), rs.getString(3));
				} else {
					resultMap.put(rs.getInt(1), rs.getString(2));
				}
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		return resultMap;
	}

	@Override
	public Map<Integer, String> getStatusDictionaryData() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Map<Integer, String> statusMap = new HashMap<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {

			st = con.createStatement();
			rs = st.executeQuery(GET_STATUS_DICTIONARY_DATA);

			while (rs.next()) {
				statusMap.put(rs.getInt(1), rs.getString(2));
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		return statusMap;
	}

	@Override
	public boolean unmatchEventAndGame(int selectedEventId) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			ps = con.prepareStatement(UNMATCH_EVENT_AND_GAME);
			ps.setInt(1, selectedEventId);

			if (ps.executeUpdate() == 0) {
				result = false;
			}

			return result;

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed unmatching event and game.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

	}

	@Override
	public boolean deleteEvent(int selectedEventId) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			ps = con.prepareStatement(DELETE_EVENT);
			ps.setInt(1, selectedEventId);

			if (ps.executeUpdate() == 0) {
				result = false;
			}

			return result;

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed to delete an event.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
	}

	@Override
	public int closeGameCoupon(int gameCouponId) throws DAOException {
		int spResult = 0;
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		CallableStatement cs = null;
		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			cs = con.prepareCall(CLOSE_GAME_COUPON);
			cs.setInt(1, gameCouponId);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.execute();
			spResult = cs.getInt(2);
			return spResult;
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed to close game coupon.", e1);
		} finally {

			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					Logger.error(e);
				}
			}

			connectionPool.closeConnection(con);
		}
	}

	@Override
	public boolean updateGame(GameCupoun game) throws DAOException {
		boolean result = true;
		Connection con = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}

			ps = con.prepareStatement(UPDATE_GAME_COUPON);
			ps.setTimestamp(1, game.getStartDate());
			ps.setTimestamp(2, game.getEndDate());
			ps.setInt(3, game.getMinBetAmount());
			ps.setInt(4, game.getJackpot());
			ps.setInt(5, game.getStatus());
			ps.setInt(6, game.getGameCupounId());

			if (ps.executeUpdate() == 0) {
				result = false;
			}

			return result;
		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed updating a game.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
	}

}
