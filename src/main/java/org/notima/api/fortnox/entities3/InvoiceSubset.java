package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class InvoiceSubset {

	private double balance;
	private boolean booked;
	private boolean cancelled;
	private String currency;
	private double currencyRate;
	private double currencyUnit;
	private String customerName;
	private String customerNumber;
	private String documentNumber;
	private String dueDate;
	private String externalInvoiceReference1;
	private String externalInvoiceReference2;
	private String invoiceDate;
	private String OCR;
	private String project;
	private boolean sent;
	private double total;

	@XmlElement(name="Balance")
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
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

	@XmlElement(name="CustomerName")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@XmlElement(name="CustomerNumber")	
	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@XmlElement(name="DocumentNumber")	
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@XmlElement(name="DueDate")	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@XmlElement(name="ExternalInvoiceReference1")	
	public String getExternalInvoiceReference1() {
		return externalInvoiceReference1;
	}

	public void setExternalInvoiceReference1(String externalInvoiceReference1) {
		this.externalInvoiceReference1 = externalInvoiceReference1;
	}

	@XmlElement(name="ExternalInvoiceReference2")	
	public String getExternalInvoiceReference2() {
		return externalInvoiceReference2;
	}

	public void setExternalInvoiceReference2(String externalInvoiceReference2) {
		this.externalInvoiceReference2 = externalInvoiceReference2;
	}

	@XmlElement(name="InvoiceDate")	
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@XmlElement(name="OCR")	
	public String getOCR() {
		return OCR;
	}

	public void setOCR(String oCR) {
		OCR = oCR;
	}

	@XmlElement(name="Project")	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@XmlElement(name="Sent")	
	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	@XmlElement(name="Total")	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
