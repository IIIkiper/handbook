package ru.svyaznoybank.handbook.war.gwt.view;

import ru.svyaznoybank.handbook.war.gwt.presenter.HandbookPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class HandbookView implements HandbookPresenter.Display {
	
	interface GUIBinder extends UiBinder<Widget, HandbookView> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	@UiField
	protected SimpleContainer cnt;
	
	@UiField
	protected MenuBar menuBar;
	@UiField
	protected MenuBarItem securityBarItem;
	@UiField
	protected MenuItem securityItem;
	
	@UiField
	protected MenuItem clientItem;
	@UiField
	protected MenuItem requestLogItem;
	
	@UiField
	protected MenuItem listType;
	@UiField
	protected MenuItem offerType;
	@UiField
	protected MenuItem underwritingType;
	@UiField
	protected MenuItem documentType;
	@UiField
	protected MenuItem productType;
	@UiField
	protected MenuItem currencyType;
	
	@UiField
	protected ContentPanel cp;
		
	private final Widget self;
	
	public HandbookView() {
		self = binder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget() {
		return self;
	}
	
	@Override
	public SimpleContainer getContent() {
		return cnt;
	}
	
	@Override
	public void setHeader(String header) {
		cp.setHeadingText("Handbook Black - " + header);
	}

	@Override
	public MenuItem getHandbookTypeItem() {
		return listType;
	}

	@Override
	public MenuItem getOfferTypeItem() {
		return offerType;
	}

	@Override
	public MenuItem getProductItem() {
		return productType;
	}

	@Override
	public MenuItem getUwtTypeItem() {
		return underwritingType;
	}

	@Override
	public MenuItem getCurrencyItem() {
		return currencyType;
	}

	@Override
	public MenuItem getDocumentItem() {
		return documentType;
	}

	@Override
	public MenuItem getClientItem() {
		return clientItem;
	}

	@Override
	public MenuItem getRequestLogItem() {
		return requestLogItem;
	}

	@Override
	public MenuBarItem getSecurityBarItem() {
		return securityBarItem;
	}

	@Override
	public MenuItem getSecurityItem() {
		return securityItem;
	}
}