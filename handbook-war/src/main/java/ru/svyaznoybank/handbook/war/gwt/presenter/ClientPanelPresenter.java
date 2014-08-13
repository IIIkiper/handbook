package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.ClientLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.view.ClientGrid;
import ru.svyaznoybank.handbook.war.gwt.view.ClientFilterPanel;

public class ClientPanelPresenter extends SearchPanelPresenter<S2Presenter.Display, ClientLoadConfig> {
	
	ClientFilterPanelPresenter capPresenter = new ClientFilterPanelPresenter(new ClientFilterPanel());
	ClientGridPresenter cgPresenter = new ClientGridPresenter(new ClientGrid());
	
	public ClientPanelPresenter(S2Presenter.Display view) {
		super(view);
	}

	@Override
	protected void bind() {
		super.bind();
		
		view.getFormPanel().setLabelWidth(105);
		view.getCenterPanel().setHeaderVisible(false);
	}

	@Override
	protected ClientGridPresenter getGridPresenter() {
		return cgPresenter;
	}

	@Override
	protected ClientFilterPanelPresenter getFilterPanel() {
		return capPresenter;
	}
}