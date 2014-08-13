package ru.svyaznoybank.handbook.jpa.dao.dic;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.dao.FilterableDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.Dictionary;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;

public abstract class DictionaryDao<T extends Dictionary> extends FilterableDao<T, DictionaryInquiry> {

	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, T> root, CriteriaBuilder cb, DictionaryInquiry params) {
		if (params.getQuery() != null && !params.getQuery().isEmpty()) {
			p.add(getOmni(cb, entityClass, params.getQuery(), root, "id"));
		}
		
		/*
		if (params.getIds() != null && !params.getIds().isEmpty()) {
			p.add(root.get("id").in(params.getIds()));
		}
		*/
		
		//params.makeCacheable(true);
	}

	@Override
	protected void configureOrder(List<Order> orderList, Root<T> root, CriteriaBuilder cb) {
		orderList.add(cb.asc(root.get("id")));
	}
}