package ru.svyaznoybank.handbook.ejb.soap;

import javax.jws.WebService;

@WebService(targetNamespace = IHandbookService.NAMESPACE)
public interface IHandbookService {
	
	String NAMESPACE = "http://svyaznoybank.ru";

}