package ru.svyaznoybank.handbook.war.gwt.json;

public interface Offer extends ClientPropertyEditable {
	
	public Double getCreditLine();
	public void setCreditLine(Double creditLine);
	
	public Double getMinCreditLine();
	public void setMinCreditLine(Double minCreditLine);
	
	public Double getMaxCreditLine();
	public void setMaxCreditLine(Double maxCreditLine);
	
	public Double getMinFirstPayment();
	public void setMinFirstPayment(Double minFirstPayment);
	
	public Double getMaxFirstPayment();
	public void setMaxFirstPayment(Double maxFirstPayment);
	
	public String getWorkflowCode();
	public void setWorkflowCode(String workflowCode);
	
	public Double getMinYield();
	public void setMinYield(Double minYield);
	
	public Double getMaxYield();
	public void setMaxYield(Double maxYield);
	
	public Integer getMinRisk();
	public void setMinRisk(Integer minRisk);
	
	public Integer getMaxRisk();
	public void setMaxRisk(Integer maxRisk);
	
	public Integer getAverageRisk();
	public void setAverageRisk(Integer averageRisk);
	
	public String getCurrencyId();
	public void setCurrencyId(String currencyId);
	
	public String getOfferCurrencyId();
	public void setOfferCurrencyId(String offerCurrencyId);
	
	public String getProductId();
	public void setProductId(String productId);
	
	public String getUwTypeId();
	public void setUwTypeId(String uwTypeId);
	
	public String getOfferTypeId();
	public void setOfferTypeId(String offerTypeId);
}