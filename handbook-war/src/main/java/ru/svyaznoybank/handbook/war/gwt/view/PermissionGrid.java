package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.List;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.model.DictionaryProperties;

import ru.svyaznoybank.handbook.war.gwt.presenter.PermissionGridPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class PermissionGrid extends FastSearchGrid<Dictionary> implements PermissionGridPresenter.Display {
	
	@UiField
	protected TextButton addButton;

	@UiField
	protected TextButton deleteButton;

	private static DictionaryProperties properties = GWT.create(DictionaryProperties.class);
	
	interface GUIBinder extends UiBinder<Widget, PermissionGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public PermissionGrid() {
		self = binder.createAndBindUi(this);
	}

	@Override
	protected void configureColumnConfigs(List<ColumnConfig<Dictionary, ?>> configs) {
		configs.add(new ColumnConfig<Dictionary, String>(properties.name(), 1, "Название"));
		configs.add(new ColumnConfig<Dictionary, String>(properties.description(), 2, "Описание"));
	}
	
	@Override
	public TextButton getAddButton() {
		return addButton;
	}

	@Override
	public TextButton getDeleteButton() {
		return deleteButton;
	}
}