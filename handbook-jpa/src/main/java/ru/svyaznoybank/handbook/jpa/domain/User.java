package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;
import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;

@Entity
@Table(name = "USERS")
@EntityListeners(AuditListener.class)
public class User implements Identity {
	
	private static final String SEQ_NAME = "user-seq";
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(sequenceName = "S_USERS", allocationSize = 1, name = SEQ_NAME)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
	private Long id;
	
	@Column(name = "S_FULL_NAME")
	private String name;
	
	@Column(name = "S_NAME", nullable = false)
	private String nickname;
	
	@Column(name = "CREATED_BY", updatable = false)
	private String creator;
	
	@Column(name = "CREATE_TIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "L_USERS_HANDBOOK_TYPE",
		joinColumns = @JoinColumn(name = "ID_USER"),
		inverseJoinColumns = @JoinColumn(name = "ID_HANDBOOK_TYPE")
	)
	private Set<HandbookType> handbookTypes;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "L_USERS_LIST_TYPE",
		joinColumns = @JoinColumn(name = "ID_USER"),
		inverseJoinColumns = @JoinColumn(name = "ID_LIST_TYPE")
	)
	private Set<OfferType> offerTypes;

	// --- Getters / Setters ---
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Set<HandbookType> getHandbookTypes() {
		return handbookTypes;
	}
	public void setHandbookTypes(Set<HandbookType> handbookTypes) {
		this.handbookTypes = handbookTypes;
	}
	public Set<OfferType> getOfferTypes() {
		return offerTypes;
	}
	public void setOfferTypes(Set<OfferType> offerTypes) {
		this.offerTypes = offerTypes;
	}
}