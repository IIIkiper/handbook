package ru.svyaznoybank.handbook.ejb.soap;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;

import ru.svyaznoybank.handbook.ejb.RequestLogService;
import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.ClientData;
import ru.svyaznoybank.handbook.jpa.domain.RequestLog;
import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;

@Stateless /* ejb injection works in 50% cases... glassfish bug? have to mark hanler as @Stateless to work around this problem. */
public class HandbookSoapServiceHandler implements SOAPHandler<SOAPMessageContext> {
	
	private JAXBContext jaxbContext;
	private Transformer transformer;
	
	private RequestLog requestLog;
	
	@EJB
	private RequestLogService service;
	
	@PostConstruct
	public void init() {
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			jaxbContext = JAXBContext.newInstance(HandbookRequest.class);
		} catch (JAXBException | TransformerConfigurationException| TransformerFactoryConfigurationError e) {
			/* Cannot happen */
			e.printStackTrace();
		}
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		handle(context, false);
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		handle(context, true);
		return true;
	}

	@Override
	public void close(MessageContext context) {
		if (requestLog == null) {
			return;
		}
//		service.persistEntity(requestLog);
	}
	
	@Override
	public Set<QName> getHeaders() {
		return null;
	}
	
	private void handle(SOAPMessageContext ctx, boolean fault) {
		/*
		if (!((String) ctx.get(MessageContext.WSDL_OPERATION)).contains("getHandbooks")) {
			return;
		}
		*/
		
		try {
			if ((Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
				requestLog.setResponse(getString(ctx.getMessage().getSOAPBody()));
				if (fault) {
					requestLog.setErrorCode(1001);
				}
			} else {
				requestLog = new RequestLog();
				
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				Node bodyNode = ctx.getMessage().getSOAPBody().getFirstChild().getFirstChild();				
				HandbookRequest request = unmarshaller.unmarshal(bodyNode, HandbookRequest.class).getValue();
				
				if (request.getParameters() != null) {
					HandbookSoapParams params = request.getParameters();
					
					requestLog.setEanId(params.getEanId());
					
					ClientData clientData = new ClientData();
					BeanUtil.copyProperties(params, clientData);
					requestLog.setClientData(clientData);
				}
				
				System.out.println(request);
			}
		} catch (SOAPException  | JAXBException ex) {
			
		}
	}
	
	private String getString(Node node) {
		try (Writer writer = new StringWriter()) {
			StreamResult result = new StreamResult(writer);
			transformer.transform(new DOMSource(node), result);
			return result.getWriter().toString();
		} catch (IOException  | TransformerException ex ) {
			/* Cannot happen */
			return null;
		}
	}
}