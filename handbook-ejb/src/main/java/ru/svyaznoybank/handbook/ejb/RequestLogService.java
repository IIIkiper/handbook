package ru.svyaznoybank.handbook.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.ejb.dto.RequestLogDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.dao.RequestLogDao;
import ru.svyaznoybank.handbook.jpa.domain.RequestLog;
import ru.svyaznoybank.handbook.jpa.inquiry.RequestLogInquiry;

@Stateless
public class RequestLogService extends Service<RequestLog> {
	
	@EJB
	private RequestLogDao dao;
	
	public PagingResult<RequestLogDto> get(RequestLogInquiry inquiry) {
		return new PagingResult<>(RequestLogDto.class, RequestLog.class, dao, inquiry);
	}
	
	public String getResponse(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		return dao.getResponse(id);
	}

	@Override
	protected Dao<RequestLog> getDao() {
		return dao;
	}
}