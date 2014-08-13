package ru.svyaznoybank.handbook.war.gwt.json.reader;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;

import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class IPagingResultReader<T extends Dto, P extends PagingLoadResult<T>> extends JsonReader<PagingLoadResult<T>, P> {

	public IPagingResultReader(IPagingResultABF<T, ? extends PagingLoadResult<T>, ? extends PagingLoadConfig> beanFactory, Class<P> rootBeanType) {
		super(beanFactory, rootBeanType);
	}
	
	@Override
	protected PagingLoadResult<T> createReturnData(Object loadConfig, P result) {
		return new PagingLoadResultBean<T>(result.getData(), result.getTotalLength(), result.getOffset());
	}
}