package ru.svyaznoybank.handbook.war.gwt.presenter;

import java.util.Date;

import ru.svyaznoybank.handbook.war.gwt.json.ClientLoadConfig;

import com.google.gwt.user.client.ui.IsWidget;

public class ClientFilterPanelPresenter extends FilterPanelPresenter<ClientFilterPanelPresenter.Display, ClientLoadConfig> {
	
	public ClientFilterPanelPresenter(Display view) {
		super(view);
	}

	public interface Display extends IsWidget {	
		Long getClientId();
		String getFirstName();
		String getLastName();
		Date getBirthday();
		String getDocSeries();
		String getDocNumber();
	}

	@Override
	protected void bind() { }

	@Override
	protected void configure(ClientLoadConfig config) {
		config.setBirthday(view.getBirthday());
		config.setClientId(view.getClientId());
		config.setFirstName(view.getFirstName());
		config.setLastName(view.getLastName());
		config.setDocNumber(view.getDocNumber());
		config.setDocSeries(view.getDocSeries());
	}
}