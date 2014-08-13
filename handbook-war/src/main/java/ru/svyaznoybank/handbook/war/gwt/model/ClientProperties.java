package ru.svyaznoybank.handbook.war.gwt.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;

import ru.svyaznoybank.handbook.war.gwt.json.Client;

public interface ClientProperties extends ModelProperties<Client> {
	
	ValueProvider<Client, String> firstName();
	ValueProvider<Client, String> lastName();
	ValueProvider<Client, String> patronymic();
	ValueProvider<Client, Long> clientId();
	ValueProvider<Client, Date> birthday();
	ValueProvider<Client, String> docSeries();
	ValueProvider<Client, String> docNumber();
	ValueProvider<Client, Date> docIssueDate();
	ValueProvider<Client, String> comment();
}