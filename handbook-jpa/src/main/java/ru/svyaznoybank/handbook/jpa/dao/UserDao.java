package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.domain.User;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;

@Stateless
public class UserDao extends FilterableDao<User, Inquiry> {
	
	@Resource
	private SessionContext ctx;

	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, User> root, CriteriaBuilder cb, Inquiry inquiry) {
		if (inquiry.getQuery() != null && !inquiry.getQuery().isEmpty()) {
			p.add(getOmni(cb, entityClass, inquiry.getQuery(), root, "nickname", "name"));
		}
		
		// Admin cannot see himself in the result list
		p.add(cb.notEqual(root.get("nickname"), ctx.getCallerPrincipal().getName()));
	}

	@Override
	protected void configureOrder(List<Order> orderList, Root<User> root, CriteriaBuilder cb) {
		orderList.add(cb.asc(root.get("createDate")));
	}
	
	public Long getIdByNickname(String nickname) {
		CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<Long> query = cb.createQuery(Long.class);
    	Root<User> from = query.from(entityClass);	
    	return single(
    		query
    		.select(from.<Long>get("id"))
    		.where(cb.equal(from.get("nickname"), nickname))
    	);
	}
	
	public List<String> getOfferTypesByNickname(String nickname) {
    	CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<String> query = cb.createQuery(String.class);
    	Root<User> from = query.from(entityClass);
    	
    	Path<String> offerTypeId = from.join("offerTypes"/*, JoinType.INNER*/).get("id");
    	
    	return list(
			query
			.select(offerTypeId)
			.where(
				cb.equal(
					cb.lower(from.<String>get("nickname")), 
					nickname.toLowerCase()
				)
			)
		);
	}
	
	public List<String> getHandbookTypesByNickname(String nickname) {
    	CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<String> query = cb.createQuery(String.class);
    	Root<User> from = query.from(entityClass);
    	
    	Path<String> handbookTypeId = from.join("handbookTypes"/*, JoinType.INNER*/).get("id");
    	
    	return list(
			query
			.select(handbookTypeId)
			.where(
				cb.equal(
					cb.lower(from.<String>get("nickname")), 
					nickname.toLowerCase()
				)
			)
		);
	}
}