package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "CompanySettings")
public class CompanySetting {
	private String adress;
	private String bg;
	private String bic;
	private String branchCode;
	private String city;
	private String contactFirstName;
	private String contactLastName;
	private String country;
	private String countryCode;
	private String databaseNumber;
	private String domicile;
	private String email;
	private String fax;
	private String iBAN;
	private String name;
	private String organizationNumber;
	private String pg;
	private String phone1;
	private String phone2;
	private boolean taxEnabled;
	private String vatNumber;
	private String visitAddress;
	private String visitCity;
	private String visitCountry;
	private String visitCountryCode;
	private String visitName;
	private String visitZipCode;
	private String www;
	private String zipCode;
	
	@XmlElement(name="Address")
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	@XmlElement(name="BG")
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	@XmlElement(name="BIC")
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	@XmlElement(name="BranchCode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@XmlElement(name="City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement(name="ContactFirstName")
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	@XmlElement(name="ContactLastName")
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@XmlElement(name="CountryCode")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@XmlElement(name="DatabaseNumber")
	public String getDatabaseNumber() {
		return databaseNumber;
	}
	public void setDatabaseNumber(String databaseNumber) {
		this.databaseNumber = databaseNumber;
	}
	@XmlElement(name="Domicile")
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	@XmlElement(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement(name="Fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@XmlElement(name="IBAN")
	public String getiBAN() {
		return iBAN;
	}
	public void setiBAN(String iBAN) {
		this.iBAN = iBAN;
	}
	
	@XmlTransient
	public boolean hasName() {
		return name!=null && name.trim().length()>0;
	}
	
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlTransient
	public boolean hasOrganizationNumber() {
		return organizationNumber!=null && organizationNumber.trim().length()>0;
	}
	
	@XmlElement(name="OrganizationNumber")
	public String getOrganizationNumber() {
		return organizationNumber;
	}
	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}
	@XmlElement(name="PG")
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
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
	@XmlElement(name="TaxEnabled")
	public boolean isTaxEnabled() {
		return taxEnabled;
	}
	public void setTaxEnabled(boolean taxEnabled) {
		this.taxEnabled = taxEnabled;
	}
	@XmlElement(name="VATNumber")
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	@XmlElement(name="VisitAddress")
	public String getVisitAddress() {
		return visitAddress;
	}
	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}
	@XmlElement(name="VisitCity")
	public String getVisitCity() {
		return visitCity;
	}
	public void setVisitCity(String visitCity) {
		this.visitCity = visitCity;
	}
	@XmlElement(name="VisitCountry")
	public String getVisitCountry() {
		return visitCountry;
	}
	public void setVisitCountry(String visitCountry) {
		this.visitCountry = visitCountry;
	}
	@XmlElement(name="VisitCountryCode")
	public String getVisitCountryCOde() {
		return visitCountryCode;
	}
	public void setVisitCountryCOde(String visitCountryCOde) {
		this.visitCountryCode = visitCountryCOde;
	}
	@XmlElement(name="VisitName")
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	@XmlElement(name="VisitZipCode")
	public String getVisitZipCode() {
		return visitZipCode;
	}
	public void setVisitZipCode(String visitZipCode) {
		this.visitZipCode = visitZipCode;
	}
	@XmlElement(name="WWW")
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	@XmlElement(name="ZipCode")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	} 
	
	
}
