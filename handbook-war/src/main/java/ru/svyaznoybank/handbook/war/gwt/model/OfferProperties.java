package ru.svyaznoybank.handbook.war.gwt.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;

import ru.svyaznoybank.handbook.war.gwt.json.Offer;

public interface OfferProperties extends ModelProperties<Offer> {
	
	ValueProvider<Offer, Date> beginDate();
	ValueProvider<Offer, Date> endDate();
	ValueProvider<Offer, String> remark();
	
	ValueProvider<Offer, Double> creditLine();
	ValueProvider<Offer, Double> minCreditLine();
	ValueProvider<Offer, Double> maxCreditLine();
	ValueProvider<Offer, Double> minFirstPayment();
	ValueProvider<Offer, Double> maxFirstPayment();
	ValueProvider<Offer, Double> minYield();
	ValueProvider<Offer, Double> maxYield();
	ValueProvider<Offer, Integer> minRisk();
	ValueProvider<Offer, Integer> maxRisk();
	ValueProvider<Offer, Integer> averageRisk();
	
	ValueProvider<Offer, String> workflowCode();
	
	ValueProvider<Offer, String> currencyId();
	ValueProvider<Offer, String> offerCurrencyId();
	ValueProvider<Offer, String> productId();
	ValueProvider<Offer, String> uwTypeId();
	ValueProvider<Offer, String> offerTypeId();
}