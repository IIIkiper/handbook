package ru.svyaznoybank.handbook.war.gwt.json.reader;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.Client;
import ru.svyaznoybank.handbook.war.gwt.json.ClientLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.ClientPagingResult;

public class ClientReader extends IPagingResultReader<Client, ClientPagingResult> {

	public ClientReader(IPagingResultABF<Client, ? extends PagingLoadResult<Client>, ClientLoadConfig> beanFactory) {
		super(beanFactory, ClientPagingResult.class);
	}
}