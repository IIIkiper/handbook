package ru.svyaznoybank.handbook.ejb;

import java.io.Serializable;

import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.domain.Identity;

public abstract class Service<T extends Identity> {
	
	protected abstract Dao<T> getDao();
	
	public T getEntity(Serializable id) {
		return getDao().getById(id);
	}
	
	/*
	public T getEntity(Serializable id, String... associations) {
		return getDao().getById(id, associations);
	}
	*/
	
	@TransactionAttribute
	public void removeEntityById(Serializable id) {
		getDao().removeById(id);
	}
	
	@TransactionAttribute
	public void persistEntity(T entity) {
		getDao().persist(entity);
	}
	
	@TransactionAttribute
	public T updateEntiy(T entity, String... ignoreProperties) {
		if (entity.getId() == null) {
			throw new IllegalArgumentException("");
		}
		
		T persistedEntity = getEntity(entity.getId());
		BeanUtil.copyProperties(entity, persistedEntity, ignoreProperties);
		
//		getDao().flush(); 

		return getDao().merge(persistedEntity);
	}
}