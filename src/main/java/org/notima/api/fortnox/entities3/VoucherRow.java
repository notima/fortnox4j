package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class VoucherRow {

	private Integer 	account;
	private String	costCenter;
	private Double	credit = 0D;
	private Double	debit = 0D;
	private String	description;
	private String	project;
	private Boolean	removed;
	private String	transactionInformation;
	private Double	quantity = 0D;
	
	@XmlElement(name="Account")
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	
	@XmlElement(name="CostCenter")	
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@XmlElement(name="Credit")
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	@XmlElement(name="Debit")
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
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
	
	@XmlElement(name="Removed")
	public Boolean getRemoved() {
		return removed;
	}
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	
	@XmlElement(name="TransactionInformation")
	public String getTransactionInformation() {
		return transactionInformation;
	}
	public void setTransactionInformation(String transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	
	@XmlElement(name="Quantity")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	
}
