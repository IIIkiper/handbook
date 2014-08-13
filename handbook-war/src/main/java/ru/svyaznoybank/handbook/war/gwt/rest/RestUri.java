package ru.svyaznoybank.handbook.war.gwt.rest;

/**
 * REST API of Handbook application
 * @author Roman Zaripov
 */
public enum RestUri {
	
	DIC_DOCUMENT("dic/document"),
	DIC_OFFER("dic/offer"),
	DIC_PRODUCT("dic/product"),
	DIC_UWT("dic/uwt"),
	DIC_HANDBOOK("dic/handbook"),
	DIC_CURRENCY("dic/currency"),
	
	CLIENT("client"),
	OFFER("offer"),
	HANDBOOK("handbook"),
	REQUEST_LOG("log/request"),
	REQUEST_LOG_RESPONSE("log/request/{id}/response"),
	
	USER("user"),
	USER_HANDBOOK("user/{id}/dic/handbook"),
	USER_OFFER("user/{id}/dic/offer"),
	USER_HANDBOOK_REVERSE("user/{id}/dic/handbook/reverse"),
	USER_OFFER_REVERSE("user/{id}/dic/offer/reverse");
	
	private String relativeUri;
	private String uri;
	
	private RestUri(String uri) {
		this.relativeUri = uri;
		this.uri = "rest/" + uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getRelativeUri() {
		return relativeUri;
	}
}