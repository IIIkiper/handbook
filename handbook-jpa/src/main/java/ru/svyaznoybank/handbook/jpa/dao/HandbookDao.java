package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import ru.svyaznoybank.handbook.jpa.domain.Handbook;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
@DeclareRoles({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class HandbookDao extends ClientDetailDao<Handbook> {
	
	@Inject
	private AuthDetails authDetails;
	
	@Resource
	private SessionContext ctx;
	
	@Override
	protected void configurePredicates(Collection<Predicate> p, CriteriaQuery<?> query, From<?, Handbook> root, CriteriaBuilder cb, ClientDetailInquiry inquiry) {
		super.configurePredicates(p, query, root, cb, inquiry);
				
		if (!authDetails.isAdmin()) {
			System.out.println("Handbook not admin");
			p.add(root.get("handbookTypeId").in(
				authDetails.getHandbookTypes().isEmpty() ?
				cb.nullLiteral(String.class) :
				authDetails.getHandbookTypes()
			));
		}
	}
}