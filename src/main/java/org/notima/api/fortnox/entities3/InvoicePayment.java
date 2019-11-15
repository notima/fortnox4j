package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="InvoicePayment")
public class InvoicePayment {

	private Double	amount;
	private Double	amountCurrency;
	private Boolean	booked;
	private String	currency;
	private Double	currencyRate;
	private Double	currencyUnit;
	private String	externalInvoiceReference1;
	private String	externalInvoiceReference2;
	private String	invoiceCustomerName;
	private String	invoiceCustomerNumber;
	private Integer	invoiceNumber;
	private String	invoiceDueDate;
	private	String	invoiceOCR;
	private Double	invoiceTotal;
	private String	modeOfPayment;
	private Integer	modeOfPaymentAccount;
	private Integer	number;
	private String	paymentDate;
	private Integer	voucherNumber;
	private String	voucherSeries;
	private Integer voucherYear;
	private String	source;
	private WriteOffs	writeOffs;

	@XmlElement(name="Amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@XmlElement(name="AmountCurrency")
	public Double getAmountCurrency() {
		return amountCurrency;
	}
	public void setAmountCurrency(Double amountCurrency) {
		this.amountCurrency = amountCurrency;
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
	
	@XmlElement(name="InvoiceCustomerName")
	public String getInvoiceCustomerName() {
		return invoiceCustomerName;
	}
	public void setInvoiceCustomerName(String invoiceCustomerName) {
		this.invoiceCustomerName = invoiceCustomerName;
	}
	
	@XmlElement(name="InvoiceCustomerNumber")
	public String getInvoiceCustomerNumber() {
		return invoiceCustomerNumber;
	}
	public void setInvoiceCustomerNumber(String invoiceCustomerNumber) {
		this.invoiceCustomerNumber = invoiceCustomerNumber;
	}
	
	@XmlElement(name="InvoiceNumber")
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@XmlElement(name="InvoiceDueDate")
	public String getInvoiceDueDate() {
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(String invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	
	@XmlElement(name="InvoiceOCR")
	public String getInvoiceOCR() {
		return invoiceOCR;
	}
	public void setInvoiceOCR(String invoiceOCR) {
		this.invoiceOCR = invoiceOCR;
	}
	
	@XmlElement(name="InvoiceTotal")
	public Double getInvoiceTotal() {
		return invoiceTotal;
	}
	public void setInvoiceTotal(Double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	
	@XmlElement(name="ModeOfPayment")
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
	@XmlElement(name="ModeOfPaymentAccount")
	public Integer getModeOfPaymentAccount() {
		return modeOfPaymentAccount;
	}
	public void setModeOfPaymentAccount(Integer modeOfPaymentAccount) {
		this.modeOfPaymentAccount = modeOfPaymentAccount;
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
	
	@XmlElement(name="VoucherNumber")
	public Integer getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(Integer voucherNumber) {
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
	public Integer getVoucherYear() {
		return voucherYear;
	}
	public void setVoucherYear(Integer voucherYear) {
		this.voucherYear = voucherYear;
	}
	@XmlElement(name="Source")
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	@XmlElement(name="WriteOffs")
	public WriteOffs getWriteOffs() {
		return writeOffs;
	}
	public void setWriteOffs(WriteOffs writeOffs) {
		this.writeOffs = writeOffs;
	}
	
	
	
}