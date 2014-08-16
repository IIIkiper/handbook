package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.xml.ws.WebFault;

@WebFault
@SuppressWarnings("serial")
public class HandbookRequestException extends HandbookException {

	public <T> HandbookRequestException(Set<ConstraintViolation<T>> cvs) {
		super(cvs);
	}
}