package ru.svyaznoybank.handbook.war.gwt.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FormPanel;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.model.DictionaryProperties;
import ru.svyaznoybank.handbook.war.gwt.presenter.PermissionEditorPresenter;

public class PermissionEditor implements PermissionEditorPresenter.Display {
		
	private DictionaryProperties props = GWT.create(DictionaryProperties.class);
	private final ListStore<Dictionary> store = new ListStore<Dictionary>(props.id());
	
	interface ExampleTemplate extends XTemplates {
	    @XTemplate("{dic.id}<br><span style='font-size:10px;'>{dic.description}</span>")
	    SafeHtml render(Dictionary dic);
	 }
	
	private final ExampleTemplate template = GWT.create(ExampleTemplate.class);
	
	
	ListView<Dictionary, Dictionary> view = new ListView<Dictionary, Dictionary>(store, new IdentityValueProvider<Dictionary>());
	
	
	@UiField(provided = true)
	protected ComboBox<Dictionary> combo;/* = new ComboBox<Dictionary>(store, props.nameLabel());*/
	
	@UiField
	protected Window window;
	@UiField
	protected TextButton saveBtn;
	@UiField
	protected TextButton cancelBtn;
	@UiField
	protected FormPanel formPanel;
	
	private final Widget self;
	
	interface GUIBinder extends UiBinder<Widget, PermissionEditor> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public PermissionEditor() {
		view.setCell(new AbstractCell<Dictionary>() {
			@Override
			public void render(Context context, Dictionary value, SafeHtmlBuilder sb) {
				sb.append(template.render(value));
			}		
		});
		
		ComboBoxCell<Dictionary> cell = new ComboBoxCell<Dictionary>(store, props.nameLabel(), view);
		
		combo = new ComboBox<Dictionary>(cell);
		combo.setTriggerAction(TriggerAction.ALL);
		
		self = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return self;
	}
	@Override
	public ListStore<Dictionary> getStore() {
		return store;
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
	public ComboBox<Dictionary> getCombo() {
		return combo;
	}
}