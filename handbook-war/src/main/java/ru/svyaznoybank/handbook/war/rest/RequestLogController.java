package ru.svyaznoybank.handbook.war.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.svyaznoybank.handbook.ejb.RequestLogService;
import ru.svyaznoybank.handbook.ejb.dto.RequestLogDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.ClientData;
import ru.svyaznoybank.handbook.jpa.domain.RequestLog;
import ru.svyaznoybank.handbook.jpa.inquiry.RequestLogInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
@Path("log/request")
@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class RequestLogController {
	
	@EJB
	private RequestLogService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<RequestLogDto> get(@BeanParam RequestLogInquiry inquiry, @BeanParam ClientData example) {
		RequestLog rl = new RequestLog();
		rl.setClientData(example);
		inquiry.setExample(rl);
		return service.get(inquiry);
	}
	
	@GET
	@Path("{id}/response")
	@Produces(MediaType.APPLICATION_XML)
	public String getResponse(@PathParam("id") Long id) {
		return service.getResponse(id);
	}
}