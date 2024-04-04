package org.notima.api.fortnox.entities3;

import java.beans.Transient;

import javax.xml.bind.annotation.XmlElement;

import org.notima.api.fortnox.FortnoxConstants;
import org.notima.util.NumberUtils;

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
	
	@Transient
	public double getAbsoluteAmount() {
		double result = Math.abs(debit.doubleValue() - credit.doubleValue());
		return result;
	}
	
	@Transient
	public double getAmount() {
		double result = debit.doubleValue() - credit.doubleValue();
		return NumberUtils.roundToPrecision(result, FortnoxConstants.DEFAULT_ROUNDING_PRECISION);
	}
	
	@Transient
	public void setAmount(Double amount) {
		if (amount<0) {
			setCredit(-amount);
		} else {
			setDebit(amount);
		}
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
	
	public void appendTransactionInformation(String appendTrxInfo) {
		if (transactionInformation==null || transactionInformation.trim().length()==0) {
			setTransactionInformation(appendTrxInfo);
		} else {
			transactionInformation += ", " + appendTrxInfo;
		}
	}
	
	@XmlElement(name="Quantity")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Creates a negated row 
	 * 
	 * @return 		A negated row 
	 */
	public VoucherRow negated() {
		
		VoucherRow vr = new VoucherRow();
		vr.account = this.account;
		vr.costCenter = this.costCenter;
		vr.debit = this.credit;
		vr.credit = this.debit;
		vr.description = this.description;
		vr.project = this.project;
		vr.quantity = -this.quantity;
		vr.transactionInformation = this.transactionInformation;

		return vr;
	}
	
}
