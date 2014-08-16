package ru.svyaznoybank.handbook.ejb.soap;

import javax.xml.ws.WebFault;

@WebFault
@SuppressWarnings("serial")
public class HandbookInternalException extends HandbookException {

	public HandbookInternalException(Throwable cause) {
		super(cause);
	}
}