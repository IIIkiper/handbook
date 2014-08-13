package ru.svyaznoybank.handbook.jpa.inquiry;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * This class and it's subclasses are desined to be wrappers for query/martix params from HTTP requests.
 * @author Roman Zaripov
 */
public class Inquiry {
	public enum SortOrder {ASC, DESC}
	
	public static class Sort {
		private String property;
		private SortOrder direction;
		
		public Sort() { }
		
		public Sort(SortOrder direction, String property) {
			this.direction = direction;
			this.property = property;
		}
		
		// --- Getters / Setters ---
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public SortOrder getDirection() {
			return direction;
		}
		public void setDirection(SortOrder direction) {
			this.direction = direction;
		}
	}

	@QueryParam("offset") @DefaultValue("-1")
	private int start = -1;
	
	@QueryParam("limit") @DefaultValue("-1")
	private int limit = -1;
	
	// TODO uses for server-side sort
	private Sort[] sort;
	
	/** Parameter name for generic search string. */
	@QueryParam("query")
	private String query;
	
	@QueryParam("ids")
	private Collection<?> ids;
	
	//private boolean cacheable;
	
	private final Collection<String> associationsToFetch = new HashSet<>();
	
	public final boolean hasPaging() {
		return start != -1 && limit != -1;
	}
	
	public final void addAssociationsToFetch(String... entities) {
		if (entities != null) {
			associationsToFetch.addAll(Arrays.asList(entities));
		}
	}
	
	public final void setAssociationsToFetch(String... entities) {
		associationsToFetch.clear();
		addAssociationsToFetch(entities);
	}
	
	/*
	public final void makeCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}
	*/
	
	public final void setPaging(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	// --- Getters / Setters ---
	public int getStart() {
		return start;
	}
	/*
	public boolean isCacheable() {
		return cacheable;
	}
	*/
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Collection<String> getAssociationsToFetch() {
		return associationsToFetch;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Sort[] getSort() {
		return sort;
	}
	public void setSort(Sort[] sort) {
		this.sort = sort;
	}
	public Collection<?> getIds() {
		return ids;
	}
	public void setIds(Collection<?> ids) {
		this.ids = ids;
	}
}