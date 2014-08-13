package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Arrays;

import ru.svyaznoybank.handbook.war.gwt.json.ListItem;
import ru.svyaznoybank.handbook.war.gwt.model.ListItemProperties;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;

/**
 * Extention of standard paging bar. Allows user to change page size dynamically.
 * @author Roman Zaripov
 */
public class PagingBar extends PagingToolBar {
	
	public PagingBar() {
		this(25);
	}

	@UiConstructor
	public PagingBar(int pageSize) {
		super(pageSize);
		
		ListItemProperties props = GWT.create(ListItemProperties.class);
		ListStore<ListItem> store = new ListStore<ListItem>(props.id());
		store.addAll(Arrays.asList(new ListItem[] {
			new ListItem(10), // TODO move ListItem here?
			new ListItem(15),
			new ListItem(20),
			new ListItem(25),
			new ListItem(30),
			new ListItem(35),
			new ListItem(40),
			new ListItem(45),
			new ListItem(50)
		}));
		
		ComboBox<ListItem> combo = new ComboBox<ListItem>(store, props.value());
		combo.setValue(new ListItem(pageSize));
		combo.setWidth(50);
		combo.setTypeAhead(true);
		combo.setTriggerAction(TriggerAction.ALL);
				
		insert(new LabelToolItem("Записей на странице "), 10); // TODO parametrize
		insert(combo, 11);
		insert(new SeparatorToolItem(), 12);
		
		combo.addSelectionHandler(new SelectionHandler<ListItem>() {		
			@Override
			public void onSelection(SelectionEvent<ListItem> event) {
				int value = event.getSelectedItem().getId();
				PagingBar.this.pageSize = value;
				loader.setLimit(value);	
				loader.load();
			}
		});
	}
}