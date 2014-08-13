package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import ru.svyaznoybank.handbook.war.gwt.json.Handbook;
import ru.svyaznoybank.handbook.war.gwt.model.HandbookProperties;

public class HandbookGrid extends AbstractGrid<Handbook> {
	
	private static HandbookProperties properties = GWT.create(HandbookProperties.class);
	
	private static final DateTimeFormat DTF_LONG = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	private static final DateTimeFormat DTF_SHORT = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	@UiTemplate("PagingGrid.ui.xml")
	interface GUIBinder extends UiBinder<Widget, HandbookGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public HandbookGrid() {
		self = binder.createAndBindUi(this);
	}

	@Override
	protected void configureColumnConfigs(List<ColumnConfig<Handbook, ?>> configs) {
		configs.add(new ColumnConfig<Handbook, Date>(properties.createDate(), 100, "Дата создания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		});
		configs.add(new ColumnConfig<Handbook, Date>(properties.beginDate(), 100, "Дата начала") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		configs.add(new ColumnConfig<Handbook, Date>(properties.endDate(), 100, "Дата окончания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT) {
					@Override
					public void render(Context context, Date value, SafeHtmlBuilder sb) {
						if (value != null && value.before(new Date())) {
							sb.appendHtmlConstant("<span style='color: red;'>");
							super.render(context, value, sb);
							sb.appendHtmlConstant("</span>");
						} else {
							super.render(context, value, sb);
						}
					}
				};
			}
		});

		configs.add(new ColumnConfig<Handbook, String>(properties.productId(), 100, "Тип продукта"));
		configs.add(new ColumnConfig<Handbook, String>(properties.handbookTypeId(), 100, "Тип списка"));
		configs.add(new ColumnConfig<Handbook, String>(properties.value(), 100, "Значение"));
		
//		configs.add(new ColumnConfig<Handbook, String>(properties.currencyId(), 100, "Валюта"));		
		
		ColumnConfig<Handbook, String> remark = new ColumnConfig<Handbook, String>(properties.remark(), 100, "Комментарий");
		remark.setHidden(true);
		configs.add(remark);

		ColumnConfig<Handbook, String> creator = new ColumnConfig<Handbook, String>(properties.creator(), 100, "Создан");
		creator.setHidden(true);
		configs.add(creator);
		
		ColumnConfig<Handbook, Date> updateDate = new ColumnConfig<Handbook, Date>(properties.updateDate(), 100, "Дата обновления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		updateDate.setHidden(true);
		configs.add(updateDate);
		
		ColumnConfig<Handbook, String> updater = new ColumnConfig<Handbook, String>(properties.updater(), 100, "Обновлен");
		updater.setHidden(true);
		configs.add(updater);
	}
}