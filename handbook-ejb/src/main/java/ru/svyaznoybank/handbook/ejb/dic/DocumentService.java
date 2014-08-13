package ru.svyaznoybank.handbook.ejb.dic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.jpa.dao.dic.DocumentDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.Document;

@Stateless
public class DocumentService extends DictionaryService<Document> {
	
	@EJB
	private DocumentDao dao;

	@Override
	protected DocumentDao getDao() {
		return dao;
	}
}