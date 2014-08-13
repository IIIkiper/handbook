package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.Dto;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public abstract class FastSearchGridPresenter <
		T extends Dto, 
		C extends DictionaryLoadConfig,
		E extends FastSearchGridPresenter.Display<T>
	> extends AbstractGridPresenter<T, C, E> {
	
	public interface Display<E extends Dto> extends AbstractGridPresenter.Display<E> {
		TextField getSearchField();
		TextButton getSearchButton();
		ToolBar getToolbar();
	}

	public FastSearchGridPresenter(Class<C> configClass, RestUri uri, E view) {
		super(configClass, uri, view);
	}
	
	protected void bind() {
		super.bind();
		
		view.getSearchField().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					fastFilter();
				}
			}
		});
		
		view.getSearchButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				fastFilter();
			}
		});
	}
	
	private void fastFilter() {
		String query = view.getSearchField().getCurrentValue();		
		if (query != null && query.isEmpty()) {
			query = null;
		}
		
//		if (query != null /*&& !query.isEmpty()*/  query.length() > 1) {
		C config = getLoadConfig(); //beanFactory.loadConfig().as();
		config.setLimit(loader.getLimit());
		config.setQuery(query);
		config.setOffset(0); // Start new query from the begining
		
		loader.load(config);
//		}		
	}
}