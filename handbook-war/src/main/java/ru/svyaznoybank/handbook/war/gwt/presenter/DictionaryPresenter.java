package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestCallback;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.DictionaryEditor;

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
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class DictionaryPresenter extends FastSearchGridPresenter<Dictionary, DictionaryLoadConfig, DictionaryPresenter.Display> {
		
	final DictionaryEditorPresenter dep;
		
	public interface Display extends FastSearchGridPresenter.Display<Dictionary> {
		TextButton getAddButton();
		TextButton getEditButton();
		TextButton getDeleteButton();
	}
		
	public DictionaryPresenter(Display view, RestUri uri) {
		super(DictionaryLoadConfig.class, uri, view);
		dep = new DictionaryEditorPresenter(new DictionaryEditor(), this);
	}

	@Override
	protected void bind() {
		super.bind();

		view.getGrid().addRowClickHandler(new RowClickHandler() {
			@Override
			public void onRowClick(RowClickEvent event) {
				view.getDeleteButton().enable();
				view.getEditButton().enable();				
			}
		});
		
		loader.addLoadHandler(new LoadHandler<DictionaryLoadConfig, PagingLoadResult<Dictionary>>() {	
			@Override
			public void onLoad(LoadEvent<DictionaryLoadConfig, PagingLoadResult<Dictionary>> event) {
				view.getDeleteButton().disable();
				view.getEditButton().disable();
				view.getGrid().getSelectionModel().deselectAll();
			}
		});
		
		// Delete record logic
		view.getDeleteButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				
				final Dictionary dto = view.getGrid().getSelectionModel().getSelectedItem();
				
				final ConfirmMessageBox box = new ConfirmMessageBox("Предупреждение", "Вы действительно хотите удалить [" + dto.getId() + "] ?");				
				box.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
							view.getGrid().mask("Удаление...");
							
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
		
		// Edit record logic
		view.getEditButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Dictionary dto = view.getGrid().getSelectionModel().getSelectedItem();
				dep.init(dto, false);
			}
		});
		
		view.getGrid().addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				view.getEditButton().fireEvent(new SelectEvent());			
			}
		});
		
		view.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Dictionary dto = beanFactory.dto().as();
				dep.init(dto, true);
				
			}
		});
	}
	
	public void editRecord(final Dictionary dic) {
		view.getGrid().mask("Редактирование...");
		RestCallback<Dictionary> callback = new RestCallback<Dictionary>() {		
			@Override
			public void onResponseReceived(Request request, Dictionary response) {
				view.getStore().update(response);
				view.getGrid().unmask();
//				view.getStore().commitChanges();
			}
			
			@Override
			public void onError(Throwable ex) {
				view.getGrid().unmask();
				new AlertMessageBox("Не удалось изменить запись [" + dic.getId() + "].", ex.getMessage()).show();
			}
		};
		
		proxy.put(beanFactory.dto(dic), callback, Dictionary.class);
	}
	
	public void addRecord(final Dictionary dic) {
		view.getGrid().mask("Добавление...");
		RestCallback<Dictionary> callback = new RestCallback<Dictionary>() {		
			@Override
			public void onResponseReceived(Request request, Dictionary response) {
				view.getStore().add(response);
				view.getGrid().unmask();
//				view.getStore().commitChanges();
			}
			
			@Override
			public void onError(Throwable ex) {
				view.getGrid().unmask();
				new AlertMessageBox("Не удалось добавить запись [" + dic.getId() + "].", ex.getMessage()).show();
			}
		};
	
		proxy.post(beanFactory.dto(dic), callback, Dictionary.class);			
	}
	
	@Override
	protected JsonReader<PagingLoadResult<Dictionary>, ? extends PagingLoadResult<Dictionary>> getReader() {
		return new DictionaryReader(beanFactory);
	};
	
	@Override
	protected DictionaryABF getBeanFactory() {
		return GWT.create(DictionaryABF.class);
	}
}