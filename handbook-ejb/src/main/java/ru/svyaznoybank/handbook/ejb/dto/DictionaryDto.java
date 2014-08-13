package ru.svyaznoybank.handbook.ejb.dto;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.dic.Dictionary;

/**
 * Model for all "dictionary" entities with string ID.
 * @author Roman Zaripov
 */
public class DictionaryDto extends GenericDto {
	
	private String id;
	private String description;
	
	public DictionaryDto() { }

	public DictionaryDto(Dictionary entity) {
		BeanUtil.copyProperties(entity, this, "users");
	}

	// --- Getters / Settrs ---
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