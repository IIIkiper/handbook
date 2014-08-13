package ru.svyaznoybank.handbook.ejb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.svyaznoybank.handbook.jpa.dao.FilterableDao;
import ru.svyaznoybank.handbook.jpa.domain.Identity;
import ru.svyaznoybank.handbook.jpa.inquiry.Inquiry;

public final class PagingResult<T> {
	private static final Logger LOG = Logger.getLogger(PagingResult.class.getName());
	
	private final List<T> data;

	private final long totalLength;
	private final long offset;
	
	public PagingResult(List<T> data, long totalLength, long offset) {
		this.data = data;
		this.totalLength = totalLength;
		this.offset = offset;
	}
	
	/**
	 * TODO comment
	 * @param dtoClass
	 * @param entityClass
	 * @param dao
	 * @param params
	 */
	public <X extends Identity, Y extends Inquiry> PagingResult(Class<T> dtoClass, Class<? super X> entityClass, FilterableDao<X, Y> dao, Y params) {	
		data = new ArrayList<T>();
		totalLength = dao.count(params);		
		offset = params.getStart();
		if (totalLength != 0) {
			List<X> entities = dao.filter(params);
			try {
				for (X entity : entities) {
					data.add(dtoClass.getConstructor(entityClass).newInstance(entity));
				}
			} catch (Exception ex) {
				LOG.log(Level.SEVERE, "Failed to construct DTO object. Check input parameters.", ex);
			}
		}
	}

	// --- Getters ---
	public List<? extends T> getData() {
		return data;
	}
	
	public long getTotalLength() {
		return totalLength;
	}
	
	public long getOffset() {
		return offset;
	}
}