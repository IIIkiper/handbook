package ru.svyaznoybank.handbook.war.gwt.model;

import ru.svyaznoybank.handbook.war.gwt.json.ListItem;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ListItemProperties extends PropertyAccess<ListItem> {
	ModelKeyProvider<ListItem> id();
	@Path("id")
	LabelProvider<ListItem> value();
}