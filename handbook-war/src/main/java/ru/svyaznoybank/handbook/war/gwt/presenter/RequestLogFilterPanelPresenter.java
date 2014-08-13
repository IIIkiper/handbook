package ru.svyaznoybank.handbook.war.gwt.presenter;

import java.util.Date;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLogLoadConfig;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;

public class RequestLogFilterPanelPresenter extends 
	FilterPanelPresenter<RequestLogFilterPanelPresenter.Display, RequestLogLoadConfig> 
{
	
	public interface Display extends IsWidget {
		public TextField getFirstName();
		public TextField getLastName();
		public TextField getPatronymic();
		public DateField getBirthday();
		public DateField getBeginDate();
		public TimeField getBeginTime();
		public DateField getEndDate();
		public TimeField getEndTime();
		public DateTimePropertyEditor getDtpe();
	}
	
	public RequestLogFilterPanelPresenter(Display view) {
		super(view);
	}

	@Override
	protected void bind() {
		// No actions required here
	}

	@Override
	protected void configure(RequestLogLoadConfig config) {
		config.setFirstName(view.getFirstName().getCurrentValue());
		config.setLastName(view.getLastName().getCurrentValue());
		config.setPatronymic(view.getPatronymic().getCurrentValue());
		config.setBirthday(view.getBirthday().getCurrentValue());
		
		config.setBeginDate(getDate(view.getBeginDate().getCurrentValue(), view.getBeginTime().getCurrentValue()));
		config.setEndDate(getDate(view.getEndDate().getCurrentValue(), view.getEndTime().getCurrentValue()));
	}
	
	private Date getDate(Date date, Date time) {
		if (date == null) {
			return null;
		}		
		if (time == null) {
			return date;
		}		
		
		return new Date(date.getTime() + time.getTime() - (long) getDateWithoutTime());
	}
	
	public native double getDateWithoutTime() /*-{
		var date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setMilliseconds(0);
		return date.getTime();
	}-*/;
}