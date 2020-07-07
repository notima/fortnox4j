package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Account")
public class Account {

	private String	url;
	private Boolean active;
	private Double	balanceBroughtForward;
	private Double	balanceCarriedForward;
	private String	costCenter;
	private String	costCenterSettings;
	private String	description;
	private Integer	number;
	private String	project;
	private String	projectSettings;
	private String	SRU;
	private String	transactionInformation;
	private String	transactionInformationSettings;
	private String	VATCode;
	private Integer year;
	
	@XmlAttribute(name="url")	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Active")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@XmlElement(name="BalanceBroughtForward")
	public Double getBalanceBroughtForward() {
		return balanceBroughtForward;
	}
	public void setBalanceBroughtForward(Double balanceBroughtForward) {
		this.balanceBroughtForward = balanceBroughtForward;
	}
	
	@XmlElement(name="BalanceCarriedForward")
	public Double getBalanceCarriedForward() {
		return balanceCarriedForward;
	}
	public void setBalanceCarriedForward(Double balanceCarriedForward) {
		this.balanceCarriedForward = balanceCarriedForward;
	}
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@XmlElement(name="CostCenterSettings")
	public String getCostCenterSettings() {
		return costCenterSettings;
	}
	public void setCostCenterSettings(String costCenterSettings) {
		this.costCenterSettings = costCenterSettings;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="Number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	@XmlElement(name="ProjectSettings")
	public String getProjectSettings() {
		return projectSettings;
	}
	public void setProjectSettings(String projectSettings) {
		this.projectSettings = projectSettings;
	}
	
	@XmlElement(name="SRU")
	public String getSRU() {
		return SRU;
	}
	public void setSRU(String sRU) {
		SRU = sRU;
	}
	
	@XmlElement(name="TransactionInformation")
	public String getTransactionInformation() {
		return transactionInformation;
	}
	public void setTransactionInformation(String transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	
	@XmlElement(name="TransactionInformationSettings")
	public String getTransactionInformationSettings() {
		return transactionInformationSettings;
	}
	public void setTransactionInformationSettings(
			String transactionInformationSettings) {
		this.transactionInformationSettings = transactionInformationSettings;
	}
	
	@XmlElement(name="VATCode")
	public String getVATCode() {
		return VATCode;
	}
	public void setVATCode(String vATCode) {
		VATCode = vATCode;
	}
	
	@XmlElement(name="Year")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
