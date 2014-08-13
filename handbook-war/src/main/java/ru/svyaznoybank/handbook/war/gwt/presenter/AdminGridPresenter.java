package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.ImageBundle;
import ru.svyaznoybank.handbook.war.gwt.json.User;
import ru.svyaznoybank.handbook.war.gwt.json.UserLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.reader.UserABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.UserReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestCallback;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.PermissionGrid;
import ru.svyaznoybank.handbook.war.gwt.view.UserDetails;
import ru.svyaznoybank.handbook.war.gwt.view.UserEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
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

public class AdminGridPresenter extends FastSearchGridPresenter<User, UserLoadConfig, AdminGridPresenter.Display> {
	
	private static final ImageBundle BUNDLE = GWT.create(ImageBundle.class);
	
	private final UserEditorPresenter uep;
	
	public AdminGridPresenter(Display view) {
		super(UserLoadConfig.class, RestUri.USER, view);
		uep = new UserEditorPresenter(new UserEditor(), this);
	}

	public interface Display extends FastSearchGridPresenter.Display<User> {
		TabPanel getTabPanel();
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
		
		loader.addLoadHandler(new LoadHandler<UserLoadConfig, PagingLoadResult<User>>() {	
			@Override
			public void onLoad(LoadEvent<UserLoadConfig, PagingLoadResult<User>> event) {
				view.getDeleteButton().disable();
				view.getGrid().getSelectionModel().deselectAll();
			}
		});
		
		view.getAddButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				uep.init(beanFactory.dto().as());
			}
		});
		
		view.getDeleteButton().addSelectHandler(new SelectHandler() {		
			@Override
			public void onSelect(SelectEvent event) {
				final User dto = view.getGrid().getSelectionModel().getSelectedItem();
				
				final ConfirmMessageBox box = new ConfirmMessageBox("Предупреждение", "Вы действительно хотите удалить [" + dto.getNickname() + "] ?");				
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
									new AlertMessageBox("Возникла ошибка при удалении [" + dto.getNickname() + "]", exception.getMessage()).show();									
								}								
							});						
						}
					}
				});
				box.show();
			}
		});
		
		view.getGrid().addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				User user = view.getStore().get(event.getRowIndex());
				
				UserDetails ud = new UserDetails();
				
				PermissionGridPresenter handbookPgp = new PermissionGridPresenter(user.getId(), RestUri.USER_HANDBOOK, RestUri.USER_HANDBOOK_REVERSE, new PermissionGrid());
				handbookPgp.bind();
				ud.setHandbook(handbookPgp.view);
				
				PermissionGridPresenter offerPgp = new PermissionGridPresenter(user.getId(), RestUri.USER_OFFER, RestUri.USER_OFFER_REVERSE, new PermissionGrid());
				offerPgp.bind();
				ud.setOffer(offerPgp.view);
				
				TabItemConfig config = new TabItemConfig(user.getNickname(), true);
				config.setIcon(BUNDLE.vcard());			
				view.getTabPanel().add(ud, config);
								
				view.getTabPanel().setActiveWidget(ud);
			}
		});
	}
	
	public void addUser(final User user) {
		RestCallback<User> callback = new RestCallback<User>() {	
			@Override
			public void onResponseReceived(Request request, User response) {
				view.getStore().add(0, response);
				view.getStore().commitChanges();
				view.getGrid().unmask();
			}
			
			@Override
			public void onError(Throwable ex) {
				view.getGrid().unmask();
				new AlertMessageBox("Не удалось добавить запись [" + user.getNickname() + "].", ex.getMessage()).show();
			}
		};
		
		proxy.post(beanFactory.dto(user), callback, User.class);
		view.getGrid().mask("Добавление...");
	}

	@Override
	protected JsonReader<PagingLoadResult<User>, ? extends PagingLoadResult<User>> getReader() {
		return new UserReader(beanFactory);
	}

	@Override
	protected UserABF getBeanFactory() {
		return GWT.create(UserABF.class);
	}
}