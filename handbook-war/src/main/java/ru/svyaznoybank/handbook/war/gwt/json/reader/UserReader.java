package ru.svyaznoybank.handbook.war.gwt.json.reader;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.User;
import ru.svyaznoybank.handbook.war.gwt.json.UserPagingResult;

public class UserReader extends IPagingResultReader<User, UserPagingResult> {

	public UserReader(IPagingResultABF<User, ? extends PagingLoadResult<User>, ? extends PagingLoadConfig> beanFactory) {
		super(beanFactory, UserPagingResult.class);
	}
}