package ru.svyaznoybank.handbook.war.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import ru.svyaznoybank.handbook.ejb.dic.ProductService;
import ru.svyaznoybank.handbook.jpa.domain.dic.Product;

@Stateless
@Path("dic/product")
public class ProductController extends DictionaryController<Product> {
	
	@EJB
	private ProductService service;

	@Override
	public ProductService getService() {
		return service;
	} 
}