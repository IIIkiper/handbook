package ru.svyaznoybank.handbook.ejb.dic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.UserDao;
import ru.svyaznoybank.handbook.jpa.dao.dic.OfferTypeDao;
import ru.svyaznoybank.handbook.jpa.domain.User;
import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;

@Stateless
public class OfferTypeService extends DictionaryService<OfferType> {
	
	@EJB
	private OfferTypeDao dao;
	
	@EJB
	private UserDao userDao;

	@Override
	protected OfferTypeDao getDao() {
		return dao;
	} 
	
	public PagingResult<DictionaryDto> getReverse(Long userId) {
		List<DictionaryDto> list = new ArrayList<>();
		for (OfferType  ht : dao.getReverse(userId)) {
			list.add(new DictionaryDto(ht));
		}
		return new PagingResult<DictionaryDto>(list, 0, 0);		
	}
	
	@TransactionAttribute
	public DictionaryDto addUserOfferType(Long userId, OfferType offerType) {
		if (offerType.getId() == null || userId == null) {
			throw new IllegalArgumentException();
		}
		
		OfferType ot = getEntity(offerType.getId());
		User user = userDao.getById(userId);
		user.getOfferTypes().add(ot);
		
		return new DictionaryDto(ot);
	}
	
	@TransactionAttribute
	public void deleteUserOfferType(Long userId, String offerTypeId) {
		if (offerTypeId == null || userId == null) {
			throw new IllegalArgumentException();
		}
		
		OfferType ot = getEntity(offerTypeId);
		User user = userDao.getById(userId);
		user.getOfferTypes().remove(ot);
	}
}