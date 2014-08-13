package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;
import java.util.List;

import ru.svyaznoybank.handbook.war.gwt.json.Offer;
import ru.svyaznoybank.handbook.war.gwt.model.OfferProperties;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class OfferGrid extends AbstractGrid<Offer> {
	
	private static OfferProperties properties = GWT.create(OfferProperties.class);
	
	private static final DateTimeFormat DTF_LONG = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	private static final DateTimeFormat DTF_SHORT = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	@UiTemplate("PagingGrid.ui.xml")
	interface GUIBinder extends UiBinder<Widget, OfferGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public OfferGrid() {
		self = binder.createAndBindUi(this);
	}

	@Override
	protected void configureColumnConfigs(List<ColumnConfig<Offer, ?>> configs) {
		configs.add(new ColumnConfig<Offer, Date>(properties.createDate(), 100, "Дата создания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		});	
		configs.add(new ColumnConfig<Offer, Date>(properties.beginDate(), 100, "Дата начала") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		
		configs.add(new ColumnConfig<Offer, Date>(properties.endDate(), 100, "Дата окончания") {
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

		configs.add(new ColumnConfig<Offer, String>(properties.productId(), 100, "Тип продукта"));
		configs.add(new ColumnConfig<Offer, Double>(properties.creditLine(), 100, "Кредитная линия"));
		configs.add(new ColumnConfig<Offer, String>(properties.currencyId(), 100, "Валюта"));
		configs.add(new ColumnConfig<Offer, String>(properties.uwTypeId(), 100, "Тип андеррайтинга"));
		configs.add(new ColumnConfig<Offer, String>(properties.offerTypeId(), 100, "Тип предложения"));
//		configs.add(new ColumnConfig<Offer, Double>(properties.minCreditLine(), 100, "min Credit line"));
//		configs.add(new ColumnConfig<Offer, Double>(properties.maxCreditLine(), 100, "max Credit line"));
//		configs.add(new ColumnConfig<Offer, Double>(properties.minFirstPayment(), 100, "min 1st payment"));
//		configs.add(new ColumnConfig<Offer, Double>(properties.maxFirstPayment(), 100, "max first payment"));
		configs.add(new ColumnConfig<Offer, Double>(properties.minYield(), 100, "Минимальная доходность"));
		configs.add(new ColumnConfig<Offer, Double>(properties.maxYield(), 100, "Максимальная доходность"));
//		configs.add(new ColumnConfig<Offer, Integer>(properties.minRisk(), 100, "min risk"));
//		configs.add(new ColumnConfig<Offer, Integer>(properties.maxRisk(), 100, "max risk"));
//		configs.add(new ColumnConfig<Offer, Integer>(properties.averageRisk(), 100, "average risk"));
//		configs.add(new ColumnConfig<Offer, String>(properties.offerCurrencyId(), 100, "Валюта предложения"));
//		configs.add(new ColumnConfig<Offer, String>(properties.workflowCode(), 100, "Workflow code"));
		
		ColumnConfig<Offer, String> remark = new ColumnConfig<Offer, String>(properties.remark(), 100, "Комментарий");
		remark.setHidden(true);
		configs.add(remark);
	
		ColumnConfig<Offer, String> creator = new ColumnConfig<Offer, String>(properties.creator(), 100, "Создан");
		creator.setHidden(true);
		configs.add(creator);
		
		ColumnConfig<Offer, Date> updateDate = new ColumnConfig<Offer, Date>(properties.updateDate(), 100, "Дата обновления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		updateDate.setHidden(true);
		configs.add(updateDate);
		
		ColumnConfig<Offer, String> updater = new ColumnConfig<Offer, String>(properties.updater(), 100, "Обновлен");
		updater.setHidden(true);
		configs.add(updater);
	}
}