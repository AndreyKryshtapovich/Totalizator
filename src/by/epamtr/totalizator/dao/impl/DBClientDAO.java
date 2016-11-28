package by.epamtr.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.ClientDAO;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;

public class DBClientDAO implements ClientDAO {
	private final static String REGISTRATION_USER = " INSERT INTO `user` (first_name,last_name,login,password,sex,e_mail,country,city,address,role)"
			+ "VALUES" + "(?,?,?,?,?,?,?,?,?,'user');";
	
	private final static String GET_EVENTS_INFO = "SELECT ev.event_id, "
			+ "ev.event_name, "
			+ "ev.game_cupon_id, "
			+ "ev.team_one, "
			+ "ev.team_two, "
			+ "ev.result_id, "
			+ "ev.start_date, "
			+ "ev.end_date, "
			+ "ev.status_id "
			+ "FROM event as ev  JOIN game_cupon as gc "
			+ "ON  ev.game_cupon_id = gc.game_cupon_id "
			+ "WHERE ev.game_cupon_id = 1;";


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
	public List<Event> getCurrentEvents() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		List<Event> eventsList = new ArrayList<>();
			
			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}
			
			try {
				st = con.createStatement();
				rs = st.executeQuery(GET_EVENTS_INFO);
				
				while (rs.next()) {
					Event event = new Event();
					event.setEventName(rs.getString(2));
					event.setGameCuponId(rs.getInt(3));
					event.setTeamOne(rs.getString(4));
					event.setTeamTwo(rs.getString(5));
					event.setStartDate(rs.getTimestamp(7));
					event.setEndDate(rs.getTimestamp(8));
					eventsList.add(event);
					
				}
			}catch (SQLException e1) {
				throw new DAOException("Database access error. Failed data obtaining.", e1);
			} finally {
				connectionPool.closeConnection(con, st,rs);
			}
		return eventsList;
	}

}
