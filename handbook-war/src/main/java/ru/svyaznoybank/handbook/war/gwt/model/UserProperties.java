package ru.svyaznoybank.handbook.war.gwt.model;

import com.sencha.gxt.core.client.ValueProvider;

import ru.svyaznoybank.handbook.war.gwt.json.User;

public interface UserProperties extends ModelProperties<User> {
	ValueProvider<User, String> nickname();
}