package ru.svyaznoybank.handbook.war.rest;

import java.net.HttpURLConnection;

import javax.annotation.security.RolesAllowed;
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

import ru.svyaznoybank.handbook.ejb.OfferService;
import ru.svyaznoybank.handbook.ejb.dto.OfferDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.Offer;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
@Path("offer")
@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class OfferController {
	
	@EJB
	private OfferService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<OfferDto> get(@BeanParam ClientDetailInquiry inquiry) {
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
	public OfferDto update(@PathParam("id") String id, Offer model) {
		return service.update(model);
	}
}