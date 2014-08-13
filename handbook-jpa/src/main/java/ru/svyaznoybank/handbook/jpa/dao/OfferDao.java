package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import ru.svyaznoybank.handbook.jpa.domain.Offer;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
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
}