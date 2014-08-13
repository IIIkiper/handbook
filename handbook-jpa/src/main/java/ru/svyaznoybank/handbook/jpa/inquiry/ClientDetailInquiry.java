package ru.svyaznoybank.handbook.jpa.inquiry;

import javax.ws.rs.QueryParam;

public class ClientDetailInquiry extends Inquiry {
	
	@QueryParam("clientId")
	private Long clientId;

	// --- Getters / Setters ---
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}