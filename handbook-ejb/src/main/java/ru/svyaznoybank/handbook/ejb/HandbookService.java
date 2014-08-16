package ru.svyaznoybank.handbook.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.HandbookDto;
import ru.svyaznoybank.handbook.ejb.soap.HandbookSoap;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.ClientDetailDao;
import ru.svyaznoybank.handbook.jpa.dao.HandbookDao;
import ru.svyaznoybank.handbook.jpa.domain.Handbook;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;

@Stateless
public class HandbookService extends Service<Handbook> {
	
	@EJB
	private HandbookDao dao;
	
	@Override
	protected ClientDetailDao<Handbook> getDao() {
		return dao;
	}
	
	public PagingResult<HandbookDto> get(ClientDetailInquiry inquiry) {
		return new PagingResult<>(HandbookDto.class, Handbook.class, dao, inquiry);
	}
	
	public List<HandbookSoap> getHandbooks(HandbookSoapParams params, Date requestDate) {
		List<HandbookSoap> list = new ArrayList<HandbookSoap>();
		for (Handbook h : dao.getHandbooks(params, requestDate)) {
			list.add(new HandbookSoap(h));
		}
		return list;
	}
	
	@TransactionAttribute
	public HandbookDto update(Handbook entity) {
		if (entity.getId() == null) {
			throw new IllegalArgumentException("");
		}
		
		Handbook persistedEntity = getEntity(entity.getId());
		
		persistedEntity.setRemark(entity.getRemark());
		persistedEntity.setEndDate(entity.getEndDate());
		
		return new HandbookDto(dao.merge(persistedEntity));
	}
}