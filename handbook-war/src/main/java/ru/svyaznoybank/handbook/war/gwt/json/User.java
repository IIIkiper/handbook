package ru.svyaznoybank.handbook.war.gwt.json;

/**
 * Model for user entity.
 * This class designed as {@link Dto} subclass mostly for code reuse.
 * #updateDate and #updater properties never use here.
 * @author Roman Zaripov
 */
public interface User extends Dto {
	
	public Long getId();
	public void setId(Long id);

	public String getNickname();
	public void setNickname(String nickname);
}