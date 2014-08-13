package ru.svyaznoybank.handbook.ejb.dic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.jpa.dao.dic.CurrencyDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.Currency;

@Stateless
public class CurrencyService extends DictionaryService<Currency> {
	
	@EJB
	private CurrencyDao dao;

	@Override
	protected CurrencyDao getDao() {
		return dao;
	}
}