package ru.svyaznoybank.handbook.war.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.svyaznoybank.handbook.ejb.ClientService;
import ru.svyaznoybank.handbook.ejb.dto.ClientDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.Client;
import ru.svyaznoybank.handbook.jpa.domain.ClientData;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
@Path("client")
@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class ClientController {
	
	@EJB
	private ClientService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<ClientDto> get(@BeanParam ClientInquiry inquiry, @BeanParam ClientData example) {
		Client client = new Client();
		client.setClientData(example);		
		inquiry.setExample(client);
		return service.get(inquiry);
	}
}