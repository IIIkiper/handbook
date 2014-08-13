package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.ClientPropertyEditable;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ClientDetailsEditorPresenter<E extends ClientPropertyEditable> extends Presenter<ClientDetailsEditorPresenter.Display> {
	
	private ClientDetailsPresenter<E> cdp;
	private E currentDetail;
	
	public interface Display extends IsWidget {
		Window getWindow();
		TextButton getSaveBtn();
		TextButton getCancelBtn();
		FormPanel getFormPanel();
		TextField getRemark();
		DateField getEndDate();
	}
	
	public ClientDetailsEditorPresenter(Display view, ClientDetailsPresenter<E> cdp) {
		super(view);
		this.cdp = cdp;
		bind();
	}
	
	public void init(HasWidgets container) {
		throw new RuntimeException("not supported");
	}
	
	public void init(E cd) {
		currentDetail = cd;
		view.getWindow().show();
	}

	@Override
	protected void bind() {
		view.getCancelBtn().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				close();
			}
		});
		
		view.getWindow().addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				view.getFormPanel().reset();
			}
		});
		
		view.getSaveBtn().addSelectHandler(new SelectHandler() {	
			@Override
			public void onSelect(SelectEvent event) {
				if (!view.getFormPanel().isValid()) {
					return;
				}
				
				currentDetail.setEndDate(view.getEndDate().getCurrentValue());
				currentDetail.setRemark(view.getRemark().getCurrentValue());
				cdp.updateDetail(currentDetail);
				close();
			}
		});
	}
	
	public void close() {
		view.getWindow().hide();
	}
}
