package ru.svyaznoybank.handbook.war.gwt.view;

import java.util.ArrayList;
import java.util.List;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;
import ru.svyaznoybank.handbook.war.gwt.presenter.AbstractGridPresenter;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;

/**
 * Base class for all grids in application. Declares grid with paging toolbar. Designed to be used with uibinder.
 * Public field access required by uibinder.
 * @author Roman Zaripov
 */
public abstract class AbstractGrid<T extends Dto> implements AbstractGridPresenter.Display<T> {
	
	@UiField(provided = true)
	public ListStore<T> store;
	
	@UiField(provided = true)
	public ColumnModel<T> cm;
	
	@UiField
	public Grid<T> grid;
	
	@UiField
	public PagingBar pagingBar;
	
	protected Widget self = null;
	
	private final RowNumberer<T> numberer;
		
	public AbstractGrid() {
		List<ColumnConfig<T, ?>> configs = new ArrayList<ColumnConfig<T, ?>>();
		configureColumnConfigs(configs);
		
		numberer = new RowNumberer<T>(new IdentityValueProvider<T>("id"));
		configs.add(0, numberer);
		
		this.cm = new ColumnModel<T>(configs);
		
		this.store = new ListStore<T>(new ModelKeyProvider<T>() {
			@Override
			public String getKey(T item) {
				return item.getId() + "";	
			}
		});
	}
	
	protected abstract void configureColumnConfigs(List<ColumnConfig<T, ?>> configs);
	
	@Override
	public PagingBar getPagingBar() {
		return pagingBar;
	}
	
	@Override
	public ListStore<T> getStore() {
		return store;
	}
	
	@Override
	public Grid<T> getGrid() {
		return grid;
	}
	
	@Override
	public RowNumberer<T> getRowNumberer() {
		return numberer;
	}
	
	@Override
	public Widget asWidget() {
		return self;
	}
}