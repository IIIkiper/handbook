package ru.svyaznoybank.handbook.war.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.svyaznoybank.handbook.ejb.UserService;
import ru.svyaznoybank.handbook.ejb.dic.HandbookTypeService;
import ru.svyaznoybank.handbook.ejb.dic.OfferTypeService;
import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.dto.UserDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.User;
import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;
import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
@Path("user")
@RolesAllowed(AuthDetails.ADMIN_ROLE)
public class UserController {
	
	@EJB
	private UserService service;
	
	@EJB
	private OfferTypeService otService;
	
	@EJB
	private HandbookTypeService htService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<UserDto> get(@BeanParam Inquiry inquiry) {
		return service.get(inquiry);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto add(User model) {	
		service.persistEntity(model);
		return new UserDto(model);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		service.removeEntityById(id);
	}
	
	@GET
	@Path("{id}/dic/handbook")
	@Produces(MediaType.APPLICATION_JSON)
//	@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
	public PagingResult<DictionaryDto> getUserHandbookTypes(@PathParam("id") Long id, @BeanParam DictionaryInquiry inquiry) {
//		if (authDetails.isAdmin() || authDetails.getUserId() != null && authDetails.getUserId().equals(id)) {
			inquiry.setUserId(id);
			return htService.get(inquiry);
//		}

		/*
		throw new WebApplicationException(
		    Response.status(HttpURLConnection.HTTP_FORBIDDEN)
		    	.entity("Operator cannot see permissions for another user").build()
		);
		*/
	}
	
	@GET
	@Path("{id}/dic/offer")
	@Produces(MediaType.APPLICATION_JSON)
//	@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
	public PagingResult<DictionaryDto> getUserOfferTypes(@PathParam("id") Long id, @BeanParam DictionaryInquiry inquiry) {
//		if (authDetails.isAdmin() || authDetails.getUserId() != null && authDetails.getUserId().equals(id)) {
			inquiry.setUserId(id);
			return otService.get(inquiry);
//		}

		/*
		throw new WebApplicationException(
		    Response.status(HttpURLConnection.HTTP_FORBIDDEN)
		    	.entity("Operator cannot see permissions for another user").build()
		);
		*/
	}
	
	@GET
	@Path("{id}/dic/handbook/reverse")
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<DictionaryDto> getUserHandbookTypes(@PathParam("id") Long id) {
		return htService.getReverse(id);
	}
	
	@GET
	@Path("{id}/dic/offer/reverse")
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<DictionaryDto> getUserOfferTypes(@PathParam("id") Long id) {
		return otService.getReverse(id);
	}
	
	@POST
	@Path("{id}/dic/handbook")
	@Produces(MediaType.APPLICATION_JSON)
	public DictionaryDto addHandbookType(@PathParam("id") Long id, HandbookType ht) {
		return htService.addUserHandbookType(id, ht);
	}
	
	@POST
	@Path("{id}/dic/offer")
	@Produces(MediaType.APPLICATION_JSON)
	public DictionaryDto addOfferType(@PathParam("id") Long id, OfferType ot) {
		return otService.addUserOfferType(id, ot);
	}
	
	@DELETE
	@Path("{id}/dic/handbook/{delId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteHandbookType(@PathParam("id") Long id, @PathParam("delId") String delId) {
		htService.deleteUserHandbookType(id, delId);
	}
	
	@DELETE
	@Path("{id}/dic/offer/{delId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteOfferType(@PathParam("id") Long id, @PathParam("delId") String delId) {
		otService.deleteUserOfferType(id, delId);
	}
}