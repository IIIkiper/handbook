package ru.svyaznoybank.handbook.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.OfferDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.dao.OfferDao;
import ru.svyaznoybank.handbook.jpa.domain.Offer;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;

@Stateless
public class OfferService extends Service<Offer> {
	
	@EJB
	private OfferDao dao;
	
	@Override
	protected Dao<Offer> getDao() {
		return dao;
	}
	
	public PagingResult<OfferDto> get(ClientDetailInquiry inquiry) {
		return new PagingResult<>(OfferDto.class, Offer.class, dao, inquiry);
	}
	
	@TransactionAttribute
	public OfferDto update(Offer entity) {
		if (entity.getId() == null) {
			throw new IllegalArgumentException("");
		}
		
		Offer persistedEntity = getEntity(entity.getId());
		persistedEntity.setRemark(entity.getRemark());
		persistedEntity.setEndDate(entity.getEndDate());

		return new OfferDto(dao.merge(persistedEntity));
	}
}