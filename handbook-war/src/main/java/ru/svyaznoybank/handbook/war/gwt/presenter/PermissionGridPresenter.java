package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryReader;
import ru.svyaznoybank.handbook.war.gwt.json.reader.IPagingResultABF;
import ru.svyaznoybank.handbook.war.gwt.rest.RestCallback;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.PermissionEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class PermissionGridPresenter extends FastSearchGridPresenter<Dictionary, DictionaryLoadConfig, PermissionGridPresenter.Display> {
		
	private final PermissionEditorPresenter pep;
	
	public PermissionGridPresenter(Long userId, RestUri uri, RestUri comboUri, Display view) {
		super(DictionaryLoadConfig.class, uri, view);
		this.uri = uri.getUri().replace("{id}", userId.toString());	
		pep = new PermissionEditorPresenter(new PermissionEditor(), comboUri, userId, this);
	}

	public interface Display extends FastSearchGridPresenter.Display<Dictionary> {
		TextButton getAddButton();
		TextButton getDeleteButton();
	}
	
	@Override
	protected void bind() {
		super.bind();
		
		view.getGrid().addRowClickHandler(new RowClickHandler() {
			@Override
			public void onRowClick(RowClickEvent event) {
				view.getDeleteButton().enable();	
			}
		});
		
		loader.addLoadHandler(new LoadHandler<DictionaryLoadConfig, PagingLoadResult<Dictionary>>() {	
			@Override
			public void onLoad(LoadEvent<DictionaryLoadConfig, PagingLoadResult<Dictionary>> event) {
				view.getDeleteButton().disable();
				view.getGrid().getSelectionModel().deselectAll();
			}
		});
		
		view.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				pep.init();
			}
		});
		
		view.getDeleteButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final Dictionary dto = view.getGrid().getSelectionModel().getSelectedItem();
				
				final ConfirmMessageBox box = new ConfirmMessageBox("Предупреждение", "Вы действительно хотите удалить [" + dto.getId() + "] ?");				
				box.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
							view.getGrid().mask();
							proxy.delete(dto, new RequestCallback() {
								@Override
								public void onResponseReceived(Request request, Response response) {
									view.getStore().remove(dto);
									view.getGrid().unmask();
								}

								@Override
								public void onError(Request request, Throwable exception) {
									view.getGrid().unmask();
									new AlertMessageBox("Возникла ошибка при удалении [" + dto.getId() + "]", exception.getMessage()).show();									
								}								
							});						
						}
					}
				});
				box.show();
			}
		});
	}
	
	public void add(final Dictionary dic) {
		RestCallback<Dictionary> callback = new RestCallback<Dictionary>() {
			@Override
			public void onResponseReceived(Request request, Dictionary response) {
				view.getGrid().unmask();
				view.getStore().add(response);
			}

			@Override
			public void onError(Throwable exception) {
				view.getGrid().unmask();
				new AlertMessageBox("Возникла ошибка при добавлении [" + dic.getId() + "]", exception.getMessage()).show();									
			}
		};
		
		view.getGrid().mask();
		proxy.post(beanFactory.dto(dic), callback, Dictionary.class);
	}

	@Override
	protected JsonReader<PagingLoadResult<Dictionary>, ? extends PagingLoadResult<Dictionary>> getReader() {
		return new DictionaryReader(beanFactory);
	}

	@Override
	protected IPagingResultABF<Dictionary, ? extends PagingLoadResult<Dictionary>, DictionaryLoadConfig> getBeanFactory() {
		return GWT.create(DictionaryABF.class);
	}
}