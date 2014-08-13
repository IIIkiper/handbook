package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.DictionaryLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.Dto;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseEvent.BeforeCollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public abstract class SearchPanelPresenter<T extends S2Presenter.Display, C extends DictionaryLoadConfig> extends S2Presenter<T> {
	
	public SearchPanelPresenter(T view) {
		super(view);
	}
	
	@Override
	protected void bind() {	
		getFilterPanel().init(view.getFormPanel());	
		getGridPresenter().init(view.getCenterPanel());
		
		super.bind();
			
		view.getSearchButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (view.getFormPanel().isValid()) {				
					C config = getGridPresenter().beanFactory.loadConfig().as();
					
					getFilterPanel().configure(config);
					
					config.setOffset(0); // TODO ???
					config.setLimit(getGridPresenter().loader.getLimit());
										
					getGridPresenter().loader.load(config);
				}
			}
		});		
		
		view.getWestPanel().addBeforeCollapseHandler(new BeforeCollapseHandler() {
			@Override
			public void onBeforeCollapse(BeforeCollapseEvent event) {
				configureSearch();
			}
		});
		
		/*
		 * Expand event doesn't fire on west panel, but works for parent border layout
		 * container. GXT bug? 
		 */
		view.getCnt().addExpandHandler(new ExpandItemHandler<ContentPanel>() {
			@Override
			public void onExpand(ExpandItemEvent<ContentPanel> event) {
				configureSearch();		
			}
		});
		
		configureSearch();
	}
	
	protected abstract FastSearchGridPresenter<? extends Dto, C, ? extends FastSearchGridPresenter.Display<?>> getGridPresenter();
	
	protected abstract FilterPanelPresenter<? extends IsWidget, C> getFilterPanel();
	
	private void configureSearch() {		
		if (view.getWestPanel().isExpanded()) {
			getGridPresenter().view.getToolbar().hide();
//			getGridPresenter().view.getSearchField().disable();
//			getGridPresenter().view.getSearchButton().disable();
		} else {
			getGridPresenter().view.getToolbar().show();
//			getGridPresenter().view.getSearchField().enable();
//			getGridPresenter().view.getSearchButton().enable();
		}
	}
}