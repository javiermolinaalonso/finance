package com.phoboss.finance.exceptions;

public class FinanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7703700959760649495L;
	
	public FinanceException(String code){
		super(code);
	}
	public FinanceException(String code, Exception e){
		super(code, e);
	}
}
