package by.epamtr.totalizator.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.command.impl.DestroyConnectionPoolCommand;
import by.epamtr.totalizator.command.impl.InitConnectionPoolCommand;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBAdminDAO;


public class UpdateGameCouponTest {
	private final static Logger Logger = LogManager.getLogger(UpdateGameCouponTest.class.getName());
	
	private final static String INSERT_TEST_GAME = "  INSERT INTO `game_cupon`(start_date,end_date,min_bet_amount,game_cupon_pull,jackpot,status_id)"
			+ " VALUES"
			+ " ('2016-01-01','2016-01-05',30,0,0,1);";
	
	private final static String DELETE_TEST_GAME = " DELETE FROM `totalizator`.`game_cupon`"
			+ " WHERE `game_cupon`.game_cupon_id = ( SELECT q.id FROM ( SELECT MAX(game_cupon_id) as id FROM `game_cupon`) as q );";
	
	private final static String GET_TEST_GAME = "SELECT "
			+ " `game_cupon`.`start_date`,"
			+ " `game_cupon`.`end_date`,"
			+ " `game_cupon`.`min_bet_amount`,"
			+ " `game_cupon`.`jackpot`,"
			+ " `game_cupon`.`status_id`"
			+ " FROM `totalizator`.`game_cupon`"
			+ " WHERE `game_cupon`.game_cupon_id = ( SELECT q.id FROM ( SELECT MAX(game_cupon_id) as id FROM `game_cupon`) as q );";

	private final static String GET_LAST_GAME_ID = "SELECT MAX(game_cupon_id) from `totalizator`.`game_cupon`;";
	
	private final static int CANSELLED = 4;
	
	private final static String START_DATE = "2017-01-21 00:00:00";
	private final static String END_DATE = "2017-01-25 23:59:00";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitConnectionPoolCommand command = new InitConnectionPoolCommand();
		command.execute();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DestroyConnectionPoolCommand command = new DestroyConnectionPoolCommand();
		command.execute();
	}

	@Test
	public void test() {
		
		 insertTestGameCoupon();
		
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			Logger.error(e);
			fail("Connection failed.");
		}
		
		int lastGameId = 0;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_LAST_GAME_ID);
			rs.next();
			lastGameId = rs.getInt(1);
		} catch (SQLException e) {
			Logger.error(e);
			fail("Failed obtaining game coupon data.");
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		
		GameCupoun expectedGame = new GameCupoun();
		Timestamp gameStartDate = Timestamp.valueOf(START_DATE);
		expectedGame.setStartDate(gameStartDate);
		
		Timestamp gameEndDate = Timestamp.valueOf(END_DATE);
		expectedGame.setEndDate(gameEndDate);
		
		expectedGame.setMinBetAmount(21);
		expectedGame.setJackpot(1000);
		expectedGame.setStatus(CANSELLED);
		expectedGame.setGameCupounId(lastGameId);
		
		DAOFactory factory = DAOFactory.getInstance();
		DBAdminDAO adminDAO = factory.getDBAdminDAO();
		
		try {
			adminDAO.updateGame(expectedGame);
		} catch (DAOException e) {
			Logger.error(e);
			fail("Error in DAO method occured.");
		}
		
		checkUpdatedGame(expectedGame);
		
		 deleteTestGameCoupon();
	}
	
	
	private void checkUpdatedGame(GameCupoun expectedGame){
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			Logger.error(e);
			fail("Connection failed.");
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(GET_TEST_GAME);
			while (rs.next()) {	
				assertEquals("Start Date mismatch.", expectedGame.getStartDate(), rs.getTimestamp(1));
				assertEquals("End Date mismatch.", expectedGame.getEndDate(), rs.getTimestamp(2));
				assertEquals("Min Bet Amount mismatch.", expectedGame.getMinBetAmount(), rs.getInt(3));
				assertEquals("Jackpot mismatch.", expectedGame.getJackpot(), rs.getInt(4));
				assertEquals("Status mismatch.", expectedGame.getStatus(), rs.getInt(5));
			}
		} catch (SQLException e) {
			Logger.error(e);
			fail("Failed obtaining user data.");
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}
		
	}
	
	
	private void insertTestGameCoupon(){
		Statement st = null;
		Connection con = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			Logger.error(e);
			fail("Connection failed.");
		}
	
		try {
			st = con.createStatement();
			st.executeUpdate(INSERT_TEST_GAME);
		} catch (SQLException e) {
			Logger.error(e);
			fail("Failed inserting test game coupon.");
		} finally {
			connectionPool.closeConnection(con, st);
		}
	}
	
	private void deleteTestGameCoupon(){
		Statement st = null;
		Connection con = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			con = connectionPool.takeConnection();
		} catch (ConnectionPoolException e) {
			Logger.error(e);
			fail("Connection failed.");
		}
		
		try {
			st = con.createStatement();
			st.executeUpdate(DELETE_TEST_GAME);
		} catch (SQLException e) {
			Logger.error(e);
			fail("Failed deletting test game coupon.");
		} finally {
			connectionPool.closeConnection(con, st);
		}
	}

}
