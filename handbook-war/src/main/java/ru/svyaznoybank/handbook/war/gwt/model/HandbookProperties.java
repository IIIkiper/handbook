package ru.svyaznoybank.handbook.war.gwt.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;

import ru.svyaznoybank.handbook.war.gwt.json.Handbook;

public interface HandbookProperties extends ModelProperties<Handbook> {
	
	ValueProvider<Handbook, String> value();
	ValueProvider<Handbook, String> remark();
	ValueProvider<Handbook, Date> beginDate();
	ValueProvider<Handbook, Date> endDate();
	ValueProvider<Handbook, String> productId();
	ValueProvider<Handbook, String> currencyId();
	ValueProvider<Handbook, String> handbookTypeId();
}