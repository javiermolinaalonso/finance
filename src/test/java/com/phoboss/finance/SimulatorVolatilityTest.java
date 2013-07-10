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
	
	@Before
	public void setUp() throws Exception{
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://localhost:5432/finance" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "phoboss" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "phubituzz" );
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
