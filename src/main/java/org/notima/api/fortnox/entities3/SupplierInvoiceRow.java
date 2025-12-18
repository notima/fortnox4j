package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class SupplierInvoiceRow {

    private String account;
    private String accountDescription;
    private String articleNumber;
    private String code;
    private String costCenter;
    private Double credit;
    private Double creditCurrency;
    private Double debit;
    private Double debitCurrency;
    private String itemDescription;
    private Double price;
    private String project;
    private Double quantity;
    private String stockLocationCode;
    private String stockPointCode;
    private Double total;
    private String transactionInformation;
    private String unit;
    
    @XmlElement(name="Account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@XmlElement(name="AccountDescription")
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	
	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}
	
	@XmlElement(name="Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	@XmlElement(name="Quantity")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	@XmlElement(name="ItemDescription")
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	@XmlElement(name="Price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	@XmlElement(name="Total")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@XmlElement(name="Unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@XmlElement(name="Credit")
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	@XmlElement(name="CreditCurrency")
	public Double getCreditCurrency() {
		return creditCurrency;
	}
	public void setCreditCurrency(Double creditCurrency) {
		this.creditCurrency = creditCurrency;
	}
	
	@XmlTransient
	public Double getAmount() {
		return (
		 (debit!=null ? debit.doubleValue() : 0.0) -
		 (credit!=null ? credit.doubleValue() : 0.0)
		 );
				
	}
	
	@XmlTransient
	public Double getAmountCurrency() {
		return (
				 (debitCurrency!=null ? debitCurrency.doubleValue() : 0.0) -
				 (creditCurrency!=null ? creditCurrency.doubleValue() : 0.0)
				 );
	}
	
	@XmlElement(name="Debit")
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	
	@XmlElement(name="DebitCurrency")
	public Double getDebitCurrency() {
		return debitCurrency;
	}
	public void setDebitCurrency(Double debitCurrency) {
		this.debitCurrency = debitCurrency;
	}
	
	@XmlElement(name="StockLocationCode")	
	public String getStockLocationCode() {
		return stockLocationCode;
	}
	public void setStockLocationCode(String stockLocationCode) {
		this.stockLocationCode = stockLocationCode;
	}
	
	@XmlElement(name="StockPointCode")	
	public String getStockPointCode() {
		return stockPointCode;
	}
	public void setStockPointCode(String stockPointCode) {
		this.stockPointCode = stockPointCode;
	}
	
	@XmlElement(name="TransactionInformation")
	public String getTransactionInformation() {
		return transactionInformation;
	}
	public void setTransactionInformation(String transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	
	
}
