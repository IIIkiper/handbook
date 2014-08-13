package ru.svyaznoybank.handbook.war.rest;

import java.net.HttpURLConnection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.svyaznoybank.handbook.ejb.HandbookService;
import ru.svyaznoybank.handbook.ejb.dto.HandbookDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.Handbook;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;

@Stateless
@Path("handbook")
//@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class HandbookController  {
	
	@EJB
	private HandbookService service;
	
//	@Inject
//	private AuthDetails authDetails;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<HandbookDto> get(@BeanParam ClientDetailInquiry inquiry) {
		if (inquiry.getClientId() == null) {
			throw new WebApplicationException(
			    Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
			    	.entity("clientId parameter is mandatory")
			    	.build()
			);
		}
		return service.get(inquiry);
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HandbookDto update(@PathParam("id") String id, Handbook model) {
		return service.update(model);
	}
}