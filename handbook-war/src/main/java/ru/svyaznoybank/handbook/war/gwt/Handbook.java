package ru.svyaznoybank.handbook.war.gwt;

import ru.svyaznoybank.handbook.war.gwt.presenter.HandbookPresenter;
import ru.svyaznoybank.handbook.war.gwt.view.HandbookView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Handbook implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new HandbookPresenter(new HandbookView()).init(RootPanel.get());
	}
	
	// See handbook.jsp
	public static native boolean isAdmin() /*-{
		return $wnd.isAdmin;
	}-*/;
	
	// See handbook.jsp
	public static native String getUsername() /*-{
		return $wnd.username;
	}-*/;
}