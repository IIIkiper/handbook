package ru.svyaznoybank.handbook.jpa.domain.dic;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ru.svyaznoybank.handbook.jpa.domain.User;

@Entity
@Table(name = "HANDBOOK_TYPE")
public class HandbookType extends Dictionary { 
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "handbookTypes")
	private Set<User> users;

	// --- Getters / Setters ---
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}