package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Set;

import javax.validation.ConstraintViolation;

@SuppressWarnings("serial")
public abstract class HandbookException extends Exception {
	
	private final FaultBean faultBean;
	
	public <T> HandbookException(Set<ConstraintViolation<T>> cvs) {
		super("Exception occured");
		faultBean = new FaultBean(cvs);
	}
	
	public HandbookException(Throwable cause) {
		this('[' + cause.getClass().getName() + "] " + cause.getMessage());
	}
	
	public <T> HandbookException(String message) {
		this(message, new FaultBean(message));
	}
	
	public HandbookException(String message, FaultBean faultBean, Throwable cause) {
		super(message, cause);
		this.faultBean = faultBean;
	}
	
	public HandbookException(String message, FaultBean faultBean) {
		super(message);
		this.faultBean = faultBean;
	}
	
	public FaultBean getFaultInfo() {
		return faultBean;
	}
}