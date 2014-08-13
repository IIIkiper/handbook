package ru.svyaznoybank.handbook.war.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.svyaznoybank.handbook.ejb.dic.DictionaryService;
import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.domain.dic.Dictionary;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

/**
 * Superclass for all dictionary-related REST-services. Defines general dictionary operations: </br>
 * - get ordered list of dictionary entities in specified range, </br>
 * - create new dictionary entry, </br>
 * - update existing dictionary entry by ID, </br>
 * - delete dictionary entry by ID.
 * @author Roman Zaripov
 */
@RolesAllowed({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public abstract class DictionaryController<E extends Dictionary> {
	
	public abstract DictionaryService<E> getService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingResult<DictionaryDto> get(@BeanParam DictionaryInquiry config) {
		return getService().get(config);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DictionaryDto add(E model) {	
		getService().persistEntity(model);
		return new DictionaryDto(model);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DictionaryDto update(@PathParam("id") String id, E model) {
		return getService().update(model);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") String id) {
		getService().removeEntityById(id);
	}
}