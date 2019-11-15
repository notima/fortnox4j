package org.notima.api.fortnox.entities3;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Voucher")
public class Voucher {

	private String	url;
	private String 	comments;
	private String	costCenter;
	private String	description;
	private String	project;
	private String	referenceNumber;
	private String	referenceType;
	private String	transactionDate;
	private Integer	voucherNumber;
	private VoucherRows voucherRows;
	private String	voucherSeries;
	private Integer	year;
	
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
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	@XmlElement(name="Description")	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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
	
	@XmlElement(name="VoucherRows")	
	public VoucherRows getVoucherRows() {
		return voucherRows;
	}
	public void setVoucherRows(VoucherRows voucherRows) {
		this.voucherRows = voucherRows;
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
	
	public void addVoucherRow(VoucherRow r) {
		if (voucherRows==null) {
			voucherRows = new VoucherRows();
		}
		if (voucherRows.getVoucherRow()==null) {
			voucherRows.setVoucherRow(new ArrayList<VoucherRow>());
		}
		voucherRows.getVoucherRow().add(r);
	}
	
	
}
