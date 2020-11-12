package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="InvoicePayment")
public class InvoicePayment extends InvoicePaymentSubset {

	private Double	amountCurrency;
	private String	externalInvoiceReference1;
	private String	externalInvoiceReference2;
	private String	invoiceCustomerName;
	private String	invoiceCustomerNumber;
	private String	invoiceDueDate;
	private	String	invoiceOCR;
	private Double	invoiceTotal;
	private String	modeOfPayment;
	private Integer	modeOfPaymentAccount;
	private Integer	voucherNumber;
	private String	voucherSeries;
	private Integer voucherYear;
	private WriteOffs	writeOffs;

	
	public InvoicePayment() {}
	
	public InvoicePayment(InvoicePaymentSubset ps) {
		
		this.url = ps.url;
		this.amount = ps.amount;
		this.booked = ps.booked;
		this.currency = ps.currency;
		this.currencyRate = ps.currencyRate;
		this.currencyUnit = ps.currencyUnit;
		this.invoiceNumber = ps.invoiceNumber;
		this.number = ps.number;
		this.paymentDate = ps.paymentDate;
		this.source = ps.source;
		
	}
	
	@XmlElement(name="AmountCurrency")
	public Double getAmountCurrency() {
		return amountCurrency;
	}
	public void setAmountCurrency(Double amountCurrency) {
		this.amountCurrency = amountCurrency;
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
	
	@XmlElement(name="WriteOffs")
	public WriteOffs getWriteOffs() {
		return writeOffs;
	}
	public void setWriteOffs(WriteOffs writeOffs) {
		this.writeOffs = writeOffs;
	}
	
	
	
}