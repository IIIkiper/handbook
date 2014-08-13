package ru.svyaznoybank.handbook.war.gwt.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

import ru.svyaznoybank.handbook.war.gwt.presenter.ClientDetailsEditorPresenter;

public class ClientDetailsEditor implements ClientDetailsEditorPresenter.Display {
	
	@UiField
	protected Window window;
	@UiField
	protected TextButton saveBtn;
	@UiField
	protected TextButton cancelBtn;
	@UiField
	protected FormPanel formPanel;
	@UiField
	protected DateField endDate;
	@UiField
	protected TextField remark;
	
	@UiField(provided = true)
	protected DateTimePropertyEditor dtpe = new DateTimePropertyEditor("dd.MM.yyyy");
	
	private final Widget self;
	
	interface GUIBinder extends UiBinder<Widget, ClientDetailsEditor> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public ClientDetailsEditor() {
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
	public TextField getRemark() {
		return remark;
	}

	@Override
	public DateField getEndDate() {
		return endDate;
	}
}
