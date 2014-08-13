package ru.svyaznoybank.handbook.jpa.dao.dic;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
public class OfferTypeDao extends DictionaryDao<OfferType> {
	
	@Inject
	private AuthDetails authDetails;
	
	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, OfferType> root, CriteriaBuilder cb, DictionaryInquiry params) {
		super.configurePredicates(p, query, root, cb, params);
		
		if (!authDetails.isAdmin()) {
			p.add(root.get("id").in(
				authDetails.getHandbookTypes() == null || authDetails.getHandbookTypes().isEmpty() ?
				cb.nullLiteral(String.class) :
				authDetails.getHandbookTypes()
			));
		} else if (params.getUserId() != null) {
			// If user is admin and user id is specified, show
			// handbook types allowed for this user
			p.add(cb.equal(root.join("users").get("id"), params.getUserId()));
		}
	}
	
	public List<OfferType> getReverse(Long userId) {
		CriteriaBuilder cb = getCriteriaBuilder();
		
    	CriteriaQuery<OfferType> query = cb.createQuery(entityClass);
    	Root<OfferType> from = query.from(entityClass);
    	
    	Subquery<String> subquery = query.subquery(String.class);
    	Root<OfferType> sfrom = subquery.from(entityClass);
    	
    	subquery.select(sfrom.<String>get("id")).where(cb.equal(sfrom.join("users").get("id"), userId));

    	return list(
    		query.select(from).where(cb.not(from.get("id").in(subquery)))
    	);
	}
}