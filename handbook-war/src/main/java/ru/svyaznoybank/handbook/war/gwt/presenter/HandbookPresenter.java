package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.Handbook;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;
import ru.svyaznoybank.handbook.war.gwt.view.DictionaryGrid;
import ru.svyaznoybank.handbook.war.gwt.view.AdminGrid;
import ru.svyaznoybank.handbook.war.gwt.view.S2View;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class HandbookPresenter extends Presenter<HandbookPresenter.Display> {
	
	private DictionaryPresenter offerTypePresenter;
	private DictionaryPresenter productPresenter;
	private DictionaryPresenter uwtTypePresenter;
	private DictionaryPresenter currencyPresenter;
	private DictionaryPresenter documentPresenter;
	private DictionaryPresenter handbookTypePresenter;
	
	private ClientPanelPresenter clientPresenter;
	
	private RequestLogPanelPresenter requestLogPresenter;
	
	private AdminGridPresenter adminGridPresenter;
	
	public interface Display extends IsWidget {
		// Dictionary menu
		MenuItem getHandbookTypeItem();
		MenuItem getOfferTypeItem();
		MenuItem getProductItem();
		MenuItem getUwtTypeItem();
		MenuItem getCurrencyItem();
		MenuItem getDocumentItem();
		
		MenuItem getClientItem();
		MenuItem getRequestLogItem();
		
		MenuBarItem getSecurityBarItem();
		MenuItem getSecurityItem();
		
		SimpleContainer getContent();
		void setHeader(String header);
	}
	
	public HandbookPresenter(Display view) {
		super(view);
	}
		
	protected void bind() {
		if (!Handbook.isAdmin()) {
			view.getSecurityBarItem().hide();
		}
		
		view.getSecurityItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getSecurityItem().getText());
//				if (isAdmin()) {
					if (adminGridPresenter == null) {
						adminGridPresenter = new AdminGridPresenter(new AdminGrid());
					}
					adminGridPresenter.init(view.getContent());
//				} else {
//					
//				}
			}
		});
		
		view.getHandbookTypeItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getHandbookTypeItem().getText());
				if (handbookTypePresenter == null) {
					handbookTypePresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_HANDBOOK);
				}
				handbookTypePresenter.init(view.getContent());
			}
		});
		
		view.getOfferTypeItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getOfferTypeItem().getText());
				if (offerTypePresenter == null) {
					offerTypePresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_OFFER);
				}
				offerTypePresenter.init(view.getContent());
			}
		});
		
		view.getCurrencyItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getCurrencyItem().getText());
				if (currencyPresenter == null) {
					currencyPresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_CURRENCY);
				}
				currencyPresenter.init(view.getContent());
			}
		});
		
		view.getDocumentItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getDocumentItem().getText());
				if (documentPresenter == null) {
					documentPresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_DOCUMENT);
				}
				documentPresenter.init(view.getContent());
			}
		});
		
		view.getProductItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getProductItem().getText());
				if (productPresenter == null) {
					productPresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_PRODUCT);
				}
				productPresenter.init(view.getContent());
			}
		});
		
		view.getUwtTypeItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getUwtTypeItem().getText());
				if ( uwtTypePresenter == null) {
					 uwtTypePresenter = new DictionaryPresenter(new DictionaryGrid(), RestUri.DIC_UWT);
				}
				 uwtTypePresenter.init(view.getContent());
			}
		});
		
		view.getClientItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				showStartPage();
			}
		});
		
		view.getRequestLogItem().addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				view.setHeader(view.getRequestLogItem().getText());
				if (requestLogPresenter == null) {
					requestLogPresenter = new RequestLogPanelPresenter(new S2View());
				}
				requestLogPresenter.init(view.getContent());
			}
		});
		
		showStartPage();
	}
	
	public void showStartPage() {
		view.setHeader(view.getClientItem().getText());
		if (clientPresenter == null) {
			clientPresenter = new ClientPanelPresenter(new S2View());
		}
		clientPresenter.init(view.getContent());	
	}
}