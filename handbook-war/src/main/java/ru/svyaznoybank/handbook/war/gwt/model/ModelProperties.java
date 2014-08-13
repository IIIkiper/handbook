package ru.svyaznoybank.handbook.war.gwt.model;

import java.util.Date;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ModelProperties<T extends Dto> extends PropertyAccess<T> {
	ModelKeyProvider<T> id();
	ValueProvider<T, String> creator();
	ValueProvider<T, String> updater();
	ValueProvider<T, Date> createDate();
	ValueProvider<T, Date> updateDate();
}