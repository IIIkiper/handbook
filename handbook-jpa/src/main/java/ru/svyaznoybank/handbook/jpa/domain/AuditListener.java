package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
public class AuditListener {
	/*
	 * Since JPA 2.1 entity listeners should support CDI, but to make this feature work I have to mark listener as 
	 * EJB stateless bean. EclipseLink bug?
	 */
	@Inject
	protected AuthDetails authDetails;
	
	@PrePersist
	public void prePersist(Identity obj) {
		
		if (obj instanceof AuditEntity) {
			AuditEntity entity = (AuditEntity) obj;
			entity.setCreateDate(new Date());
			entity.setCreator(authDetails.getUsername());
		} else if (obj instanceof RequestLog) {
			RequestLog entity = (RequestLog) obj;
			entity.setCreateDate(new Date());
		} else if (obj instanceof Ean) {
			Ean entity = (Ean) obj;
			entity.setCreateDate(new Date());
		} else if (obj instanceof User) {
			User user = (User) obj;
			user.setCreateDate(new Date());
			user.setCreator(authDetails.getUsername());		
		}
		
		preUpdate(obj);
	}
	
	@PreUpdate
	public void preUpdate(Identity obj) {
		
		if (obj instanceof AuditEntity) {
			AuditEntity entity = (AuditEntity) obj;
			entity.setUpdateDate(new Date());
			entity.setUpdater(authDetails.getUsername());
		} else if (obj instanceof RequestLog) {
			RequestLog entity = (RequestLog) obj;
			entity.setUpdateDate(new Date());
		} else if (obj instanceof Ean) {
			Ean entity = (Ean) obj;
			entity.setUpdateDate(new Date());
		}
	}
}