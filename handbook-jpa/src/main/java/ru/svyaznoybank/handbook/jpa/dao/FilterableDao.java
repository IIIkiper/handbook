package ru.svyaznoybank.handbook.jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.domain.Identity;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;

public abstract class FilterableDao<T extends Identity, E extends Inquiry> extends Dao<T> {
	
    public List<T> filter(E params) {    	
    	CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<T> query = cb.createQuery(entityClass);
    	Root<T> from = query.from(entityClass);
    	
    	Collection<Predicate> predicates = new HashSet<>();
    	configurePredicates(predicates, query, from, cb, params);
    	
    	List<Order> orderList = new ArrayList<>();
//    	if (params.getSort() != null) {
//    		for (Sort sort : params.getSort()) {
//    			switch (sort.getDirection()) {
//					case ASC: orderList.add(cb.asc(from.get(sort.getProperty()))); break;
//					case DESC: orderList.add(cb.desc(from.get(sort.getProperty()))); break;
//    			}
//    		}
//    	} else {
	    	configureOrder(orderList, from, cb);
	    	if (orderList.isEmpty()) {
	    		throw new IllegalArgumentException("Order must be specified for paging");
	    	}
//    	}
    	   	
    	query.select(getSelection(from, cb))
    		.where(predicates.toArray(new Predicate[] {}))
    		.orderBy(orderList);
    	
    	initializeAssociations(from, params.getAssociationsToFetch());
    	
    	TypedQuery<T> tQuery = entityManager.createQuery(query);
    	if (params.hasPaging()) {
    		tQuery.setFirstResult(params.getStart())
	    		.setMaxResults(params.getLimit());
    	}
    	/*
    	if (params.isCacheable()) {
    		tQuery.setHint("org.hibernate.cacheable", true);
    	}
    	*/
    	return tQuery.getResultList();
    }
        
    public long count(E dto) {
       	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
    	Root<T> from = criteria.from(entityClass);
    	Collection<Predicate> predicates = new HashSet<>();
    	configurePredicates(predicates, criteria, from, cb, dto);
    	return single(
    		criteria
    			.select(cb.count(from))
    			.where(predicates.toArray(new Predicate[] {}))
    	);
    }
    
    protected Selection<? extends T> getSelection(From<?, T> from, CriteriaBuilder cb) {
    	return from;
    }
    
    protected abstract void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query,  From<?, T> root, CriteriaBuilder cb, E inquiry);
    
    protected abstract void configureOrder(List<Order> orderList, Root<T> root, CriteriaBuilder cb);
    
	protected List<String> getDistinctProperty(String propertyName, Inquiry params) {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<String> query = cb.createQuery(String.class);
    	Root<T> from = query.from(entityClass);
    	
    	Path<?> path = from.get(propertyName);
    	   	
    	List<Predicate> p = new ArrayList<>();
    	if (params.getQuery() != null && !params.getQuery().isEmpty()) {
    		p.add(getOmni(cb, entityClass, params.getQuery(), from, propertyName));
    	}
    	p.add(path.isNotNull());
    	
		query
			.multiselect(path)
			.where(p.toArray(new Predicate[0]))
			.distinct(true)
			.orderBy(cb.asc(path));
		
    	TypedQuery<String> tQuery = entityManager.createQuery(query);
    	/*
    	if (params.isCacheable()) {
    		tQuery.setHint("org.hibernate.cacheable", true);
    	}
    	*/
    	return tQuery.getResultList();
	}
}