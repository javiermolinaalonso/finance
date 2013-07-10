package com.phoboss.finance.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.phoboss.finance.entities.SimulatorResult;
import com.phoboss.finance.entities.Value;
import com.phoboss.finance.enumerations.Actions;
import com.phoboss.finance.exceptions.FinanceException;
import com.phoboss.finance.strategy.Strategy;

public class SimulatorServiceImpl {
	
	private static final Logger logger = Logger.getLogger(SimulatorServiceImpl.class);
	//TODO Use a configurable moneyManagement
	private static final Double sellAmount = 500d;
	private static final Double buyAmount = 500d;
	
	private final String stockName;
	private final Double startMoney;
	private final Integer startShares;
	private final List<Value> values;
	private final Strategy strategy;
	
	private List<Value> processedValues;
	private Double endMoney;
	private Integer endShares;
//	private MoneyManagement management;
	private SimulatorResult result;
	
	public SimulatorServiceImpl(String stockName, Double startMoney, Integer startShares,
			List<Value> values, Strategy strategy) {
		super();
		this.stockName = stockName;
		this.startMoney = startMoney;
		this.endMoney = startMoney;
		this.startShares = startShares;
		this.endShares = startShares;
		this.values = values;
		this.strategy = strategy;
		this.processedValues = new ArrayList<Value>();
		this.result = new SimulatorResult(stockName, this.endMoney, this.startShares, this.endShares, this.startMoney, this.processedValues);
	}

	public void simulate(){
		logger.debug("Simulating...");
		for(Value v : values){
			try{
				processAction(strategy.evaluate(v), v);
				processedValues.add(v);
			}catch(FinanceException fe){
				logger.warn("The action cannot be done: " + fe.getMessage());
			}
			doPostProcess();
		}
	}

	private void doPostProcess(){
		this.result = new SimulatorResult(stockName, this.endMoney, this.startShares, this.endShares, this.startMoney, this.values);
		
	}
	private void processAction(Actions action, Value value){
		switch(action){
		case BUY:
			processBuy(value);
			return;
		case SELL: 
			processSell(value);
			return;
		case NOTHING:
			return;
		}
	}
	
	
	private void processSell(Value value) {
		Integer shares = (int) Math.floor(sellAmount / value.getCurrent());
		if(endShares < shares){
			throw new FinanceException("NotEnoughShares");
		}
		endShares-=shares;
		endMoney+=shares * value.getCurrent();
	}

	private void processBuy(Value value) {
		if(endMoney < buyAmount){
			throw new FinanceException("NotEnoughMoney");
		}
		Integer shares = (int) Math.floor(buyAmount / value.getCurrent());
		endShares+=shares;
		endMoney-=shares * value.getCurrent();
	}

	public Double getStartMoney() {
		return startMoney;
	}

	public Double getEndMoney() {
		return endMoney;
	}

	public Integer getStartShares() {
		return startShares;
	}

	public Integer getEndShares() {
		return endShares;
	}
	
	public Double getEndSharesValue() {
		return endShares * values.get(values.size() - 1).getCurrent();
	}

	public List<Value> getValues() {
		return values;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public SimulatorResult getResult() {
		return result;
	}
	
}
