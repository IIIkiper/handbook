package ru.svyaznoybank.handbook.ejb.dto;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.Client;

public class ClientDto extends ClientDataDto { 
	public ClientDto(Client entity) {
		BeanUtil.copyProperties(entity, this, "clientData", "offers", "handbooks");
		BeanUtil.copyProperties(entity.getClientData(), this, "doc");
	}
}