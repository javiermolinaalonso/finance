package com.phoboss.finance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Price")
public class Price {

	private Long id;
	private Date date;
	private Double previous;
	private Double current;
	private Double adjClose;
	private Double dayMax;
	private Double dayMin;
	private Double volume;
	private Double open;
	private String ticker;
	
	
	public Price(Date date, Double previous, Double current, Double adjClose,
			Double dayMax, Double dayMin, Double volume, Double open,
			String ticker) {
		super();
		this.date = date;
		this.previous = previous;
		this.current = current;
		this.adjClose = adjClose;
		this.dayMax = dayMax;
		this.dayMin = dayMin;
		this.volume = volume;
		this.open = open;
		this.ticker = ticker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="previous")
	public Double getPrevious() {
		return previous;
	}

	public void setPrevious(Double previous) {
		this.previous = previous;
	}

	@Column(name="current")
	public Double getCurrent() {
		return current;
	}

	public void setCurrent(Double current) {
		this.current = current;
	}

	@Column(name="adj_close")
	public Double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}

	@Column(name="day_max")
	public Double getDayMax() {
		return dayMax;
	}

	public void setDayMax(Double dayMax) {
		this.dayMax = dayMax;
	}

	@Column(name="day_min")
	public Double getDayMin() {
		return dayMin;
	}

	public void setDayMin(Double dayMin) {
		this.dayMin = dayMin;
	}

	@Column(name="volume")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@Column(name="open")
	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	@Column(name="ticker")
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
	    return id;
	}
}
