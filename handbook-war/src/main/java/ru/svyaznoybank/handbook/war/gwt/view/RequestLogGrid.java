package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;
import java.util.List;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLog;
import ru.svyaznoybank.handbook.war.gwt.model.RequestLogProperties;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.BeforeExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandItemEvent.BeforeExpandItemHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;
import com.sencha.gxt.widget.core.client.grid.RowExpander;

public class RequestLogGrid extends FastSearchGrid<RequestLog> {
	
	private static RequestLogProperties properties = GWT.create(RequestLogProperties.class);
	
	private static final DateTimeFormat DTF_LONG = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	private static final DateTimeFormat DTF_SHORT = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	interface GUIBinder extends UiBinder<Widget, RequestLogGrid> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	private RowExpander<RequestLog> expander;
	private ColumnConfig<RequestLog, String> name;
	
	public RequestLogGrid() {		
		self = binder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget() {
		grid.setLoadMask(true);
	
		grid.getView().setViewConfig(new GridViewConfig<RequestLog>() {
			@Override
			public String getRowStyle(RequestLog model, int rowIndex) {
				// Lines, where errorCode != null, will be highlighted with red.
				return model.getErrorCode() != null ? "hb-error-request-log-entry" : ""; // see style.css
			}		
			@Override
			public String getColStyle(RequestLog model, ValueProvider<? super RequestLog, ?> valueProvider, int rowIndex, int colIndex) {
				return "";
			}
		});

		grid.getView().setColumnLines(true);
		
		grid.getView().setAutoExpandColumn(name);		
		expander.initPlugin(grid);
				
		return self;
	}

	@Override
	protected void configureColumnConfigs(List<ColumnConfig<RequestLog, ?>> configs) {
		expander = new RowExpander<RequestLog>(new AbstractCell<RequestLog>() {
			@Override
			public void render(final Context context, final RequestLog value, final SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<textarea rows='12' spellcheck='false' class='hb-xml-response-log' readonly>" 
					+ value.getResponse() 
					+ "</textarea>");
			}
		});
		
		expander.addBeforeExpandHandler(new BeforeExpandItemHandler<RequestLog>() {
			@Override
			public void onBeforeExpand(BeforeExpandItemEvent<RequestLog> event) {
				final RequestLog value = event.getItem();

				if (value.getResponse() != null) {
					return;
				}
				
				event.setCancelled(true);
				
				// Request XML response only if user expands the row.
				RequestBuilder rb = new RequestBuilder(
					RequestBuilder.GET, 
					RestUri.REQUEST_LOG_RESPONSE.getUri().replace("{id}", value.getId() + "")
				);
				rb.setHeader("Accept", "application/xml");
				try {
					rb.sendRequest(null, new RequestCallback() {
						@Override
						public void onResponseReceived(Request request, Response response) {
							value.setResponse(response.getText());
							expander.expandRow(store.indexOf(value));
						}
						@Override
						public void onError(Request request, Throwable e) {
							new AlertMessageBox("Возникла ошибка при загрузке ответа для записи с ID [" + value.getId() + "]", e.getMessage()).show();
						}
					});
				} catch (RequestException e) {
					new AlertMessageBox("Возникла ошибка при загрузке ответа для записи с ID [" + value.getId() + "]", e.getMessage()).show();
				}				
			}
		});
		
		configs.add(expander);
		
		name = new ColumnConfig<RequestLog, String>(properties.firstName(), 1, "Имя");
		configs.add(name);
		
		configs.add(new ColumnConfig<RequestLog, String>(properties.lastName(), 1, "Фамилия"));
		configs.add(new ColumnConfig<RequestLog, String>(properties.patronymic(), 1, "Отчество"));
		configs.add(new ColumnConfig<RequestLog, Long>(properties.clientId(), 1, "ID клиента"));
		configs.add(new ColumnConfig<RequestLog, Date>(properties.birthday(), 1, "Дата рождения") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		configs.add(new ColumnConfig<RequestLog, String>(properties.docSeries(), 1, "Серия документа"));
		configs.add(new ColumnConfig<RequestLog, String>(properties.docNumber(), 1, "Номер документа"));
		configs.add(new ColumnConfig<RequestLog, Date>(properties.docIssueDate(), 1, "Дата выдачи") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_SHORT);
			}
		});
		
		ColumnConfig<RequestLog, Integer> errorCode = new ColumnConfig<RequestLog, Integer>(properties.errorCode(), 1, "Код ошибки");
		errorCode.setHidden(true);
		configs.add(errorCode);
		
		ColumnConfig<RequestLog, String> ean = new ColumnConfig<RequestLog, String>(properties.eanId(), 1, "EAN");
		ean.setHidden(true);
		configs.add(ean);
		
		ColumnConfig<RequestLog, String> docType = new ColumnConfig<RequestLog, String>(properties.docId(), 1, "Тип документа");
		docType.setHidden(true);
		configs.add(docType);
		
		ColumnConfig<RequestLog, String> comment = new ColumnConfig<RequestLog, String>(properties.remark(), 1, "Комментарий");
		comment.setHidden(true);
		configs.add(comment);
		
		ColumnConfig<RequestLog, Date> createDate = new ColumnConfig<RequestLog, Date>(properties.createDate(), 1, "Дата создания") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		createDate.setHidden(true);
		configs.add(createDate);
		
		ColumnConfig<RequestLog, String> creator = new ColumnConfig<RequestLog, String>(properties.creator(), 1, "Создан");
		creator.setHidden(true);
		configs.add(creator);
		
		ColumnConfig<RequestLog, Date> updateDate = new ColumnConfig<RequestLog, Date>(properties.updateDate(), 1, "Дата обновления") {
			@Override
			public Cell<Date> getCell() {
				return new DateCell(DTF_LONG);
			}
		};
		updateDate.setHidden(true);
		configs.add(updateDate);
		
		ColumnConfig<RequestLog, String> updater = new ColumnConfig<RequestLog, String>(properties.updater(), 1, "Обновлен");
		updater.setHidden(true);
		configs.add(updater);	
	}
}