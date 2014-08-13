package ru.svyaznoybank.handbook.war.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class DateConverterProvider implements ParamConverterProvider {
	
	public static class DateConverter implements ParamConverter<Date> {
		@Override
		public Date fromString(String value) {
			try {
				return new Date(Long.parseLong(value));
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		@Override
		public String toString(Date value) {
			return String.valueOf(value.getTime());
		}
	}
	
	private final DateConverter dateConverter = new DateConverter();

	@Override
	@SuppressWarnings("unchecked")
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		return rawType.equals(Date.class) ? (ParamConverter<T>) dateConverter : null;
	}
}