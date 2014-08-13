package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import ru.svyaznoybank.handbook.war.gwt.json.User;
import ru.svyaznoybank.handbook.war.gwt.model.UserProperties;
import ru.svyaznoybank.handbook.war.gwt.presenter.AdminGridPresenter;

public class AdminGrid extends FastSearchGrid<User> implements AdminGridPresenter.Display {
	
	@UiField
	protected TabPanel tabPanel;
	
	@UiField
	protected TextButton addButton;
	
	@UiField
	protected TextButton deleteButton;
	
	private static UserProperties properties = GWT.create(UserProperties.class);
	
	private static final DateTimeFormat DTF_LONG = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	
	interface GUIBinder extends UiBinder<Widget, AdminGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public AdminGrid() {
		self = binder.createAndBindUi(this);
	}
	
	@Override
	protected void configureColumnConfigs(List<ColumnConfig<User, ?>> configs) {
		configs.add(new ColumnConfig<User, String>(properties.nickname(), 1, "Имя"));
		configs.add(new ColumnConfig<User, String>(properties.creator(), 1, "Добавлен"));			
		configs.add(new ColumnConfig<User, Date>(properties.createDate(), 1, "Дата добавления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		});
	}

	@Override
	public TabPanel getTabPanel() {
		return tabPanel;
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