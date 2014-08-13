package ru.svyaznoybank.handbook.ejb.dto;

import ru.svyaznoybank.handbook.jpa.domain.AbstractIdentity;

/**
 * Base model for all entities with numeric ID.
 * @author Roman Zaripov
 */
public abstract class EntityDto extends GenericDto {
	
	private Long id;
	
	public EntityDto() { }
	
	public EntityDto(AbstractIdentity entity) {		
		super(entity);
	}

	// --- Getters / Setters ---
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}