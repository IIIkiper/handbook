package ru.svyaznoybank.handbook.war.gwt.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLog;

public interface RequestLogProperties extends ModelProperties<RequestLog> {
	
	@Path("firstName")
	LabelProvider<RequestLog> firstNameLabel();
	
	ValueProvider<RequestLog, String> firstName();
	ValueProvider<RequestLog, String> lastName();
	ValueProvider<RequestLog, String> patronymic();
	ValueProvider<RequestLog, Long> clientId();
	ValueProvider<RequestLog, Date> birthday();
	ValueProvider<RequestLog, String> docSeries();
	ValueProvider<RequestLog, String> docNumber();
	ValueProvider<RequestLog, Date> docIssueDate();
	ValueProvider<RequestLog, String> comment();
	
	ValueProvider<RequestLog, String> docId();
	ValueProvider<RequestLog, String> eanId();
	ValueProvider<RequestLog, Integer> errorCode();
	ValueProvider<RequestLog, String> response();
}