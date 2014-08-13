package ru.svyaznoybank.handbook.ejb.dic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.jpa.dao.dic.ProductDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.Product;

@Stateless
public class ProductService extends DictionaryService<Product> {
	
	@EJB
	private ProductDao dao;

	@Override
	protected ProductDao getDao() {
		return dao;
	}
}