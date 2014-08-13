package ru.svyaznoybank.handbook.war.gwt.view;

import ru.svyaznoybank.handbook.war.gwt.presenter.UserEditorPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class UserEditor implements UserEditorPresenter.Display {
	
	@UiField
	protected Window window;
	@UiField
	protected TextButton saveBtn;
	@UiField
	protected TextButton cancelBtn;
	@UiField
	protected FormPanel formPanel;
	@UiField
	protected TextField name;
	
	private final Widget self;
	
	interface GUIBinder extends UiBinder<Widget, UserEditor> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	
	public UserEditor() {
		self = binder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget() {
		return self;
	}
	
	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public TextButton getSaveBtn() {
		return saveBtn;
	}

	@Override
	public TextButton getCancelBtn() {
		return cancelBtn;
	}

	@Override
	public FormPanel getFormPanel() {
		return formPanel;
	}

	@Override
	public TextField getName() {
		return name;
	}
}