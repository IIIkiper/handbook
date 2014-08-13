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

import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
public class HandbookTypeDao extends DictionaryDao<HandbookType> {
	
	@Inject
	private AuthDetails authDetails;
	
	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, HandbookType> root, CriteriaBuilder cb, DictionaryInquiry params) {
		super.configurePredicates(p, query, root, cb, params);
		
		if (!authDetails.isAdmin()) {
			// If user is operator, show only allowed handbook types
			p.add(root.get("id").in(
				authDetails.getHandbookTypes().isEmpty() ?
				cb.nullLiteral(String.class) :
				authDetails.getHandbookTypes()
			));
		} else if (params.getUserId() != null) {
			// If user is admin and user id is specified, show 
			// handbook types allowed for this user
			p.add(cb.equal(root.join("users").get("id"), params.getUserId()));
		}
	}
	
	public List<HandbookType> getReverse(Long userId) {
		CriteriaBuilder cb = getCriteriaBuilder();
		
    	CriteriaQuery<HandbookType> query = cb.createQuery(entityClass);
    	Root<HandbookType> from = query.from(entityClass);
    	
    	Subquery<String> subquery = query.subquery(String.class);
    	Root<HandbookType> sfrom = subquery.from(entityClass);

    	subquery.select(sfrom.<String>get("id")).where(cb.equal(sfrom.join("users").get("id"), userId));
    	
    	return list(
    		query.select(from).where(cb.not(from.get("id").in(subquery)))
    	);
	}
}