package ru.svyaznoybank.handbook.war.gwt.rest;

import java.util.Collection;
import java.util.Date;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.AutoBeanVisitor;
import com.sencha.gxt.data.client.writer.UrlEncodingWriter;

public class RestUrlEncodingWriter<M> extends UrlEncodingWriter<M> {

	public RestUrlEncodingWriter(AutoBeanFactory factory, Class<M> clazz) {
		super(factory, clazz);
	}
	
	@Override
	public void appendModel(M model, final UrlEncodingAppender appender) {
		AutoBean<M> autoBean = getAutoBean(model);

		autoBean.accept(new AutoBeanVisitor() {
			@Override
			public boolean visitCollectionProperty(String propertyName, AutoBean<Collection<?>> value, CollectionPropertyContext ctx) {
				if (value != null) {
					for (Object obj : value.as()) {
						AutoBean<?> subBean = AutoBeanUtils.getAutoBean(obj);
						subBean.accept(this);
					}
				}
				return false;
			}

			@Override
			public boolean visitValueProperty(String propertyName, Object value, PropertyContext ctx) {
				if (value != null) {
					if (value instanceof Date) { // encode date as milliseconds
						appender.append(propertyName, String.valueOf(((Date) value).getTime()));
					} else {
						appender.append(propertyName, value.toString());
					}
				}
				return false;
			}
		});
	}
}