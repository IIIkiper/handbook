package ru.svyaznoybank.handbook.ejb.dic;

import javax.annotation.security.DeclareRoles;
import javax.ejb.TransactionAttribute;

import ru.svyaznoybank.handbook.ejb.Service;
import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.dic.DictionaryDao;
import ru.svyaznoybank.handbook.jpa.domain.dic.Dictionary;
import ru.svyaznoybank.handbook.jpa.inquiry.DictionaryInquiry;
import ru.svyaznoybank.handbook.security.AuthDetails;

@DeclareRoles({AuthDetails.ADMIN_ROLE, AuthDetails.OPERATOR_ROLE})
public abstract class DictionaryService<E extends Dictionary> extends Service<E> {
	
	@Override
	protected abstract DictionaryDao<E> getDao();
	
	public PagingResult<DictionaryDto> get(DictionaryInquiry inquiry) {
		return new PagingResult<>(DictionaryDto.class, Dictionary.class, getDao(), inquiry);
	}
	
	@TransactionAttribute
	public DictionaryDto update(E entity) {
		if (entity.getId() == null) {
			throw new IllegalArgumentException("");
		}
		
		E persistedEntity = getEntity(entity.getId());
		// Only description can be updated
		persistedEntity.setDescription(entity.getDescription());

		getDao().flush(); 

		return new DictionaryDto(persistedEntity);// getDao().merge(persistedEntity);
	}
}