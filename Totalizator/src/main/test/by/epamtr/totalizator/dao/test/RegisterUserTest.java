package by.epamtr.totalizator.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.impl.DestroyConnectionPoolCommand;
import by.epamtr.totalizator.command.impl.InitConnectionPoolCommand;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBClientDAO;
import junit.framework.Assert;

public class RegisterUserTest {
	
	private final static Logger Logger = LogManager.getLogger(RegisterUserTest.class.getName());
	private final static String GET_USER_ROW_AMOUNT = " SELECT COUNT(*) FROM `totalizator`.`user`;";
	private final static String GET_USER_DATA = " SELECT  "
			+ " `user`.`first_name`,"
			+ " `user`.`last_name`,"
			+ " `user`.`login`,"
			+ " `user`.`password`,"
			+ " `user`.`sex`,"
			+ " `user`.`e_mail`,"
			+ " `user`.`country`,"
			+ " `user`.`city`,"
			+ " `user`.`address`,"
			+ " `user`.`role`"
			+ " FROM `totalizator`.`user`"
			+ " WHERE `user`.user_id = ( SELECT MAX(user_id) FROM `user`);";
	
	private final static String DELETE_TEST_USER = "DELETE FROM `totalizator`.`user`"
			+ " WHERE `user`.user_id = ( SELECT q.id FROM ( SELECT MAX(user_id) as id FROM `user`) as q );";
	
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

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int rowAmountBeforeTest = 0;
		int rowAmountAfterTest = 0;
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				Logger.error(e);
				fail("Connection failed.");
			}
			
			try {
				st = con.createStatement();
				rs =st.executeQuery(GET_USER_ROW_AMOUNT);
				rs.next();
				rowAmountBeforeTest = rs.getInt(1);
			} catch (SQLException e) {
				Logger.error(e);
				fail("Failed obtaining initial info from table user.");
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
				
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
			}
			
			User expectedUser = new User();
			expectedUser.setFirstName("Test");
			expectedUser.setLastName("Test");
			expectedUser.setSex("M");
			expectedUser.seteMail("test@test.com");
			expectedUser.setCountry("Test");
			expectedUser.setCity("Test");
			expectedUser.setAddress("Test");
			expectedUser.setRole("user");
			String expectedLogin = "Test";
			String expectedPassword =  DigestUtils.md5Hex("0987");
			
			DAOFactory factory = DAOFactory.getInstance();
			DBClientDAO clientDAO = factory.getDBClientDAO();
			
			try {
				clientDAO.registrationUser(expectedUser ,expectedLogin, expectedPassword);
			} catch (DAOException e) {
				Logger.error(e);
				fail("Error in DAO method occured.");
			}
			
			try {
				st = con.createStatement();
				rs =st.executeQuery(GET_USER_ROW_AMOUNT);
				rs.next();
				rowAmountAfterTest = rs.getInt(1);
			} catch (SQLException e) {
				Logger.error(e);
				fail("Failed obtaining info from table user after method call.");
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
				
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
			}
			
			Assert.assertEquals("Invalid number of rows.", rowAmountBeforeTest, rowAmountAfterTest - 1, 0.01);
			
			
			try {
				st = con.createStatement();
				rs =st.executeQuery(GET_USER_DATA);
				while (rs.next()) {
					assertEquals("First Name mismatch.", expectedUser.getFirstName(),rs.getString(1));
					assertEquals("Last Name mismatch.", expectedUser.getLastName(),rs.getString(2));
					assertEquals("Login mismatch.", expectedLogin, rs.getString(3));
					assertEquals("Password mismatch.", expectedPassword, rs.getString(4));
					assertEquals("Sex mismatch.", expectedUser.getSex(), rs.getString(5));
					assertEquals("Email mismatch.", expectedUser.geteMail(), rs.getString(6));
					assertEquals("Country mismatch.", expectedUser.getCountry(), rs.getString(7));
					assertEquals("City mismatch.", expectedUser.getCity(), rs.getString(8));
					assertEquals("Address mismatch.", expectedUser.getAddress(), rs.getString(9));
					assertEquals("Role mismatch.", expectedUser.getRole(), rs.getString(10));
				}
			} catch (SQLException e) {
				Logger.error(e);
				fail("Failed obtaining user data.");
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
				
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						Logger.error(e);
						fail("Failed closing resources.");
					}
				}
				
			}
			
			
				try {
					st = con.createStatement();
					st.executeUpdate(DELETE_TEST_USER);
				} catch (SQLException e) {
						Logger.error(e);
						fail("Failed deletting test user.");
				}finally{
					connectionPool.closeConnection(con, st);
				}
			

	}

}
