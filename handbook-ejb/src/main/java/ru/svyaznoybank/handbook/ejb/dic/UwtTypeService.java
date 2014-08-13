package ru.svyaznoybank.handbook.ejb.dic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.jpa.dao.dic.UwtTypeDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.UwtType;

@Stateless
public class UwtTypeService extends DictionaryService<UwtType> {
	
	@EJB
	private UwtTypeDao dao;

	@Override
	protected UwtTypeDao getDao() {
		return dao;
	}
}