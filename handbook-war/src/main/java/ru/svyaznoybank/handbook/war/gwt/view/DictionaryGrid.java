package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;
import java.util.List;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.model.DictionaryProperties;
import ru.svyaznoybank.handbook.war.gwt.presenter.DictionaryPresenter;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * View to for viewing, editing, adding and removing dictionary entities.
 * @author Roman Zaripov
 */
public class DictionaryGrid extends FastSearchGrid<Dictionary> implements DictionaryPresenter.Display {
	
	@UiField
	protected TextButton addButton;
	@UiField
	protected TextButton editButton;
	@UiField
	protected TextButton deleteButton;
	
	private static DictionaryProperties properties = GWT.create(DictionaryProperties.class);
	
	private static final DateTimeFormat DTF = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	
	interface GUIBinder extends UiBinder<Widget, DictionaryGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);

	public DictionaryGrid() {		
		self = binder.createAndBindUi(this);
	}
	
	@Override
	protected void configureColumnConfigs(List<ColumnConfig<Dictionary, ?>> configs) {
		configs.add(new ColumnConfig<Dictionary, String>(properties.name(), 1, "Название"));
		configs.add(new ColumnConfig<Dictionary, String>(properties.description(), 2, "Описание"));
		configs.add(new ColumnConfig<Dictionary, String>(properties.creator(), 1, "Создан"));
		configs.add(new ColumnConfig<Dictionary, Date>(properties.createDate(), 1, "Дата создания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF);
			}
		});
		configs.add(new ColumnConfig<Dictionary, String>(properties.updater(), 1, "Обновлен"));
		configs.add(new ColumnConfig<Dictionary, Date>(properties.updateDate(), 1, "Дата обновления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF);
			}
		});
	}
	
	@Override
	public Widget asWidget() {
		TextField field = new TextField();
		field.setAllowBlank(false);
		field.setEmptyText("Введите описание...");
		
		return super.asWidget();
	}

	// --- Getters ---
	@Override
	public TextButton getAddButton() {
		return addButton;
	}
	@Override
	public TextButton getEditButton() {
		return editButton;
	}
	@Override
	public TextButton getDeleteButton() {
		return deleteButton;
	}
}