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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Supplier class
 * 
 * https://developer.fortnox.se/documentation/resources/suppliers/
 * 
 * @author Daniel Tamm
 *
 */
@XmlRootElement(name = "Supplier")
public class Supplier extends SupplierSubset {

	private Boolean	active;
	private String	bank;
	private String	branchCode;
	private String	clearingNumber;
	private String	comments;
	private String	fax;
	private String	ourReference;
	private String	ourCustomerNumber;
	private String	phone1;
	private String	phone2;
	private String 	VATNumber;
	private String	VATType;
	private String	visitingAddress;
	private String	visitingCity;
	private String	visitingCountry;
	private String	visitingCountryCode;
	private String	visitingZipCode;
	private String	workPlace;
	private String	www;
	private String	yourReference;
	
	@XmlElement(name="Active")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@XmlElement(name="Bank")
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@XmlElement(name="BranchCode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	@XmlElement(name="ClearingNumber")
	public String getClearingNumber() {
		return clearingNumber;
	}
	public void setClearingNumber(String clearingNumber) {
		this.clearingNumber = clearingNumber;
	}
	
	@XmlElement(name="Comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@XmlElement(name="Fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@XmlElement(name="OurReference")
	public String getOurReference() {
		return ourReference;
	}
	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
	}
	
	@XmlElement(name="OurCustomerNumber")
	public String getOurCustomerNumber() {
		return ourCustomerNumber;
	}
	public void setOurCustomerNumber(String ourCustomerNumber) {
		this.ourCustomerNumber = ourCustomerNumber;
	}
	
	@XmlElement(name="Phone1")
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	@XmlElement(name="Phone2")
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@XmlElement(name="VATNumber")
	public String getVATNumber() {
		return VATNumber;
	}
	public void setVATNumber(String vATNumber) {
		VATNumber = vATNumber;
	}
	
	@XmlElement(name="VATType")
	public String getVATType() {
		return VATType;
	}
	public void setVATType(String vATType) {
		VATType = vATType;
	}
	
	@XmlElement(name="VisitingAddress")
	public String getVisitingAddress() {
		return visitingAddress;
	}
	public void setVisitingAddress(String visitingAddress) {
		this.visitingAddress = visitingAddress;
	}
	
	@XmlElement(name="VisitingCity")
	public String getVisitingCity() {
		return visitingCity;
	}
	public void setVisitingCity(String visitingCity) {
		this.visitingCity = visitingCity;
	}
	
	@XmlElement(name="VisitingCountry")
	public String getVisitingCountry() {
		return visitingCountry;
	}
	public void setVisitingCountry(String visitingCountry) {
		this.visitingCountry = visitingCountry;
	}
	
	@XmlElement(name="VisitingCountryCode")
	public String getVisitingCountryCode() {
		return visitingCountryCode;
	}
	public void setVisitingCountryCode(String visitingCountryCode) {
		this.visitingCountryCode = visitingCountryCode;
	}
	
	@XmlElement(name="VisitingZipCode")
	public String getVisitingZipCode() {
		return visitingZipCode;
	}
	public void setVisitingZipCode(String visitingZipCode) {
		this.visitingZipCode = visitingZipCode;
	}
	
	@XmlElement(name="WorkPlace")
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	
	@XmlElement(name="WWW")
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	
	@XmlElement(name="YourReference")
	public String getYourReference() {
		return yourReference;
	}
	public void setYourReference(String yourReference) {
		this.yourReference = yourReference;
	}
	
	
}
