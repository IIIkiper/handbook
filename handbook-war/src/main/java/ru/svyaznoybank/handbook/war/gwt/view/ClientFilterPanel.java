package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.LongField;
import com.sencha.gxt.widget.core.client.form.TextField;

import ru.svyaznoybank.handbook.war.gwt.presenter.ClientFilterPanelPresenter;

public class ClientFilterPanel implements ClientFilterPanelPresenter.Display {
	
	private final Widget self;
	
	@UiField
	protected LongField clientId;
	@UiField
	protected TextField firstName;
	@UiField
	protected TextField lastName;
	@UiField
	protected DateField birthday;
	@UiField
	protected TextField docSeries;
	@UiField
	protected TextField docNumber;
	
	@UiField(provided = true)
	protected DateTimePropertyEditor dtpe = new DateTimePropertyEditor("dd.MM.yyyy");
	
	interface GUIBinder extends UiBinder<Widget, ClientFilterPanel> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public ClientFilterPanel() {
		self = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return self;
	}

	@Override
	public Long getClientId() {
		return clientId.getCurrentValue();
	}

	@Override
	public String getFirstName() {
		return firstName.getCurrentValue();
	}

	@Override
	public String getLastName() {
		return lastName.getCurrentValue();
	}

	@Override
	public Date getBirthday() {
		return birthday.getCurrentValue();
	}

	@Override
	public String getDocSeries() {
		return docSeries.getCurrentValue();
	}

	@Override
	public String getDocNumber() {
		return docNumber.getCurrentValue();
	}
}