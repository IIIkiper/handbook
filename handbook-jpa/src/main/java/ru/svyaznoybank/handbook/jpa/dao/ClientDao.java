package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.domain.Client;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientInquiry;

@Stateless
public class ClientDao extends FilterableDao<Client, ClientInquiry> {

	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, Client> root, CriteriaBuilder cb, ClientInquiry inquiry) {
		if (inquiry.getQuery() != null && !inquiry.getQuery().isEmpty()) {
			p.add(getOmni(cb, entityClass, inquiry.getQuery(), root, 
				"firstName",
				"lastName",
				"clientId",
				"docSeries",
				"docNumber"
			));
		} else if (inquiry.getExample() != null) {
			p.add(getExample(cb, inquiry.getExample()/*.getClientData()*/, root));
		}
	}

	@Override
	protected void configureOrder(List<Order> orderList, Root<Client> root, CriteriaBuilder cb) {
		// TODO ? 
		orderList.add(cb.asc(root.get("createDate")));
		/*
		orderList.add(cb.asc(root.get("clientData").get("firstName")));
		orderList.add(cb.asc(root.get("clientData").get("lastName")));
		*/
	}
}