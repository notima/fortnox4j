package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class VoucherSubset {

	protected String url;

	protected String comments;
	protected String description;
	protected String referenceNumber;
	protected String referenceType;
	protected String transactionDate;
	protected Integer voucherNumber;
	protected String voucherSeries;
	protected Integer year;
	protected Integer approvalState;
	
	@XmlAttribute(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="ReferenceNumber")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	@XmlElement(name="ReferenceType")
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	
	@XmlElement(name="TransactionDate")	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
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
	
	@XmlElement(name="Year")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@XmlElement(name="ApprovalState")
	public Integer getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(Integer approvalState) {
		this.approvalState = approvalState;
	}
	
	
	
}
