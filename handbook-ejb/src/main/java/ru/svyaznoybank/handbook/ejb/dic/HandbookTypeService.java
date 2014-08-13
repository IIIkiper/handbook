package ru.svyaznoybank.handbook.ejb.dic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.UserDao;
import ru.svyaznoybank.handbook.jpa.dao.dic.HandbookTypeDao;
import ru.svyaznoybank.handbook.jpa.domain.User;
import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;

@Stateless
public class HandbookTypeService extends DictionaryService<HandbookType> {
	
	@EJB
	private HandbookTypeDao dao;
	
	@EJB
	private UserDao userDao;

	@Override
	protected HandbookTypeDao getDao() {
		return dao;
	} 
	
	public PagingResult<DictionaryDto> getReverse(Long userId) {
		List<DictionaryDto> list = new ArrayList<>();
		for (HandbookType ht : dao.getReverse(userId)) {
			list.add(new DictionaryDto(ht));
		}
		return new PagingResult<DictionaryDto>(list, 0, 0);
	}
	
	@TransactionAttribute
	public DictionaryDto addUserHandbookType(Long userId, HandbookType handbookType) {
		if (handbookType.getId() == null || userId == null) {
			throw new IllegalArgumentException();
		}
		
		HandbookType ot = getEntity(handbookType.getId());
		User user = userDao.getById(userId);
		user.getHandbookTypes().add(ot);
		
		return new DictionaryDto(ot);
	}
	
	@TransactionAttribute
	public void deleteUserHandbookType(Long userId, String handbookTypeId) {
		if (handbookTypeId == null || userId == null) {
			throw new IllegalArgumentException();
		}
		
		HandbookType ot = getEntity(handbookTypeId);
		User user = userDao.getById(userId);
		
		user.getHandbookTypes().remove(ot);
	}
}