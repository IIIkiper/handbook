package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;

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

public class DictionaryEditorPresenter extends Presenter<DictionaryEditorPresenter.Display> {
	
	private boolean addMode;
	private Dictionary dictionary;
	private final DictionaryPresenter dictionaryPresenter;
	
	public interface Display extends IsWidget {
		Window getWindow();
		TextButton getSaveBtn();
		TextButton getCancelBtn();
		FormPanel getFormPanel();
		TextField getId();
		TextField getDescription();
	}

	public DictionaryEditorPresenter(Display view, DictionaryPresenter dictionaryPresenter) {
		super(view);
		this.dictionaryPresenter = dictionaryPresenter;
		bind();
	}
	
	@Override
	public void init(HasWidgets container) {
		throw new RuntimeException("not supported");
	}
	
	public void init(Dictionary dictionary, boolean addMode) {
		this.addMode = addMode;
		this.dictionary = dictionary;
		
		if (addMode) {
			view.getWindow().setHeadingText("Добавить в справочник");
		} else {
			view.getId().setValue(dictionary.getId());
			view.getId().disable();
			view.getDescription().setValue(dictionary.getDescription());
			view.getWindow().setHeadingText("Редактирование [" + dictionary.getId() + "]");
		}
		
		view.getWindow().show();
	}
	
	public void close() {
		view.getWindow().hide();
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
				view.getId().enable();
			}
		});
		
		view.getSaveBtn().addSelectHandler(new SelectHandler() {	
			@Override
			public void onSelect(SelectEvent event) {
				if (!view.getFormPanel().isValid()) {
					return;
				}
				
				if (addMode) {
					dictionary.setDescription(view.getDescription().getCurrentValue());
					dictionary.setId(view.getId().getCurrentValue());
					dictionaryPresenter.addRecord(dictionary);
				} else {
					if (dictionary.getDescription().equals(view.getDescription().getCurrentValue())) {
						close();
						return;
					}
					dictionary.setDescription(view.getDescription().getCurrentValue());
					dictionaryPresenter.editRecord(dictionary);
				}
				
				close();
			}
		});
	}
}