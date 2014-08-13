package ru.svyaznoybank.handbook.war.gwt.view;

import ru.svyaznoybank.handbook.war.gwt.ImageBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;

public class UserDetails implements IsWidget {
	
	private static final ImageBundle BUNDLE = GWT.create(ImageBundle.class);
	
	@UiField
	protected ContentPanel handbookCnt;
	
	@UiField
	protected ContentPanel offerCnt;
	
	@UiField
	protected AccordionLayoutContainer alc;
	
	@UiTemplate("ClientDetails.ui.xml")
	interface GUIBinder extends UiBinder<Widget, UserDetails> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	private Widget self;
	
	public UserDetails() {
		self = binder.createAndBindUi(this);
		handbookCnt.getHeader().setIcon(BUNDLE.list());
		offerCnt.getHeader().setIcon(BUNDLE.offer());
		alc.setActiveWidget(handbookCnt);
	}
	
	@Override
	public Widget asWidget() {
		return self;
	}
	
	public void setHandbook(IsWidget handbook) {
		handbookCnt.add(handbook);
	}
	
	public void setOffer(IsWidget offer) {
		offerCnt.add(offer);
	}
}