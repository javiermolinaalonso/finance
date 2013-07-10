package com.phoboss.finance.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Value {
	private Double previous;
	private Double current;
	private Date date;
	private Double dividend;
	
	
	public Value(Double previous, Double current, Date date, Double dividend) {
		super();
		this.previous = previous;
		this.current = current;
		this.date = date;
		this.dividend = dividend;
	}
	public Double getPrevious() {
		return previous;
	}
	public void setPrevious(Double previous) {
		this.previous = previous;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getDividend() {
		return dividend;
	}
	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb = sb.append("[").append(new SimpleDateFormat("dd/MM/yyyy").format(date)).append(", ").append(previous).append(", ").append(current);
		if(dividend != null){
			sb = sb.append(", Div ").append(dividend);
		}
		sb = sb.append("]");
		return sb.toString();
		
	}
}
