package ru.svyaznoybank.handbook.war.gwt.view;

import com.google.gwt.uibinder.client.UiField;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;
import ru.svyaznoybank.handbook.war.gwt.presenter.FastSearchGridPresenter;

public abstract class FastSearchGrid<T extends Dto> extends AbstractGrid<T> implements FastSearchGridPresenter.Display<T> {
	
	@UiField
	public TextField searchField;
	
	@UiField
	public TextButton searchButton;
	
	@UiField
	public ToolBar toolbar;
	
	@Override
	public TextField getSearchField() {
		return searchField;
	}

	@Override
	public TextButton getSearchButton() {
		return searchButton;
	}
	
	@Override
	public ToolBar getToolbar() {
		return toolbar;
	}
}