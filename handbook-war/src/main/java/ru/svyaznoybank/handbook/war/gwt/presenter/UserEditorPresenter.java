package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.User;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class UserEditorPresenter extends Presenter<UserEditorPresenter.Display> {
	
	private User user;
	private final AdminGridPresenter agp;
	
	public interface Display extends IsWidget {
		Window getWindow();
		TextButton getSaveBtn();
		TextButton getCancelBtn();
		FormPanel getFormPanel();
		TextField getName();
	}
	
	public UserEditorPresenter(Display view, AdminGridPresenter agp) {
		super(view);
		this.agp = agp;
		bind();
	}
	
	public void init(HasWidgets container) {
		throw new RuntimeException("not supported");
	}
	
	public void init(User user) {
		this.user = user;
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
				
				user.setNickname(view.getName().getCurrentValue());
				agp.addUser(user);	
				close();
			}
		});	
	}
	
	public void close() {
		view.getWindow().hide();
	}
}