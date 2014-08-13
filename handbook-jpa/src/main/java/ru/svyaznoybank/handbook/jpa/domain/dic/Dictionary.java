package ru.svyaznoybank.handbook.jpa.domain.dic;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import ru.svyaznoybank.handbook.jpa.domain.AuditEntity;
import ru.svyaznoybank.handbook.jpa.domain.Identity;

/**
 * Base class for all dictioanary entities. 
 * Due to historical reasons primary keys for respective tables 
 * are strings defined by users manually through GUI.
 * @author Roman Zaripov
 */
@MappedSuperclass
public abstract class Dictionary extends AuditEntity implements Identity {

	@Id
	@Column(name = "ID")
	@NotNull
	private String id;
	
	@Column(name = "DESCRIPTION")
	private String description;

	// --- Getters / Setters ---
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}