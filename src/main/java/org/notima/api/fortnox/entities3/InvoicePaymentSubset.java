package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class InvoicePaymentSubset {

	protected String url;
	protected Double	amount;
	protected Boolean	booked;
	protected String	currency;
	protected Double	currencyRate;
	protected Double	currencyUnit;
	protected Integer	invoiceNumber;
	protected Integer	number;
	protected String	paymentDate;
	protected String	source;
	
	@XmlAttribute(name="url")	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@XmlElement(name="Booked")
	public Boolean getBooked() {
		return booked;
	}
	public void setBooked(Boolean booked) {
		this.booked = booked;
	}
	
	@XmlElement(name="Currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name="CurrencyRate")
	public Double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	@XmlElement(name="CurrencyUnit")
	public Double getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(Double currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	
	@XmlElement(name="InvoiceNumber")
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@XmlElement(name="Number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@XmlElement(name="PaymentDate")
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@XmlElement(name="Source")
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
}
