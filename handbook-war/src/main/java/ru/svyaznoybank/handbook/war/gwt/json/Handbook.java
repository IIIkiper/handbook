package ru.svyaznoybank.handbook.war.gwt.json;

public interface Handbook extends ClientPropertyEditable {
	
	public String getValue();
	public void setValue(String value);
	
	public String getProductId();
	public void setProductId(String productId);
	
	public String getCurrencyId();
	public void setCurrencyId(String currencyId);
	
	public String getHandbookTypeId();
	public void setHandbookTypeId(String handbookTypeId);
}