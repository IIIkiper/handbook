package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.CurrencyService;
import ru.svyaznoybank.handbook.jpa.domain.dic.Currency;

@Stateless
@Path("dic/currency")
public class CurrencyController extends DictionaryController<Currency> {
	
	@EJB
	private CurrencyService service;

	@Override
	public CurrencyService getService() {
		return service;
	} 
}