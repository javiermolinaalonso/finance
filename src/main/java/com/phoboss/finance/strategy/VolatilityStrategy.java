package com.phoboss.finance.strategy;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.phoboss.finance.entities.Value;
import com.phoboss.finance.enumerations.Actions;


@PropertySource("classpath:strategies/volatility.properties")
public class VolatilityStrategy implements Strategy {

	private static final Logger logger = Logger.getLogger(VolatilityStrategy.class);
	@Inject private Environment environment;
	
	private Double sellPercentage;
	private Double buyPercentage;
	private final boolean manualSetup;

	public VolatilityStrategy(){
		super();
		manualSetup = false;
	}
	
	public VolatilityStrategy(Double sellPercentage, Double buyPercentage) {
		super();
		this.sellPercentage = sellPercentage;
		this.buyPercentage = buyPercentage;
		manualSetup = true;
	}
	
	@PostConstruct
	public void setup(){
		if(!manualSetup){
			this.buyPercentage = Double.valueOf(environment.getProperty("action.buy.value"));
			this.sellPercentage = Double.valueOf(environment.getProperty("action.sell.value"));
		}
	}

	public Double getSellPercentage() {
		return sellPercentage;
	}

	public void setSellPercentage(Double sellPercentage) {
		this.sellPercentage = sellPercentage;
	}

	public Double getBuyPercentage() {
		return buyPercentage;
	}

	public void setBuyPercentage(Double buyPercentage) {
		this.buyPercentage = buyPercentage;
	}

	public Actions evaluate(Value value) {
		logger.debug("Evaluating value " + value);
		if(value.getPrevious() != null && value.getPrevious() > 0.0d){
			Double previous = value.getPrevious();
			if(value.getDividend() != null){
				previous = previous - value.getDividend();
			}
			if(((value.getCurrent() - previous) / previous) * 100 < -1*buyPercentage ){
				logger.info("The value " + value + " evaluated to buy");
				return Actions.BUY;
			}else if(((value.getCurrent() - previous) / previous) * 100 > sellPercentage){
				logger.info("The value " + value + " evaluated to sell");
				return Actions.SELL;
			}
		}
		return Actions.NOTHING;
	}
	
}
