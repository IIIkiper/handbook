package ru.svyaznoybank.handbook.war.gwt.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public abstract class Presenter<T extends IsWidget> {
	
	protected final T view;
	private boolean binded;
	
	public Presenter(T view) {
		this.view = view;
	}
	
	public void init(HasWidgets container) {		
		if (container instanceof SimpleContainer) {
			((SimpleContainer) container).setWidget(view.asWidget());
		} else {
			container.add(view.asWidget());
		}
		
		if (!binded) {
			bind();
			binded = true;
		}
		
		if (container instanceof ResizeContainer) {
			((ResizeContainer) container).forceLayout();
		}		
	}
	
	protected abstract void bind();
}