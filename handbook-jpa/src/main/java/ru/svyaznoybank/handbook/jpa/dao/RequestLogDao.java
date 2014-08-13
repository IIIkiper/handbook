package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.domain.RequestLog;
import ru.svyaznoybank.handbook.jpa.inquiry.RequestLogInquiry;

@Stateless
public class RequestLogDao extends FilterableDao<RequestLog, RequestLogInquiry> {

	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, RequestLog> root, CriteriaBuilder cb, RequestLogInquiry inquiry) {
		if (inquiry.getQuery() != null && !inquiry.getQuery().isEmpty()) {
			p.add(getOmni(cb, entityClass, inquiry.getQuery(), root, 
				"firstName",
				"lastName",
				"patronymic"
			));			
		} else if (inquiry.getExample() != null) {
			p.add(getExample(cb, inquiry.getExample(), root,
				"firstName",
				"lastName",
				"patronymic", 
				"birthday"
			));
		}
		
		addBetween(cb, p, root.<Date>get("createDate"), inquiry.getBeginDate(), inquiry.getEndDate());
	}

	@Override
	protected void configureOrder(List<Order> orderList, Root<RequestLog> root, CriteriaBuilder cb) {
		orderList.add(cb.asc(root.get("createDate")));	
	}
	
	public String getResponse(long id) {
		CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<String> query = cb.createQuery(String.class);
    	Root<RequestLog> from = query.from(entityClass);
    	
    	query.multiselect(
    		from.get("response"))
    			.where(cb.equal(from.get("id"), id)
    	);
    	
    	return entityManager.createQuery(query).getSingleResult();
	}
}