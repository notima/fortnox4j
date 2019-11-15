package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class FinancialYearSubset {

	private String	url;
	private Integer	id;
	private String	fromDate;
	private String	toDate;
	private String	accountCharts;
	private String	accountingMethod;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement(name="FromDate")
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	@XmlElement(name="ToDate")
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@XmlElement(name="accountCharts")
	public String getAccountCharts() {
		return accountCharts;
	}
	public void setAccountCharts(String accountCharts) {
		this.accountCharts = accountCharts;
	}
	
	@XmlElement(name="AccountingMethod")
	public String getAccountingMethod() {
		return accountingMethod;
	}
	public void setAccountingMethod(String accountingMethod) {
		this.accountingMethod = accountingMethod;
	}
	
	
	
}
