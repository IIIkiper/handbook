package ru.svyaznoybank.handbook.war.gwt.rest;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;
import com.google.gwt.http.client.Request;

public interface RestCallback<T extends Dto> {
	public void onResponseReceived(Request request, T response);
	public void onError(Throwable exception);
}