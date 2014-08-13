package ru.svyaznoybank.handbook.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.ejb.dto.ClientDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.ClientDao;
import ru.svyaznoybank.handbook.jpa.domain.Client;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientInquiry;

@Stateless
public class ClientService {
	
	@EJB
	private ClientDao dao;
	
	public PagingResult<ClientDto> get(ClientInquiry inquiry) {
		return new PagingResult<>(ClientDto.class, Client.class, dao, inquiry);
	}
}