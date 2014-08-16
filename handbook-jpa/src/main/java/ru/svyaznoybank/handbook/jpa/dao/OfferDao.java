package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import ru.svyaznoybank.handbook.jpa.domain.Offer;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
public class OfferDao extends ClientDetailDao<Offer> {
		
	@Inject
	private AuthDetails authDetails;
	
	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, Offer> root, CriteriaBuilder cb, ClientDetailInquiry inquiry) {
		super.configurePredicates(p, query, root, cb, inquiry);
		
		if (!authDetails.isAdmin()) {
			System.out.println("NOT admin");
			p.add(root.get("offerTypeId").in(
				authDetails.getOfferTypes().isEmpty() ?
				cb.nullLiteral(String.class) :
				authDetails.getOfferTypes()
			));
		}
	}
	
	public List<Offer> getOffers(HandbookSoapParams params, Date requestDate) {
		return entityManager.createNamedQuery(Offer.LAST_OFFERS_IN_TYPE_ID_GROUPS_QUERY, entityClass)
			.setParameter("LAST_NAME", params.getLastName())
			.setParameter("FIRST_NAME", params.getFirstName())
			.setParameter("SECOND_NAME", params.getPatronymic())
			.setParameter("BIRTHDAY", params.getBirthday(), TemporalType.DATE)
			.setParameter("DOC_TYPE", params.getDocType())
			.setParameter("DOC_SERIES", params.getDocSeries())
			.setParameter("DOC_NUMBER", params.getDocNumber())
			.setParameter("PHONE_NUMBER", params.getPhoneNumber())
			.setParameter("CUSTOMER_ID", params.getCustomerId())
			.setParameter("PRODUCT_ID", params.getProductId())
			.setParameter("CURRENCY_ID", params.getCurrencyId())
			.setParameter("REQUEST_DATE", requestDate, TemporalType.DATE)
			.getResultList();
	}
}