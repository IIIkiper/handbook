package ru.svyaznoybank.handbook.war.gwt.model;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;

public interface DictionaryProperties extends ModelProperties<Dictionary> {

	// Id for dictionaries is the same as name;
	@Path("id")
	ValueProvider<Dictionary, String> name();
	
	@Path("id")
	LabelProvider<Dictionary> nameLabel();
	
	ValueProvider<Dictionary, String> description();
}