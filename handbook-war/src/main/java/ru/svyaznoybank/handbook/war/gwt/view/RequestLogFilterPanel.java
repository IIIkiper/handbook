package ru.svyaznoybank.handbook.war.gwt.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;

import ru.svyaznoybank.handbook.war.gwt.presenter.RequestLogFilterPanelPresenter;

public class RequestLogFilterPanel implements RequestLogFilterPanelPresenter.Display {
	
	private final Widget self;
	
	@UiField
	protected TextField firstName;
	@UiField
	protected TextField lastName;
	@UiField
	protected TextField patronymic;
	@UiField
	protected DateField birthday;
	@UiField
	protected DateField beginDate;
	@UiField
	protected TimeField beginTime;
	@UiField
	protected DateField endDate;
	@UiField
	protected TimeField endTime;
	
	@UiField(provided = true)
	protected DateTimePropertyEditor dtpe = new DateTimePropertyEditor("dd.MM.yyyy");
	
	interface GUIBinder extends UiBinder<Widget, RequestLogFilterPanel> { }
	private static GUIBinder binder = GWT.create(GUIBinder.class);
	
	public RequestLogFilterPanel() {
		self = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return self;
	}

	@Override
	public TextField getFirstName() {
		return firstName;
	}

	@Override
	public TextField getLastName() {
		return lastName;
	}

	@Override
	public TextField getPatronymic() {
		return patronymic;
	}

	@Override
	public DateField getBirthday() {
		return birthday;
	}

	@Override
	public DateField getBeginDate() {
		return beginDate;
	}

	@Override
	public TimeField getBeginTime() {
		return beginTime;
	}

	@Override
	public DateField getEndDate() {
		return endDate;
	}

	@Override
	public TimeField getEndTime() {
		return endTime;
	}

	@Override
	public DateTimePropertyEditor getDtpe() {
		return dtpe;
	}
}