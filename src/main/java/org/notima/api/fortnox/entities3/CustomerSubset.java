package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class CustomerSubset {

	private String city;
	private String customerNumber;
	private String email;
	private String name;
	private String organisationNumber;
	private String phone;
	private String zipCode;
	
	public CustomerSubset() {}
	
	/**
	 * Create a customer subset from a customer
	 * 
	 * @param src
	 */
	public CustomerSubset(Customer src) {
		if (src==null) return;
		city = src.getCity();
		customerNumber = src.getCustomerNumber();
		email = src.getEmail();
		name = src.getName();
		organisationNumber = src.getOrganisationNumber();
		phone = src.getPhone1();
		zipCode = src.getZipCode();
	}
	
	@XmlElement(name="City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement(name="CustomerNumber")
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	@XmlElement(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@XmlElement(name="ZipCode")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}
