package ru.svyaznoybank.handbook.war.gwt.json;

import java.util.List;

// TODO PagingLoadResult

public interface IPagingResult<T extends Dto> {
	
	List<T> getList();
	int getTotal();
	int getOffset();
}
