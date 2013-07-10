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
@Table(name="Split")
public class Split {

	@Id
	@Column(name="ticker")
	private String ticker;
	@Id
	@Column(name="date")
	private Date date;
	
	@Column(name="original_shares", nullable=false)
	private Integer originalShares;
	
	@Column(name="new_shares", nullable=false)
	private Integer newShares;

	
	public Split(){
		super();
	}
	public Split(String ticker, Date date, Integer originalShares, Integer newShares) {
		this();
		this.date = date;
		this.ticker = ticker;
		this.originalShares = originalShares;
		this.newShares = newShares;
	}

	
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getOriginalShares() {
		return originalShares;
	}

	public void setOriginalShares(Integer originalShares) {
		this.originalShares = originalShares;
	}

	public Integer getNewShares() {
		return newShares;
	}

	public void setNewShares(Integer newShares) {
		this.newShares = newShares;
	}
	
	
}
