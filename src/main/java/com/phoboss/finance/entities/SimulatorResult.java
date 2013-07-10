package com.phoboss.finance.entities;

import java.text.DecimalFormat;
import java.util.List;

public class SimulatorResult {

	private String stockName;
	private Double endAmount;
	private Double startAmount;
	private Integer startStocks;
	private Integer endStocks;
	private Double endAmountWithStocks;
	private Double profitability;
	private Double profitabilityWithStocks;
	private Double profitabilityLineal;
	private List<Value> values;
	
	public SimulatorResult(String stockName, Double endAmount, Integer startStocks, Integer endStocks,
			Double startAmount, List<Value> values) {
		super();
		this.stockName = stockName;
		this.endAmount = endAmount;
		this.startStocks = startStocks;
		this.endStocks = endStocks;
		this.startAmount = startAmount;
		this.values = values;
		if(!values.isEmpty()) {
			this.endAmountWithStocks = endAmount + (endStocks - startStocks) * values.get(values.size() - 1).getCurrent();
			this.profitability = (endAmount - startAmount) / startAmount;
			this.profitabilityWithStocks = (endAmountWithStocks - startAmount) / startAmount;
			this.profitabilityLineal = (values.get(values.size() - 1).getCurrent() - values.get(0).getCurrent()) / values.get(0).getCurrent();
		}
	}
	public Double getProfitabilityWithStocks() {
		return profitabilityWithStocks;
	}
	public String getStockName() {
		return stockName;
	}
	public Double getEndAmount() {
		return endAmount;
	}
	public Double getStartAmount() {
		return startAmount;
	}
	public Double getEndAmountWithStocks() {
		return endAmountWithStocks;
	}
	public Double getProfitability() {
		return profitability;
	}
	public Double getProfitabilityLineal() {
		return profitabilityLineal;
	}
	public Integer getStartStocks() {
		return startStocks;
	}
	public Integer getEndStocks() {
		return endStocks;
	}

	public Double getVolatility(){
		return new SimulatorVolatility(1, values).getVolatility();
	}
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuilder sb = new StringBuilder();
		sb = sb.append("Stock name: ").append(stockName).append("\n");
		sb = sb.append("Start shares: ").append(startStocks).append("\n");
		sb = sb.append("End shares: ").append(endStocks).append("\n");
		sb = sb.append("Start amount: ").append(df.format(startAmount)).append("\n");
		sb = sb.append("End amount: ").append(df.format(endAmount)).append("\n");
		sb = sb.append("End amount selling stocks: ").append(df.format(endAmountWithStocks)).append("\n");
		sb = sb.append("Profitability: ").append(df.format(profitability*100)).append("%\n");
		sb = sb.append("Profitability selling stocks: ").append(df.format(profitabilityWithStocks*100)).append("%\n");
		sb = sb.append("Linear profitability: ").append(df.format(profitabilityLineal*100)).append("%\n");
		sb = sb.append("Volatility: ").append(df.format(getVolatility() * 100)).append("%\n");
		return sb.toString();
	}
}
