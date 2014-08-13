package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.OfferTypeService;
import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;

@Stateless
@Path("dic/offer")
public class OfferTypeController extends DictionaryController<OfferType> {
	
	@EJB
	private  OfferTypeService service;

	@Override
	public OfferTypeService getService() {
		return service;
	} 
}