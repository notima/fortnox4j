package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Account")
public class Account {

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
	
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Double getBalanceBroughtForward() {
		return balanceBroughtForward;
	}
	public void setBalanceBroughtForward(Double balanceBroughtForward) {
		this.balanceBroughtForward = balanceBroughtForward;
	}
	public Double getBalanceCarriedForward() {
		return balanceCarriedForward;
	}
	public void setBalanceCarriedForward(Double balanceCarriedForward) {
		this.balanceCarriedForward = balanceCarriedForward;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getCostCenterSettings() {
		return costCenterSettings;
	}
	public void setCostCenterSettings(String costCenterSettings) {
		this.costCenterSettings = costCenterSettings;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProjectSettings() {
		return projectSettings;
	}
	public void setProjectSettings(String projectSettings) {
		this.projectSettings = projectSettings;
	}
	public String getSRU() {
		return SRU;
	}
	public void setSRU(String sRU) {
		SRU = sRU;
	}
	public String getTransactionInformation() {
		return transactionInformation;
	}
	public void setTransactionInformation(String transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	public String getTransactionInformationSettings() {
		return transactionInformationSettings;
	}
	public void setTransactionInformationSettings(
			String transactionInformationSettings) {
		this.transactionInformationSettings = transactionInformationSettings;
	}
	public String getVATCode() {
		return VATCode;
	}
	public void setVATCode(String vATCode) {
		VATCode = vATCode;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	
	
}
