package by.epamtr.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.dao.ClientDAO;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.exception.DAOException;

/**
 * This class is the implementation of the
 * {@link by.epamtr.totalizator.dao.ClientDAO} for working with database.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DBClientDAO implements ClientDAO {

	private final static Logger Logger = LogManager.getLogger(DBClientDAO.class.getName());

	private final static String REGISTRATION_USER = " INSERT INTO `user` (first_name,last_name,login,password,sex,e_mail,country,city,address,role)"
			+ "VALUES" + "(?,?,?,?,?,?,?,?,?,'user');";

	private final static String GET_EVENTS_INFO = "SELECT ev.event_id, " + "ev.event_name, " + "ev.game_cupon_id, "
			+ "ev.team_one, " + "ev.team_two, " + "ev.result_id, " + "ev.start_date, " + "ev.end_date, "
			+ "ev.status_id " + "FROM event as ev  JOIN game_cupon as gc " + "ON  ev.game_cupon_id = gc.game_cupon_id "
			+ "WHERE ev.game_cupon_id = ?" + " ORDER BY ev.event_id ;";

	private final static String GET_OPENED_GAME = "SELECT `game_cupon`.`game_cupon_id`," + " `game_cupon`.`start_date`,"
			+ " `game_cupon`.`end_date`," + " `game_cupon`.`min_bet_amount`," + " `game_cupon`.`game_cupon_pull`,"
			+ " `game_cupon`.`jackpot`" + " FROM `totalizator`.`game_cupon`" + " WHERE `game_cupon`.`status_id` = 1;";

	private final static String GET_OPENED_GAMES_COUNT = "SELECT COUNT(*)" + " FROM `totalizator`.`game_cupon`"
			+ " WHERE `game_cupon`.`status_id` = 1;";

	private final static String INSERT_INTO_BET = "INSERT INTO `totalizator`.`bet`" + " (`user_id`,"
			+ " `game_cupon_id`," + " `credit_card_number`," + " `bet_money_amount`," + " `transaction_date`)"
			+ " VALUES (?, ?, ?, ?, ?);";

	private final static String INSERT_INTO_USER_BET_DETAIL = "INSERT INTO `totalizator`.`user_bet_detail`"
			+ " (`bet_id`," + " `event_id`," + " `result_id`)" + "VALUES (?,?,?);";

	private final static String GET_LAST_INSERTED_ID = "SELECT LAST_INSERT_ID();";

	@Override
	public boolean registrationUser(User user, String login, String password) throws DAOException {
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

			ps = con.prepareStatement(REGISTRATION_USER);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, login);
			ps.setString(4, password);
			ps.setString(5, user.getSex());
			ps.setString(6, user.geteMail());
			ps.setString(7, user.getCountry());
			ps.setString(8, user.getCity());
			ps.setString(9, user.getAddress());

			if (ps.executeUpdate() == 0) {
				result = false;
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed user registration.", e1);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
		return result;
	}

	@Override
	public List<Event> getCurrentEvents(int gameCouponId) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Event> eventsList = new ArrayList<>();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			ps = con.prepareStatement(GET_EVENTS_INFO);
			ps.setInt(1, gameCouponId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Event event = new Event();
				event.setEventId(rs.getInt(1));
				event.setEventName(rs.getString(2));
				event.setGameCuponId(rs.getInt(3));
				event.setTeamOne(rs.getString(4));
				event.setTeamTwo(rs.getString(5));
				event.setStartDate(rs.getTimestamp(7));
				event.setEndDate(rs.getTimestamp(8));
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
	public GameCupoun getOpenedGame() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		GameCupoun game = new GameCupoun();

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		int openedGamesCount = getOpenedGamesCount();

		if (openedGamesCount != 1) {
			throw new DAOException("Invalid number of currently opened games.");
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_OPENED_GAME);

			while (rs.next()) {
				game.setGameCupounId(rs.getInt(1));
				game.setStartDate(rs.getTimestamp(2));
				game.setEndDate(rs.getTimestamp(3));
				game.setMinBetAmount(rs.getInt(4));
				game.setGameCuponPull(rs.getInt(5));
				game.setJackpot(rs.getInt(6));
				game.setStatus(1);
			}

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		return game;
	}

	@Override
	public boolean makeBet(MakeBetDTO makeBetDTO, String encryptedCardNumber) throws DAOException {
		boolean result = true;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Statement st = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.takeConnection();
			con.setAutoCommit(false);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			ps = con.prepareStatement(INSERT_INTO_BET);
			ps.setInt(1, makeBetDTO.getUser().getUserId());
			ps.setInt(2, Integer.valueOf(makeBetDTO.getGameCouponId()));

			ps.setString(3, encryptedCardNumber);
			ps.setInt(4, Integer.valueOf(makeBetDTO.getBetAmount()));
			ps.setTimestamp(5, currentTime);

			if (ps.executeUpdate() == 0) {
				result = false;
				return result;
			}
			ps.close();

			st = con.createStatement();
			rs = st.executeQuery(GET_LAST_INSERTED_ID);
			rs.next();
			int lastInsertedBetId = rs.getInt(1);

			rs.close();
			st.close();

			ps = con.prepareStatement(INSERT_INTO_USER_BET_DETAIL);
			ps.setInt(1, lastInsertedBetId);

			int eventId = 0;
			int resultId = 0;

			for (int i = 1; i < 16; i++) {
				eventId = makeBetDTO.getEventsList().get(i - 1).getEventId();
				resultId = Integer.valueOf(makeBetDTO.getUserResultMap().get("result" + new Integer(i).toString()));
				ps.setInt(2, eventId);
				ps.setInt(3, resultId);

				if (ps.executeUpdate() == 0) {
					result = false;
					return result;
				}
			}

			con.commit();

		} catch (SQLException e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
				Logger.error("Failed rollback a transaction.", e);
			}
			throw new DAOException("Database access error. Failed inserting bet data.", e1);
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				Logger.error("Failed setting AutoCommit = true.", e);
			}
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
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					Logger.error(e);
				}
			}
			connectionPool.closeConnection(con);
		}
		return result;
	}

	/**
	 * Gets quantity of currently opened game coupons in the system (game
	 * coupon's status is Opened).
	 * 
	 * @return quantity of currently opened game coupons.
	 * @throws DAOException
	 *             if obtaining information from the data storage fails.
	 */
	private int getOpenedGamesCount() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		int openedGamesCount = 0;

		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection failed.", e);
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_OPENED_GAMES_COUNT);
			rs.next();
			openedGamesCount = rs.getInt(1);

		} catch (SQLException e1) {
			throw new DAOException("Database access error. Failed data obtaining.", e1);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}

		return openedGamesCount;
	}

}
