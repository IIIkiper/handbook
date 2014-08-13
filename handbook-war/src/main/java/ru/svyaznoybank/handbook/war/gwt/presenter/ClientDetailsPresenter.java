package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.ClientPropertyConfig;
import ru.svyaznoybank.handbook.war.gwt.json.ClientPropertyEditable;
import ru.svyaznoybank.handbook.war.gwt.rest.RestCallback;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.ClientDetailsEditor;

import com.google.gwt.http.client.Request;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;

public abstract class ClientDetailsPresenter<T extends ClientPropertyEditable> 
	extends AbstractGridPresenter<T, ClientPropertyConfig, AbstractGridPresenter.Display<T>> {
		
	private final long clientId;
	private final ClientDetailsEditorPresenter<T> cdep;
	private final Class<T> clazz;

	public ClientDetailsPresenter(long clientId, RestUri uri, AbstractGridPresenter.Display<T> view, Class<T> clazz) {
		super(ClientPropertyConfig.class, uri, view);
		this.clientId = clientId;
		this.clazz = clazz;
		this.cdep = new ClientDetailsEditorPresenter<T>(new ClientDetailsEditor(), this);
	}
	
	@Override
	protected void bind() {
		super.bind();
		
		/*
		view.getGrid().getView().setViewConfig(new GridViewConfig<T>() {
			@Override
			public String getRowStyle(T model, int rowIndex) {
				// see css/handbook.css
				return model.getEndDate() == null || model.getEndDate().after(new Date()) ? "" : "hb-error-request-log-entry";
			}		
			@Override
			public String getColStyle(T model, ValueProvider<? super T, ?> valueProvider, int rowIndex, int colIndex) {
				return "";
			}
		});
		*/
		
		view.getGrid().addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				T clientDetails = view.getStore().get(event.getRowIndex());
				cdep.init(clientDetails);
			}
		});
	}
	
	public void updateDetail(T cpe) {
		RestCallback<T> callback = new RestCallback<T>() {	
			@Override
			public void onResponseReceived(Request request, T response) {
				view.getStore().update(response);
				view.getGrid().unmask();
			}
			
			@Override
			public void onError(Throwable ex) {
				view.getGrid().unmask();
				new AlertMessageBox("Не удалось изменить запись.", ex.getMessage()).show();
			}
		};

		proxy.put(beanFactory.dto(cpe), callback, clazz);
		view.getGrid().mask("Редактирование...");
	}
	
	@Override
	protected ClientPropertyConfig getLoadConfig() {
		ClientPropertyConfig config = super.getLoadConfig();
		config.setClientId(clientId);
		return config;
	}
}