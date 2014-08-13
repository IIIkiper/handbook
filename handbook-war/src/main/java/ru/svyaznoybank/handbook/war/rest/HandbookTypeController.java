package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.HandbookTypeService;
import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;

@Stateless
@Path("dic/handbook")
public class HandbookTypeController extends DictionaryController<HandbookType> {
	
	@EJB
	private HandbookTypeService service;

	@Override
	public HandbookTypeService getService() {
		return service;
	} 
}