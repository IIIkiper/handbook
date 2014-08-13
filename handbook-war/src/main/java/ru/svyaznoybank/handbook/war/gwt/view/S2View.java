package ru.svyaznoybank.handbook.war.gwt.view;

import ru.svyaznoybank.handbook.war.gwt.presenter.S2Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class S2View implements S2Presenter.Display {
	
	@UiField
	protected ContentPanel centerCnt;
	@UiField
	protected FormPanel formPanel;
	@UiField
	protected ContentPanel westCnt;
	@UiField
	protected TextButton searchButton;
	@UiField
	protected TextButton clearButton;
	@UiField
	protected BorderLayoutContainer cnt;
	
	interface GUIBinder extends UiBinder<Widget, S2View> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	protected Widget self = null;
	
	public S2View() {
		self = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return self;
	}
	
	@Override
	public FormPanel getFormPanel() {
		return formPanel;
	}
	
	@Override
	public ContentPanel getCenterPanel() {
		return centerCnt;
	}

	@Override
	public TextButton getSearchButton() {
		return searchButton;
	}

	@Override
	public TextButton getClearButton() {
		return clearButton;
	}

	@Override
	public ContentPanel getWestPanel() {
		return westCnt;
	}

	@Override
	public BorderLayoutContainer getCnt() {
		return cnt;
	}
}