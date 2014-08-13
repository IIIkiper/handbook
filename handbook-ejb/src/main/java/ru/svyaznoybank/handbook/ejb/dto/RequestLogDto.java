package ru.svyaznoybank.handbook.ejb.dto;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.RequestLog;

public class RequestLogDto extends ClientDataDto {
	
	private Integer errorCode;
	private String response;
	private String eanId;

	public RequestLogDto(RequestLog entity) {
		BeanUtil.copyProperties(entity, this, "response", "clientData", "ean");
		BeanUtil.copyProperties(entity.getClientData(), this, "doc");
	}

	// --- Getters / Setters ---
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getEanId() {
		return eanId;
	}
	public void setEanId(String eanId) {
		this.eanId = eanId;
	}
}