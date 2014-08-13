package ru.svyaznoybank.handbook.war.gwt.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

import ru.svyaznoybank.handbook.war.gwt.presenter.DictionaryEditorPresenter;

public class DictionaryEditor implements DictionaryEditorPresenter.Display {
	
	@UiField
	protected Window window;
	@UiField
	protected TextButton saveBtn;
	@UiField
	protected TextButton cancelBtn;
	@UiField
	protected FormPanel formPanel;
	@UiField
	protected TextField id;
	@UiField
	protected TextField description;	
	
	private final Widget self;
	
	interface GUIBinder extends UiBinder<Widget, DictionaryEditor> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	
	public DictionaryEditor() {
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
	public TextField getId() {
		return id;
	}

	@Override
	public TextField getDescription() {
		return description;
	}
}