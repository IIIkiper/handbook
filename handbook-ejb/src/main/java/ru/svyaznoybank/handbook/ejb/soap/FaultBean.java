package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class FaultBean {
	
	private String message;
	
	public FaultBean() { }
	
	public FaultBean(String message) {
		this.message = message;
	}
	
	public <T> FaultBean(Set<ConstraintViolation<T>> cvs) {
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<?> cv : cvs) {
			message.append("['").append(cv.getPropertyPath()).append("' ").append(cv.getMessage()).append(']');
		}
		this.message = message.toString();
	}

	// --- Getters / Setters ---
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}