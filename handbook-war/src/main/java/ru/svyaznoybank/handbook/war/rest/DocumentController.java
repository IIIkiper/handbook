package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.DocumentService;
import ru.svyaznoybank.handbook.jpa.domain.dic.Document;

@Stateless
@Path("dic/document")
public class DocumentController extends DictionaryController<Document> {
	
	@EJB
	private DocumentService service;

	@Override
	public DocumentService getService() {
		return service;
	} 
}