package by.epamtr.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.GeneralDAO;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;

public class DBGeneralDAO implements GeneralDAO {
	private final static String GET_USER_INFO = "SELECT " + "`user`.`first_name`," + "`user`.`last_name`,"
			+ "`user`.`login`," + "`user`.`password`," + "`user`.`sex`," + "`user`.`e_mail`," + "`user`.`country`,"
			+ "`user`.`city`," + "`user`.`address`," + "`user`.`role`" + "FROM `totalizator`.`user`"
			+ "WHERE `user`.`login` = ? and `user`.`password` = ?;";

	@Override
	public User signInCheck(String login, String password) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				throw new DAOException("Connection failed.", e);
			}

			ps = con.prepareStatement(GET_USER_INFO);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (login.equals(rs.getString(3)) && password.equals(rs.getString(4))) {
					user = new User();
					user.setAddress(rs.getString(9));
					user.setCity(rs.getString(8));
					user.setCountry(rs.getString(7));
					user.seteMail(rs.getString(6));
					user.setFirstName(rs.getString(1));
					user.setLastName(rs.getString(2));
					user.setSex(rs.getString(5));
					user.setRole(rs.getString(10));
				}
			}

		} catch (SQLException e) {
			throw new DAOException("Database access error.", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
		return user;
	}

}
