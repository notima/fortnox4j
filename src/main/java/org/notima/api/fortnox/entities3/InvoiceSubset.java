package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

import org.notima.api.fortnox.FortnoxConstants;

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
	private String invoiceType;
	private boolean noxFinans;
	private String OCR;
	private String project;
	private boolean sent;
	private String termsOfPayment;
	private double total;
	private String wayOfDelivery;

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

	@XmlElement(name="InvoiceType")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@XmlElement(name="NoxFinans")
	public boolean isNoxFinans() {
		return noxFinans;
	}

	public void setNoxFinans(boolean noxFinans) {
		this.noxFinans = noxFinans;
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

	@XmlElement(name="TermsOfPayment")
	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	@XmlElement(name="Total")	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@XmlElement(name="WayOfDelivery")
	public String getWayOfDelivery() {
		return wayOfDelivery;
	}

	public void setWayOfDelivery(String wayOfDelivery) {
		this.wayOfDelivery = wayOfDelivery;
	}
	

	/**
	 * Method to check if the reference field is available in the invoice subset. If this returns false,
	 * try to get the reference using Invoice.fetchRefInField(String);
	 * 
	 * 
	 * @param referenceField		The reference field to test.
	 * @return						True if the reference field is valid.
	 */
	public boolean isValidReference(String referenceField) {

		boolean validReference = 
			FortnoxConstants.EXTREF1.equalsIgnoreCase(referenceField) ||
			FortnoxConstants.EXTREF2.equalsIgnoreCase(referenceField) ||
			FortnoxConstants.OCR.equalsIgnoreCase(referenceField);
		
		return validReference;
		
	}
	
	/**
	 * Gets the reference in given field name and returns the reference.
	 * 
	 * @param referenceField		Any of the fields FortnoxConstants.EXTREF1, EXTREF2 or OCR.
	 * @return						The reference if any. An non valid reference field will return null,
	 */
	public String fetchRefInfield(String referenceField) {
		
		String refInFortnox = null;
		
		if (FortnoxConstants.EXTREF1.equalsIgnoreCase(referenceField)) {
			refInFortnox = getExternalInvoiceReference1();
		} else if (FortnoxConstants.EXTREF2.equalsIgnoreCase(referenceField)) {
			refInFortnox = getExternalInvoiceReference2();
		} else if (FortnoxConstants.OCR.equalsIgnoreCase(referenceField)) {
			refInFortnox = getOCR();
		}
		
		return refInFortnox;
	}
	
	
	
}
