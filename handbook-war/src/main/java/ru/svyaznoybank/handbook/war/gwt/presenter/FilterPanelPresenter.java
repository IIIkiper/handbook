package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;

import com.google.gwt.user.client.ui.IsWidget;

public abstract class FilterPanelPresenter<T extends IsWidget, C extends DictionaryLoadConfig> extends Presenter<T> {

	public FilterPanelPresenter(T view) {
		super(view);
	}
	
	protected abstract void configure(C config);
}