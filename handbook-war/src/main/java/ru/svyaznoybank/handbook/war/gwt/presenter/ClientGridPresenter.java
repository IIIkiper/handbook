package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.ImageBundle;
import ru.svyaznoybank.handbook.war.gwt.json.Client;
import ru.svyaznoybank.handbook.war.gwt.json.ClientLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.ClientPropertyConfig;
import ru.svyaznoybank.handbook.war.gwt.json.Handbook;
import ru.svyaznoybank.handbook.war.gwt.json.Offer;
import ru.svyaznoybank.handbook.war.gwt.json.reader.ClientABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.ClientReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.ClientDetails;
import ru.svyaznoybank.handbook.war.gwt.view.HandbookGrid;
import ru.svyaznoybank.handbook.war.gwt.view.OfferGrid;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;

public class ClientGridPresenter extends FastSearchGridPresenter<Client, ClientLoadConfig, ClientGridPresenter.Display> {
	
	private static final ImageBundle BUNDLE = GWT.create(ImageBundle.class);
	
	public interface Display extends FastSearchGridPresenter.Display<Client> {
		TabPanel getTabPanel();
	}
	
	public ClientGridPresenter(Display view) {
		super(ClientLoadConfig.class, RestUri.CLIENT, view);
	}
	
	@Override
	protected void bind() {
		super.bind();
		
		view.getGrid().addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				Client client = view.getStore().get(event.getRowIndex());
				
				final ClientDetails cd = new ClientDetails();
				
				HandbookGridPresenter hgp = new HandbookGridPresenter(client.getId(), new HandbookGrid()) {
					protected void bind() {
						super.bind();
						loader.addLoadHandler(new LoadHandler<ClientPropertyConfig, PagingLoadResult<Handbook>>() {
							@Override
							public void onLoad(LoadEvent<ClientPropertyConfig, PagingLoadResult<Handbook>> event) {
								cd.setHandbookSize(event.getLoadResult().getTotalLength());
							}
						});
					};
				};
				hgp.bind();
				cd.setHandbook(hgp.view);
				
				OfferGridPresenter ogp = new OfferGridPresenter(client.getId(), new OfferGrid())  {
					protected void bind() {
						super.bind();
						loader.addLoadHandler(new LoadHandler<ClientPropertyConfig, PagingLoadResult<Offer>>() {
							@Override
							public void onLoad(LoadEvent<ClientPropertyConfig, PagingLoadResult<Offer>> event) {
								cd.setOfferSize(event.getLoadResult().getTotalLength());
							}
						});
					};
				};
				ogp.bind();
				cd.setOffer(ogp.view);
								
				TabItemConfig config = new TabItemConfig(client.getFirstName() + " " + client.getLastName(), true);
				config.setIcon(BUNDLE.vcard());
				view.getTabPanel().add(cd, config);
								
				view.getTabPanel().setActiveWidget(cd);	
			}
		});
	}
	
	@Override
	protected JsonReader<PagingLoadResult<Client>, ? extends PagingLoadResult<Client>> getReader() {
		return new ClientReader(beanFactory);
	}

	@Override
	protected ClientABF getBeanFactory() {
		return GWT.create(ClientABF.class);
	}
}