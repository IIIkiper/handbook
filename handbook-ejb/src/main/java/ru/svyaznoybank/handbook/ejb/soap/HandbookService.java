package ru.svyaznoybank.handbook.ejb.soap;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(
	endpointInterface = "ru.svyaznoybank.handbook.ejb.soap.IHandbookService", 
	targetNamespace = IHandbookService.NAMESPACE
)
public class HandbookService implements IHandbookService {

}