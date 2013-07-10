package com.phoboss.finance.entities.composite;

import java.io.Serializable;
import java.util.Date;

public class DateTickerPkey implements Serializable {
	private static final long serialVersionUID = 3869992741305996869L;
	private String ticker;
	private Date date;

	public DateTickerPkey() {
		super();
	}

	public DateTickerPkey(String ticker, Date date) {
		this();
		this.ticker = ticker;
		this.date = date;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTicker() {
		return ticker;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DateTickerPkey)) {
			return false;
		} else {
			DateTickerPkey dp = (DateTickerPkey) other;
			return dp.getDate().equals(getDate())
					&& dp.getTicker().equals(getTicker());
		}
	}

	@Override
	public int hashCode() {
		return date.hashCode() * 10 + ticker.hashCode();
	}
}
