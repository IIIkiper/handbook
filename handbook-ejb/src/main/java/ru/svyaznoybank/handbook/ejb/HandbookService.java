package ru.svyaznoybank.handbook.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.HandbookDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.ClientDetailDao;
import ru.svyaznoybank.handbook.jpa.dao.HandbookDao;
import ru.svyaznoybank.handbook.jpa.domain.Handbook;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;

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