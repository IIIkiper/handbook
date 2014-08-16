package ru.svyaznoybank.handbook.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.OfferDto;
import ru.svyaznoybank.handbook.ejb.soap.OfferSoap;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.dao.OfferDao;
import ru.svyaznoybank.handbook.jpa.domain.Offer;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;

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
	
	public List<OfferSoap> getOffers(HandbookSoapParams params, Date requestDate) {
		List<OfferSoap> list = new ArrayList<OfferSoap>();
		for (Offer h : dao.getOffers(params, requestDate)) {
			list.add(new OfferSoap(h));
		}
		return list;
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