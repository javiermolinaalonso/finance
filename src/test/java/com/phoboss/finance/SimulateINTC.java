package com.phoboss.finance;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Test;

import com.phoboss.finance.entities.Value;
import com.phoboss.finance.service.SimulatorServiceImpl;
import com.phoboss.finance.strategy.VolatilityStrategy;

public class SimulateINTC extends SimulatorVolatilityTest {

	@Test
	public void testSantander() throws SQLException{
		Calendar cal = Calendar.getInstance();
		cal.set(2005, 1, 1);
		List<Value> data = loadData(cal.getTime());
		Collections.reverse(data);
		SimulatorServiceImpl s = new SimulatorServiceImpl("INTC", 10000d, 0, data, new VolatilityStrategy(3d, 3d));
		s.simulate();
		System.out.println(s.getResult());
	}
	
	@Override
	protected IDataSet getDataSet() throws DataSetException, IOException {
		return new XlsDataSet(new File("./src/test/resources/INTCAdjusted.xls"));
	}
}
