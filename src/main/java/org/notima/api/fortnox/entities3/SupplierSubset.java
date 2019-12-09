package org.notima.api.fortnox.entities3;

/**
 * 
 * Copyright 2019 Notima System Integration AB (Sweden)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Daniel Tamm
 *
 */

import javax.xml.bind.annotation.XmlElement;

public class SupplierSubset {

	private String 	address1;
	private String	address2;
	private String	bankAccountNumber;
	private String	BG;
	private String	BIC;
	private String	city;
	private String	costCenter;
	private String	countryCode;
	private String	currency;
	private Boolean	disablePaymentFile;
	private String	email;
	private String	IBAN;
	private String	PG;
	private String	name;
	private String	organisationNumber;
	private String	phone;
	private String	preDefinedAccount;
	private String	project;
	private String	supplierNumber;
	private String	termsOfPayment;
	private String	zipCode;
	
	@XmlElement(name="Address1")
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@XmlElement(name="Address2")
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@XmlElement(name="BankAccountNumber")
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	@XmlElement(name="BG")
	public String getBG() {
		return BG;
	}
	public void setBG(String bG) {
		BG = bG;
	}
	
	@XmlElement(name="BIC")
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
	}
	
	@XmlElement(name="City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@XmlElement(name="CountryCode")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@XmlElement(name="Currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name="DisablePaymentFile")
	public Boolean getDisablePaymentFile() {
		return disablePaymentFile;
	}
	public void setDisablePaymentFile(Boolean disablePaymentFile) {
		this.disablePaymentFile = disablePaymentFile;
	}
	
	@XmlElement(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement(name="IBAN")
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	
	@XmlElement(name="PG")
	public String getPG() {
		return PG;
	}
	public void setPG(String pG) {
		PG = pG;
	}
	
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="OrganisationNumber")
	public String getOrganisationNumber() {
		return organisationNumber;
	}
	public void setOrganisationNumber(String organisationNumber) {
		this.organisationNumber = organisationNumber;
	}
	
	@XmlElement(name="Phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@XmlElement(name="PreDefinedAccount")
	public String getPreDefinedAccount() {
		return preDefinedAccount;
	}
	public void setPreDefinedAccount(String preDefinedAccount) {
		this.preDefinedAccount = preDefinedAccount;
	}
	
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	@XmlElement(name="SupplierNumber")
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	
	@XmlElement(name="TermsOfPayment")
	public String getTermsOfPayment() {
		return termsOfPayment;
	}
	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}
	
	@XmlElement(name="ZipCode")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}
