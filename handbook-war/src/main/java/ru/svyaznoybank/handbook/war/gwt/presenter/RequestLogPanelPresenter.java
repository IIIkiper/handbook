package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLogLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.view.RequestLogFilterPanel;
import ru.svyaznoybank.handbook.war.gwt.view.RequestLogGrid;

public class RequestLogPanelPresenter extends SearchPanelPresenter<S2Presenter.Display, RequestLogLoadConfig> {
	
	RequestLogFilterPanelPresenter rlfpp = new RequestLogFilterPanelPresenter(new RequestLogFilterPanel());
	RequestLogGridPresenter rlgp = new RequestLogGridPresenter(new RequestLogGrid());

	public RequestLogPanelPresenter(S2Presenter.Display view) {
		super(view);
	}
	
	@Override
	protected void bind() {
//		capPresenter.init(view.getFormPanel());
		view.getFormPanel().setLabelWidth(105);
		
//		cgPresenter.init(view.getCenterPanel());
		
		view.getCenterPanel().setHeadingText("Лог запросов");
		
		super.bind();
	}

	@Override
	protected RequestLogGridPresenter getGridPresenter() {
		return rlgp;
	}

	@Override
	protected RequestLogFilterPanelPresenter getFilterPanel() {
		return rlfpp;
	}
}