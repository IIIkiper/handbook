package ru.svyaznoybank.handbook.security;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;

import ru.svyaznoybank.handbook.jpa.dao.UserDao;

@SessionScoped
@SuppressWarnings("serial")
@DeclareRoles({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public class AuthDetails implements Serializable {
	
	private static final Logger LOG = Logger.getLogger(AuthDetails.class.getName());
	
	public static final String ADMIN_ROLE = "HandbookAdmin";
	public static final String OPERATOR_ROLE = "HandbookOperator";
	
	@Resource
	private SessionContext ctx;
	
	@EJB
	private UserDao userDao;
	
	private String username;
	private boolean admin;
	
	private Long userId;

	private List<String> handbookTypes = Collections.emptyList();
	private List<String> offerTypes = Collections.emptyList();
	
	@PostConstruct
	public void init() {
		username = ctx.getCallerPrincipal().getName();
		admin = ctx.isCallerInRole(ADMIN_ROLE);
		if (!admin) {		
			handbookTypes = userDao.getHandbookTypesByNickname(username);
			offerTypes = userDao.getOfferTypesByNickname(username);
			userId = userDao.getIdByNickname(username);
		}
				
//		if (LOG.isLoggable(Level.FINE)) {
			LOG.log(
				Level.INFO,
				"User [{0}] is admin [{1}], has ID [{2}]. Allowed handbook types: {3}. Allowed offer types: {4}", 
				new Object[] {username, admin, userId, handbookTypes, offerTypes}
			);
//		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public List<String> getOfferTypes() {
		return offerTypes;
	}
	
	public List<String> getHandbookTypes() {
		return handbookTypes;
	}
	
	public Long getUserId() {
		return userId;
	}
}