package by.epamtr.totalizator.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import by.epamtr.totalizator.bean.entity.User;
import by.epamtr.totalizator.command.impl.DestroyConnectionPoolCommand;
import by.epamtr.totalizator.command.impl.InitConnectionPoolCommand;
import by.epamtr.totalizator.connectionpool.exception.ConnectionPoolException;
import by.epamtr.totalizator.dao.DAOFactory;
import by.epamtr.totalizator.dao.connectionpool.ConnectionPool;
import by.epamtr.totalizator.dao.exception.DAOException;
import by.epamtr.totalizator.dao.impl.DBInitDAO;




public class RegisterUserTest {

	
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
	@Ignore
	public void test() {
		Connection con = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
			try {
				con = connectionPool.takeConnection();
			} catch (ConnectionPoolException e) {
				fail("Connection failed.");
			}
			
		/*	private String firstName;
			private String lastName;
			private String sex;
			private String eMail;
			private String country;
			private String city;
			private String address;
			private String role;
			private int userId;*/
			
			User expectedUser = new User();
			expectedUser.setFirstName("Test");
			expectedUser.setLastName("Test");
			expectedUser.setSex("M");
	}

}
