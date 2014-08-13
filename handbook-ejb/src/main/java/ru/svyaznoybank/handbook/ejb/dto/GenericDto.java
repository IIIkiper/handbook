package ru.svyaznoybank.handbook.ejb.dto;

import java.util.Date;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.AuditEntity;

/**
 * Base interfae for all model classes.
 * @author Roman Zaripov
 */
public abstract class GenericDto {
	
	private Date createDate;
	private Date updateDate;
	private String creator;
	private String updater;
	
	public GenericDto() { }
	
	public GenericDto(AuditEntity entity) {
		BeanUtil.copyProperties(entity, this);
	}
	
	public abstract Object getId();

	// --- Getters / Setters ---
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
}