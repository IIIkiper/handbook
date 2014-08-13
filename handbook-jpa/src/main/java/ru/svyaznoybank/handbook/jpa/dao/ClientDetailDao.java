package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.domain.AbstractIdentity;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;

public abstract class ClientDetailDao<T extends AbstractIdentity> extends FilterableDao<T, ClientDetailInquiry> {

	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, T> root, CriteriaBuilder cb, ClientDetailInquiry inquiry) {
		// Supposed to be extended with classes, that have clientId property.
		p.add(cb.equal(root.get("clientId"), inquiry.getClientId()));
	}

	@Override
	protected void configureOrder(List<Order> orderList, Root<T> root, CriteriaBuilder cb) {
		orderList.add(cb.asc(root.get("createDate")));
	}
}