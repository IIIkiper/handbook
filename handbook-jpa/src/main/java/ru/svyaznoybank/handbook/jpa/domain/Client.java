package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Client extends AbstractIdentity {

	@Embedded
	private ClientData clientData;
	
	@OneToMany(mappedBy = "client")
	private Set<Offer> offers;
	
	@OneToMany(mappedBy = "client")
	private Set<Handbook> handbooks;
	
	// --- Getters / Setters ---
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public Set<Handbook> getHandbooks() {
		return handbooks;
	}
	public void setHandbooks(Set<Handbook> handbooks) {
		this.handbooks = handbooks;
	}
	public ClientData getClientData() {
		return clientData;
	}
	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}
}