package ru.svyaznoybank.handbook.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.svyaznoybank.handbook.ejb.dto.UserDto;
import ru.svyaznoybank.handbook.ejb.util.PagingResult;
import ru.svyaznoybank.handbook.jpa.dao.Dao;
import ru.svyaznoybank.handbook.jpa.dao.UserDao;
import ru.svyaznoybank.handbook.jpa.domain.User;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;

@Stateless
public class UserService extends Service<User> {
	
	@EJB
	private UserDao dao;

	@Override
	protected Dao<User> getDao() {
		return dao;
	} 
	
	public PagingResult<UserDto> get(Inquiry inquiry) {
		return new PagingResult<UserDto>(UserDto.class, User.class, dao, inquiry);
	}
}