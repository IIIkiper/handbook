package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.UwtTypeService;
import ru.svyaznoybank.handbook.jpa.domain.dic.UwtType;

@Stateless
@Path("dic/uwt")
public class UwtTypeController extends DictionaryController<UwtType> {
	
	@EJB
	private UwtTypeService service;

	@Override
	public UwtTypeService getService() {
		return service;
	} 
}