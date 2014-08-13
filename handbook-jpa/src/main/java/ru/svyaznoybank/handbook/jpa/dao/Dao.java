package ru.svyaznoybank.handbook.jpa.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.svyaznoybank.handbook.jpa.domain.Identity;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;

/**
 * Superclass for DAO objects
 * @author Roman Zaripov
 */
@SuppressWarnings("unchecked")
public abstract class Dao<T extends Identity> {
	
	protected final Logger log = Logger.getLogger(getClass().getName());
		
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final Class<T> entityClass;
	
    {
        Type type = this.getClass().getGenericSuperclass();
        try {
        	entityClass = (Class<T>) ((type instanceof ParameterizedType) ? ((ParameterizedType) type).getActualTypeArguments()[0] : type);
        } catch (ClassCastException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    protected CriteriaBuilder getCriteriaBuilder() {
    	return entityManager.getCriteriaBuilder();
    }
    
    protected <X> List<X> list(CriteriaQuery<X> criteria, int start, int limit, Order... orderBy) {
    	if (orderBy.length == 0) {
    		throw new IllegalArgumentException("Order must be specified for paging");
    	}
    	criteria.orderBy(orderBy);
    	return entityManager.createQuery(criteria)
	    	.setFirstResult(start)
	    	.setMaxResults(limit)
	    	.getResultList();  	
    }
    
    protected <X> List<X> list(CriteriaQuery<X> criteria, Inquiry inquiry, Order... orderBy) {
    	if (!inquiry.hasPaging()) {
    		throw new IllegalArgumentException("Paging is invalid");
    	}
    	return list(criteria, inquiry.getStart(), inquiry.getLimit(), orderBy);
    }
    
    protected <X> List<X> list(CriteriaQuery<X> criteria, int limit, Order... orderBy) {
    	return list(criteria, 0, limit, orderBy);
    }
    
    protected <X> List<X> list(CriteriaQuery<X> criteria) {
    	return entityManager.createQuery(criteria).getResultList();
    }
       
    public List<T> list() {
    	CriteriaBuilder cb = getCriteriaBuilder();
    	CriteriaQuery<T> query = cb.createQuery(entityClass);
    	Root<T> from = query.from(entityClass);  	
    	return list(query.select(from));	
    }
    
    protected <X> X single(CriteriaQuery<X> criteria) {
    	try {
    		return entityManager.createQuery(criteria).getSingleResult();
    	} catch (Exception ex) {
    		return null;
    	}
    }
    
    public long count() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
    	Root<T> root = criteria.from(entityClass);
    	return single(criteria.select(cb.count(root)));
    } 
    
    protected void initializeAssociations(FetchParent<?, ?> fetchParent, Collection<String> associations) {
    	for (String association : associations) {
    		//fetchParent.fetch(association, JoinType.LEFT);
    		FetchParent<?, ?> currentFetch = fetchParent;
            for (String pathSegment : association.split("\\.")) {
            	currentFetch = currentFetch.fetch(pathSegment, JoinType.LEFT);
            }
    	}
    }
    
    protected void initializeAssociations(FetchParent<?, ?> fetchParent, String... associations) {
    	initializeAssociations(fetchParent, Arrays.asList(associations));
    }
    
    protected <X extends Comparable<? super X>> void addBetween(CriteriaBuilder cb, Collection<Predicate> p, Expression<? extends X> expr, X min, X max) {
    	if (min != null) {
    		p.add(cb.greaterThanOrEqualTo(expr, min));
    	}
    	if (max != null) {
    		p.add(cb.lessThanOrEqualTo(expr, max));
    	}
    }
    
    protected <X extends Identity> Predicate getOmni(final CriteriaBuilder cb, Class<X> entityClass, String omni, final From<?, ?> from, String... includeFields) {
    	List<Predicate> predicates = new ArrayList<>();
    	getOmni(cb, entityClass, omni, from, predicates, includeFields);
    	return cb.or(predicates.toArray(new Predicate[] {}));
    }
    
    private void getOmni(final CriteriaBuilder cb, Class<?> entityClass, String omni, final Path<?> from, List<Predicate> predicates, String... includeFields) {
    	if (omni == null) {
    		throw new IllegalArgumentException("Omni search string should be specified");
    	}
    	
    	final String lowerOmni = omni.toLowerCase();
    	
    	final Set<String> names = new HashSet<>();
    	if (includeFields.length != 0) {
    		names.addAll(Arrays.asList(includeFields));
    	}
    	
    	Class<?> clazz = entityClass;
    	do {
    		for (Field field : clazz.getDeclaredFields()) {
    			if ((names.isEmpty() ? true : names.contains(field.getName())) &&
    					field.isAnnotationPresent(Column.class) && 
//    					!field.isAnnotationPresent(Id.class) &&
    					!field.isAnnotationPresent(Version.class) &&
    					field.getType().equals(String.class)) {
    				predicates.add(cb.like(cb.lower(from.<String>get(field.getName())), "%" +  lowerOmni + "%"));
    			} else if (field.isAnnotationPresent(Embedded.class)) {
    				getOmni(cb, field.getType(), lowerOmni, from.get(field.getName()), predicates, includeFields);
    			}
    		}
    		
    		clazz = clazz.getSuperclass();
    	} while (clazz != null && clazz != Object.class);
    }
    
    protected <X extends Identity> Predicate getExample(final CriteriaBuilder cb, final X entity, final From<?, ?> from, String... includeFields) {
    	List<Predicate> predicates = new ArrayList<>();
    	getExample(cb, entity, from, predicates, includeFields);
    	return cb.and(predicates.toArray(new Predicate[] {}));
    }
    
    private  void getExample(final CriteriaBuilder cb, final Object entity, final Path<?> from, List<Predicate> predicates, String... includeFields) {
    	if (entity == null) {
    		throw new IllegalArgumentException("Example entity cannot be null");
    	}
    	
    	final Set<String> names = new HashSet<>();
    	if (includeFields.length != 0) {
    		names.addAll(Arrays.asList(includeFields));
    	}
    	
    	Class<?> clazz = entity.getClass();
    	do {
    		for (Field field : clazz.getDeclaredFields()) {
    			try {    				
    				field.setAccessible(true);
    				Object value = field.get(entity);   				
    				if (value != null) {
		    			if ((names.isEmpty() ? true : names.contains(field.getName())) && 
	    					field.isAnnotationPresent(Column.class) && 
	    					!field.isAnnotationPresent(Id.class) &&
	    					!field.isAnnotationPresent(Version.class)) {
			    				if (field.getType().equals(String.class)) {   					
			    					predicates.add(cb.like(cb.lower(from.<String>get(field.getName())), "%" + ((String) value).toLowerCase() + "%"));
			    				} else {
			    					predicates.add(cb.equal(from.get(field.getName()), value));
			    				}	    				
		    			} else if (field.isAnnotationPresent(Embedded.class)) {
		    				getExample(cb, value, from.get(field.getName()), predicates, includeFields);
		    			}
    				}
    			} catch (IllegalAccessException ex) { }
    		}
    		
    		clazz = clazz.getSuperclass();
    	} while (clazz != null && clazz != Object.class);
    }
    
    public T getById(Serializable id) {
    	return getById(id, entityClass);
    }
    
    public void removeById(Serializable id) {
    	remove(getById(id));
    }
    
    public <X extends Identity> void removeById(Serializable id, Class<X> clazz) {
    	remove(getById(id, clazz));
    }
     
    public <X> X getById(Serializable id, Class<X> clazz) {
    	return id != null ? entityManager.find(clazz, id) : null;
    }
    
    public T getById(Serializable id, String... associations) {
    	return getById(id, entityClass, associations);
    }
    
    public <X extends Identity> X getById(Serializable id, Class<X> clazz, String... associations) {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<X> query= cb.createQuery(clazz);
    	Root<X> from = query.from(clazz);
    	
    	initializeAssociations(from, associations);
    	
    	return single(
			query.select(from)
				.where(cb.equal(from.get("id"), id))
    	);
    }
    
    public <X extends Identity> void persist(X entity) {
    	if (entity != null) {
    		entityManager.persist(entity);
    	}
    }
    
    public <X extends Identity> X merge(X entity) {
    	if (entity != null) {
    		return entityManager.merge(entity);
    	}
		throw new NullPointerException();
    }
        
    public <X> void remove(X entity) {
    	if (entity != null) {
    		entityManager.remove(entity);
    	}
    }
    
    public void flush() {
    	entityManager.flush();
    }
    
    public <X extends Identity> void lock(X entity, LockModeType lockType) {
    	entityManager.lock(entity, lockType);
    }
}