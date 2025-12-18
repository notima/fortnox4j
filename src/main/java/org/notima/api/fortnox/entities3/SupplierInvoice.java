package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "SupplierInvoice")
public class SupplierInvoice implements InvoiceInterface {

	private String url;
	private String accountingMethod;
	private Double administrationFee;
	private Double balance;
	private Boolean booked;
	private Boolean cancelled;
	private String comments;
	private String costCenter;
	private Boolean credit;
	private String creditReference;
	private String currency;
	private Double currencyRate;
	private Double currencyUnit;
	private Boolean	disablePaymentFile;
	private String dueDate;
	private String externalInvoiceNumber;
	private String externalInvoiceSeries;
	private String finalPayDate;
	private Double freight;
	private String givenNumber;
	private String invoiceDate;
	private Boolean paymentPending;
	private SupplierInvoiceRows supplierInvoiceRows;
	private String supplierName;
	private String supplierNumber;
	private String OCR;
	private String ourReference;
	private String project;
	private Double roundOffValue;
	private String salesType;
	private Double total;
	private Double VAT;
	private String voucherNumber;
	private String voucherSeries;
	private String voucherYear;
	private String yourReference;

	@XmlAttribute(name="url")	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getAccountingMethod() {
		return accountingMethod;
	}
	public void setAccountingMethod(String accountingMethod) {
		this.accountingMethod = accountingMethod;
	}
	@XmlElement(name="AdministrationFee")
	public Double getAdministrationFee() {
		return administrationFee;
	}

	public void setAdministrationFee(Double administrationFee) {
		this.administrationFee = administrationFee;
	}

	@XmlElement(name="Balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@XmlElement(name="Booked")
	public Boolean isBooked() {
		return booked;
	}

	public void setBooked(Boolean booked) {
		this.booked = booked;
	}

	@XmlElement(name="Cancelled")
	public Boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	@XmlElement(name="Comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	@XmlElement(name="Credit")	
	public Boolean isCredit() {
		return credit;
	}

	public void setCredit(Boolean credit) {
		this.credit = credit;
	}

	@XmlElement(name="CreditReferenceReference")	
	public String getCreditReference() {
		return creditReference;
	}

	public void setCreditReference(String creditReference) {
		this.creditReference = creditReference;
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

	@XmlElement(name="DisablePaymentFile")	
	public Boolean isDisablePaymentFile() {
		return disablePaymentFile;
	}

	public void setDisablePaymentFile(Boolean flag) {
		this.disablePaymentFile = flag;
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
	
	@XmlElement(name="FinalPayDate")
	public String getFinalPayDate() {
		return finalPayDate;
	}
	public void setFinalPayDate(String finalPayDate) {
		this.finalPayDate = finalPayDate;
	}
	
	@XmlElement(name="GivenNumber")
	public String getGivenNumber() {
		return givenNumber;
	}
	
	public void setGivenNumber(String nr) {
		givenNumber = nr;
	}
	
	@XmlElement(name="InvoiceDate")
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@XmlElement(name="Freight")
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	
	@XmlElement(name="PaymentPending")
	public Boolean getPaymentPending() {
		return paymentPending;
	}
	public void setPaymentPending(Boolean paymentPending) {
		this.paymentPending = paymentPending;
	}
	
	@XmlElement(name="SupplierInvoiceRows")	
	public SupplierInvoiceRows getSupplierInvoiceRows() {
		return supplierInvoiceRows;
	}

	public void setSupplierInvoiceRows(SupplierInvoiceRows invoiceRows) {
		this.supplierInvoiceRows = invoiceRows;
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
	
	@XmlElement(name="SalesType")	
	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	@XmlElement(name="OCR")	
	public String getOCR() {
		return OCR;
	}

	public void setOCR(String oCR) {
		this.OCR = oCR;
	}

	@XmlElement(name="OurReference")	
	public String getOurReference() {
		return ourReference;
	}

	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
	}

	@XmlElement(name="Project")	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@XmlElement(name="RoundOffValue")	
	public Double getRoundOff() {
		return roundOffValue;
	}

	public void setRoundOffValue(Double roundOff) {
		this.roundOffValue = roundOff;
	}

	@XmlElement(name="Total")	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@XmlElement(name="VAT")	
	public Double getVAT() {
		return VAT;
	}

	public void setVAT(Double totalVAT) {
		this.VAT = totalVAT;
	}

	@XmlElement(name="VoucherNumber")	
	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	@XmlElement(name="VoucherSeries")	
	public String getVoucherSeries() {
		return voucherSeries;
	}

	public void setVoucherSeries(String voucherSeries) {
		this.voucherSeries = voucherSeries;
	}

	@XmlElement(name="VoucherYear")	
	public String getVoucherYear() {
		return voucherYear;
	}

	public void setVoucherYear(String voucherYear) {
		this.voucherYear = voucherYear;
	}

	@XmlElement(name="YourReference")	
	public String getYourReference() {
		return yourReference;
	}

	public void setYourReference(String yourReference) {
		this.yourReference = yourReference;
	}
	
	@Override
	@XmlTransient
	public String getDocumentNumber() {
		return givenNumber;
	}
	
}
