package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;
import ru.svyaznoybank.handbook.war.gwt.json.reader.IPagingResultABF;
import ru.svyaznoybank.handbook.war.gwt.rest.RestProxy;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUrlEncodingWriter;
import ru.svyaznoybank.handbook.war.gwt.view.PagingBar;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;

public abstract class AbstractGridPresenter <
		T extends Dto, 
		C extends PagingLoadConfig,
		E extends AbstractGridPresenter.Display<T>
	> extends Presenter<E> {
	
	protected PagingLoader<C, PagingLoadResult<T>> loader;
	protected RestProxy<C, T> proxy;
	
	protected String uri;
	
	protected final Class<C> configClass;
	
	protected final IPagingResultABF<T, ? extends PagingLoadResult<T>, C> beanFactory;

	public interface Display<T extends Dto> extends IsWidget {
		Grid<T> getGrid();
		PagingBar getPagingBar();
		ListStore<T> getStore();
		RowNumberer<T> getRowNumberer();
	}
	
	public AbstractGridPresenter(Class<C> configClass, RestUri uri, E view) {
		super(view);
		this.uri = uri.getUri();
		this.configClass = configClass;
		beanFactory = getBeanFactory();
	}

	@Override
	protected void bind() {
		proxy = new RestProxy<C, T>(uri);
		proxy.setWriter(new RestUrlEncodingWriter/*UrlEncodingWriter*/<C>(beanFactory, configClass));
		
		loader = new PagingLoader<C, PagingLoadResult<T>>(proxy, getReader()) {
			protected C newLoadConfig()  {
				return getLoadConfig();
			}			
		};
		loader.setLimit(view.getPagingBar().getPageSize());
		
		view.getGrid().setLoader(loader);
		
		loader.addLoadHandler(new LoadResultListStoreBinding<C, T, PagingLoadResult<T>>(view.getStore()));
		
		view.getGrid().addViewReadyHandler(new ViewReadyHandler() {		
			@Override
			public void onViewReady(ViewReadyEvent event) {
				loader.load();
			}
		});
				
		view.getRowNumberer().initPlugin(view.getGrid());
						
		view.getPagingBar().bind(loader);
	}
	
	protected C getLoadConfig() {
		return beanFactory.loadConfig().as();
	}
		
	protected abstract JsonReader<PagingLoadResult<T>, ? extends PagingLoadResult<T>> getReader();
	
	protected abstract IPagingResultABF<T, ? extends PagingLoadResult<T>, C> getBeanFactory();
}