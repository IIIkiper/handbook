package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.DictionaryReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class PermissionEditorPresenter extends Presenter<PermissionEditorPresenter.Display> {
	
	private final ListLoader<DictionaryLoadConfig, PagingLoadResult<Dictionary>> loader;
//	private final RestProxy<DictionaryLoadConfig, Dictionary> proxy;
	private final HttpProxy<DictionaryLoadConfig> proxy;
	private final DictionaryABF abf;
	
	private boolean changed;
	
	private final PermissionGridPresenter pgp;
	
	public PermissionEditorPresenter(Display view, RestUri uri, Long userId, PermissionGridPresenter pgp) {
		super(view);
		this.pgp = pgp;
		
		abf = GWT.create(DictionaryABF.class);
		
		String url = uri.getUri().replace("{id}", userId.toString());	
		proxy = new HttpProxy<DictionaryLoadConfig>(new RequestBuilder(RequestBuilder.GET, url));
//		proxy = new RestProxy<DictionaryLoadConfig, Dictionary>(url);
//		proxy.setWriter(new UrlEncodingWriter<DictionaryLoadConfig>(abf, DictionaryLoadConfig.class));
						
		DictionaryReader reader = new DictionaryReader(abf);		
		loader = new ListLoader<DictionaryLoadConfig, PagingLoadResult<Dictionary>>(proxy, reader);
		loader.addLoadHandler(new LoadResultListStoreBinding<DictionaryLoadConfig, Dictionary, PagingLoadResult<Dictionary>>(view.getStore()));
//		loader.useLoadConfig(null);
		
		bind();
	}

	public interface Display extends IsWidget {
		Window getWindow();
		TextButton getSaveBtn();
		TextButton getCancelBtn();
		FormPanel getFormPanel();
		ListStore<Dictionary> getStore();
		ComboBox<Dictionary> getCombo();
	}
	
	public void init(HasWidgets container) {
		throw new RuntimeException("not supported");
	}
	
	public void init() {
		view.getWindow().show();
	}

	@Override
	protected void bind() {
//		view.getCombo().setPageSize(25);

		view.getCombo().setLoader(loader);
		
		view.getWindow().addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				view.getFormPanel().reset();
			}
		});
		
		view.getWindow().addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				if (changed) {
					loader.load();
					changed = false;
				}
			}
		});
		
		view.getCancelBtn().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				close();
			}
		});
				
		view.getSaveBtn().addSelectHandler(new SelectHandler() {	
			@Override
			public void onSelect(SelectEvent event) {
				if (!view.getFormPanel().isValid()) {
					return;
				}
				
				pgp.add(view.getCombo().getCurrentValue());

				changed = true;
				
				close();
			}
		});
	}
	
	public void close() {
		view.getWindow().hide();
	}
}