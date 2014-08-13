package ru.svyaznoybank.handbook.war.gwt.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public abstract class S2Presenter<T extends S2Presenter.Display> extends Presenter<T> {
	
	public S2Presenter(T view) {
		super(view);
	}

	public interface Display extends IsWidget {
		FormPanel getFormPanel();
		ContentPanel getCenterPanel();
		ContentPanel getWestPanel();
		BorderLayoutContainer getCnt();
		
		TextButton getSearchButton();
		TextButton getClearButton();
	}
	
	@Override
	protected void bind() {
		view.getClearButton().addSelectHandler(new SelectHandler() {		
			@Override
			public void onSelect(SelectEvent event) {
				view.getFormPanel().reset();				
			}
		});	
	}
}