package ru.svyaznoybank.handbook.ejb.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @author Roman Zaripov
 */
@WebService(targetNamespace = IHandbookService.NAMESPACE)
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public interface IHandbookService {
	String NAMESPACE = "http://svyaznoybank.ru/handbook/white";
	String SERVICE_NAME = "HandbookService";
	
	@WebMethod
	@WebResult(name = "HandbookResponse", partName = "handbookResponse")
	public HandbookResponse getHandbook(
		@WebParam(name = "HandbookRequest", partName = "handbookRequest") HandbookRequest request
	) throws HandbookRequestException, HandbookInternalException;
}