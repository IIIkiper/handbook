package ru.svyaznoybank.handbook.jpa.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//import oracle.xdb.XMLType;

@Converter
public class XmlResponseConverter implements AttributeConverter<String, Object> {
	
	/*
	@Resource(lookup = "jdbc/handbook")
	private DataSource ds;
	*/

	@Override
	public Object convertToDatabaseColumn(String attribute) {
		/*
		try {
			/
			 * If you are using a JTA DataSource for your JPA persistence unit, then you can 
			 * just access the JDBC Connection from the JavaEE containers DataSource. 
			 * Just inject or lookup the JTA DataSource from JNDI and get a Connection from it. 
			 * As long as you are within a JTA transactional context, the JDBC Connection 
			 * will be the same Connection used by JPA.
			 /
			return XMLType.createXML(ds.getConnection(), attribute);
		} catch (SQLException ex) {
			LOG.error("Failed to construct Oracle XMLType", ex);
			return null;
		}
		*/
		return attribute;
	}

	@Override
	public String convertToEntityAttribute(Object dbData) {
		/*
		try {
			LOG.error("Data source: [" + ds + "]. dbData type is [" + dbData.getClass() + "].");
			if (dbData instanceof XMLType) {
				return dbData == null ? null : ((XMLType)dbData).getStringVal();
			}
			return dbData.toString();
		} catch (SQLException ex) {
			LOG.error("Failed to extract string from Oracle XMLType", ex);
			return null;
		}
		*/
		return dbData.toString();
	}
}