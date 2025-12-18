package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class SupplierInvoiceSubset implements InvoiceInterface {

	private Double balance;
	private boolean booked;
	private boolean cancelled;
	private String currency;
	private double currencyRate;
	private double currencyUnit;
	private String supplierName;
	private String supplierNumber;
	private String givenNumber;
	private String dueDate;
	private String externalInvoiceNumber;
	private String externalInvoiceSeries;
	private String invoiceDate;
	private String project;
	private double total;
	private String authorizerName;

	@XmlElement(name="Balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@XmlElement(name="Booked")
	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	@XmlElement(name="Cancelled")
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@XmlElement(name="Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@XmlElement(name="CurrencyRate")
	public double getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(double currencyRate) {
		this.currencyRate = currencyRate;
	}

	@XmlElement(name="CurrencyUnit")
	public double getCurrencyUnit() {
		return currencyUnit;
	}
	
	public void setCurrencyUnit(double currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	@XmlElement(name="SupplierName")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@XmlElement(name="SupplierNumber")	
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	@Override
	public String getDocumentNumber() {
		return givenNumber;
	}

	@XmlElement(name="DueDate")	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@XmlElement(name="ExternalInvoiceNumber")	
	public String getExternalInvoiceNumber() {
		return externalInvoiceNumber;
	}

	public void setExternalInvoiceNumber(String externalInvoiceNumber) {
		this.externalInvoiceNumber = externalInvoiceNumber;
	}

	@XmlElement(name="ExternalInvoiceSeries")	
	public String getExternalInvoiceSeries() {
		return externalInvoiceSeries;
	}

	public void setExternalInvoiceSeries(String externalInvoiceSeries) {
		this.externalInvoiceSeries = externalInvoiceSeries;
	}

	@XmlElement(name="InvoiceDate")	
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@XmlElement(name="Project")	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@XmlElement(name="Total")	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@XmlElement(name="GivenNumber")
	public String getGivenNumber() {
		return givenNumber;
	}

	public void setGivenNumber(String givenNumber) {
		this.givenNumber = givenNumber;
	}

	@XmlElement(name="AuthorizerName")
	public String getAuthorizerName() {
		return authorizerName;
	}

	public void setAuthorizerName(String authorizerName) {
		this.authorizerName = authorizerName;
	}
	
}
