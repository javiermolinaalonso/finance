package com.phoboss.finance.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

public class SimulatorVolatility {

	private Integer period;
	private Double volatility;

	public SimulatorVolatility(Integer period, List<Value> values) {
		this.period = period;
		this.updateVolatility(values);
	}

	private void updateVolatility(List<Value> values) {
		this.volatility = 0d;
		List<Double> acumVolatilities = new ArrayList<Double>();
		for (Value v : values) {
			if (v.getPrevious() != null && v.getPrevious() > 0d) {
				acumVolatilities.add((v.getCurrent() - v.getPrevious())
						/ v.getPrevious());
			}
		}
		double[] volatilities = new double[acumVolatilities.size()];
		for(int i = 0; i < acumVolatilities.size(); i++){
			volatilities[i] = acumVolatilities.get(i);
		}
		this.volatility = new StandardDeviation().evaluate(volatilities) * Math.sqrt(acumVolatilities.size());
	}

	public Double getVolatility() {
		return volatility;
	}
}
