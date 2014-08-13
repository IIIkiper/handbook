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
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import ru.svyaznoybank.handbook.war.gwt.json.Client;
import ru.svyaznoybank.handbook.war.gwt.model.ClientProperties;
import ru.svyaznoybank.handbook.war.gwt.presenter.ClientGridPresenter;

public class ClientGrid extends FastSearchGrid<Client> implements ClientGridPresenter.Display {
	
	@UiField
	protected TabPanel tabPanel;
	
	private static ClientProperties properties = GWT.create(ClientProperties.class);
	
	private static final DateTimeFormat DTF_LONG = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	private static final DateTimeFormat DTF_SHORT = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	interface GUIBinder extends UiBinder<Widget, ClientGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public ClientGrid() {		
		self = binder.createAndBindUi(this);
	}
	
	@Override
	protected void configureColumnConfigs(List<ColumnConfig<Client, ?>> configs) {
		configs.add(new ColumnConfig<Client, String>(properties.firstName(), 1, "Имя"));
		configs.add(new ColumnConfig<Client, String>(properties.lastName(), 1, "Фамилия"));
		configs.add(new ColumnConfig<Client, String>(properties.patronymic(), 1, "Отчество"));
		configs.add(new ColumnConfig<Client, Long>(properties.clientId(), 1, "ID клиента"));
		configs.add(new ColumnConfig<Client, Date>(properties.birthday(), 1, "Дата рождения") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		configs.add(new ColumnConfig<Client, String>(properties.docSeries(), 1, "Серия документа"));
		configs.add(new ColumnConfig<Client, String>(properties.docNumber(), 1, "Номер документа"));
		configs.add(new ColumnConfig<Client, Date>(properties.docIssueDate(), 1, "Дата выдачи") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		
		ColumnConfig<Client, String> comment = new ColumnConfig<Client, String>(properties.comment(), 1, "Комментарий");
		comment.setHidden(true);
		configs.add(comment);
		
		ColumnConfig<Client, Date> createDate = new ColumnConfig<Client, Date>(properties.createDate(), 1, "Дата создания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		createDate.setHidden(true);
		configs.add(createDate);
		
		ColumnConfig<Client, String> creator = new ColumnConfig<Client, String>(properties.creator(), 1, "Создан");
		creator.setHidden(true);
		configs.add(creator);
		
		ColumnConfig<Client, Date> updateDate = new ColumnConfig<Client, Date>(properties.createDate(), 1, "Дата обновления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		updateDate.setHidden(true);
		configs.add(updateDate);
		
		ColumnConfig<Client, String> updater = new ColumnConfig<Client, String>(properties.creator(), 1, "Обновлен");
		updater.setHidden(true);
		configs.add(updater);
	}

	@Override
	public TabPanel getTabPanel() {
		return tabPanel;
	}
}