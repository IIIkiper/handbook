package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import ru.svyaznoybank.handbook.ejb.HandbookService;
import ru.svyaznoybank.handbook.ejb.OfferService;

@Stateless
@WebService(
	endpointInterface = "ru.svyaznoybank.handbook.ejb.soap.IHandbookService",
	targetNamespace = IHandbookService.NAMESPACE,
	name = IHandbookService.SERVICE_NAME,
	serviceName = IHandbookService.SERVICE_NAME
)
@HandlerChain(file = "handlers.xml")
public class HandbookSoapService implements IHandbookService {
	
	private static final Logger LOG = Logger.getLogger(HandbookSoapService.class.getName());
	
	@Inject
	private Validator validator;
	
	@EJB
	private HandbookService handbookService;
	
	@EJB
	private OfferService offerService;

	@Override
	@RolesAllowed("HandbookAdmin")
	public HandbookResponse getHandbook(HandbookRequest request) throws HandbookRequestException, HandbookInternalException {
		Set<ConstraintViolation<HandbookRequest>> cvs = validator.validate(request);
		if (!cvs.isEmpty()) {
			throw new HandbookRequestException(cvs);
		}
			
		HandbookResponse response = new HandbookResponse();
		
		try {
			response.getHandbooks().addAll(handbookService.getHandbooks(request.getParameters(), request.getRequestDate()));
			response.getOffers().addAll(offerService.getOffers(request.getParameters(), request.getRequestDate()));
			if (request.getParameters().getEanId() != null) {
				//TODO
			}
		} catch (Exception /*Throwable*/ ex) {
			throw new HandbookInternalException(ex);
		}
		
		response.setRequestId(request.getRequestId());
		response.setResponseDate(new Date());
		
		return response;
	}
}