package com.phoboss.finance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.BeforeClass;

import com.phoboss.finance.entities.Value;


/**
 * Unit test for simple App.
 */
public abstract class SimulatorVolatilityTest extends DBTestCase {

	protected Connection conn;
	
	@BeforeClass
	public void createDatabase() throws SQLException, ClassNotFoundException{
		// Load the HSQL Database Engine JDBC driver
        // hsqldb.jar should be in the class path or made part of the current jar
        Class.forName("org.hsqldb.jdbcDriver");

        // connect to the database.   This will load the db files and start the
        // database if it is not alread running.
        // db_file_name_prefix is used to open or create files that hold the state
        // of the db.
        // It can contain directory names relative to the
        // current working directory
        conn = DriverManager.getConnection("jdbc:hsqldb:sample",
                                           "sa",                     // username
                                           "");                      // password
        
        Statement st = conn.createStatement();
        st.execute("Create table Value");
	}
	
	
	@Before
	public void setUp() throws Exception{
		// Load the HSQL Database Engine JDBC driver
        // hsqldb.jar should be in the class path or made part of the current jar
        Class.forName("org.hsqldb.jdbcDriver");

        // connect to the database.   This will load the db files and start the
        // database if it is not alread running.
        // db_file_name_prefix is used to open or create files that hold the state
        // of the db.
        // It can contain directory names relative to the
        // current working directory
        conn = DriverManager.getConnection("jdbc:hsqldb:sample",
                                           "sa",                     // username
                                           "");                      // password
        
        Statement st = conn.createStatement();
        st.executeUpdate("Drop table if exists Value");
        st.executeUpdate("Create table Value(DAY DATE, CURRENT DOUBLE, PREVIOUS DOUBLE, DIVIDEND DOUBLE )");
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:sample" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
        super.setUp();
	}
	
	public List<Value> loadData(Date date) throws SQLException {
		List<Value> data = new ArrayList<Value>();
		String statement = "Select * from Value";
		if(date != null){
			statement+=" where day > '" + new SimpleDateFormat("yyyy-MM-dd").format(date)+"'";
		}
		PreparedStatement st = conn.prepareStatement(statement);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			Date day = rs.getDate("day");
			Double current = rs.getDouble("current");
			Double previous = rs.getDouble("previous");
			Double dividend = rs.getDouble("dividend");
			data.add(new Value(previous, current, day, dividend));
		}
		return data;
	}
	@Override
	protected abstract IDataSet getDataSet() throws DataSetException, IOException;
	
}
