package ru.svyaznoybank.handbook.jpa.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import ru.svyaznoybank.handbook.jpa.domain.Handbook;
import ru.svyaznoybank.handbook.jpa.inquiry.ClientDetailInquiry;
import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;
import ru.svyaznoybank.handbook.security.AuthDetails;

@Stateless
//@DeclareRoles({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
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
	
	public List<Handbook> getHandbooks(HandbookSoapParams params, Date requestDate) {
		return entityManager.createNamedQuery(Handbook.LAST_HANDBOOKS_IN_TYPE_ID_GROUPS_QUERY, entityClass)
			.setParameter("LAST_NAME", params.getLastName())
			.setParameter("FIRST_NAME", params.getFirstName())
			.setParameter("SECOND_NAME", params.getPatronymic())
			.setParameter("BIRTHDAY", params.getBirthday(), TemporalType.DATE)
			.setParameter("DOC_TYPE", params.getDocType())
			.setParameter("DOC_SERIES", params.getDocSeries())
			.setParameter("DOC_NUMBER", params.getDocNumber())
			.setParameter("PHONE_NUMBER", params.getPhoneNumber())
			.setParameter("CUSTOMER_ID", params.getCustomerId())
			.setParameter("PRODUCT_ID", params.getProductId())
			.setParameter("CURRENCY_ID", params.getCurrencyId())
			.setParameter("REQUEST_DATE", requestDate, TemporalType.DATE)
			.getResultList();
	}
}