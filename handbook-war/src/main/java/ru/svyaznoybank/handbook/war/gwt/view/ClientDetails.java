package ru.svyaznoybank.handbook.war.gwt.view;

import ru.svyaznoybank.handbook.war.gwt.ImageBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;

/**
 * @author Roman Zaripov
 */
public class ClientDetails implements IsWidget {
	
	private static final ImageBundle BUNDLE = GWT.create(ImageBundle.class);
	
	@UiField
	protected ContentPanel handbookCnt;
	
	@UiField
	protected ContentPanel offerCnt;
	
	@UiField
	protected AccordionLayoutContainer alc;
	
	interface GUIBinder extends UiBinder<Widget, ClientDetails> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	private Widget self;
	
	public ClientDetails() {
		self = binder.createAndBindUi(this);
		handbookCnt.getHeader().setIcon(BUNDLE.list());
		offerCnt.getHeader().setIcon(BUNDLE.offer());
		alc.setActiveWidget(handbookCnt);
	}
	
	public void setHandbook(IsWidget handbook) {
		handbookCnt.add(handbook);
	}
	
	public void setOffer(IsWidget offer) {
		offerCnt.add(offer);
	}
	
	public void setHandbookSize(int size) {
		handbookCnt.setHeadingText("Списки [" + size + "]");
	}
	
	public void setOfferSize(int size) {
		offerCnt.setHeadingText("Предложения [" + size + "]");
	}

	@Override
	public Widget asWidget() {
		return self;
	}
}