package com.phoboss.finance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.phoboss.finance.entities.composite.DateTickerPkey;

@Entity
@IdClass(DateTickerPkey.class)
@Table(name="Dividend")
public class Dividend {

	@Id
	@Column(name="ticker")
	private String ticker;
	@Id
	@Column(name="date")
	private Date date;
	
	@Column(name="amount", nullable=false)
	private Double amount;

	public Dividend(){
		super();
	}
	public Dividend(String ticker, Date date, Double amount) {
		this();
		this.date = date;
		this.amount = amount;
		this.ticker = ticker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
