package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Customer")
public class Customer {

	public static final String TYPE_PRIVATE = "PRIVATE";
	public static final String TYPE_COMPANY = "COMPANY";
	
	public static final String TYPE_VAT_SEVAT = "SEVAT";
	public static final String TYPE_VAT_SEREVERSEDVAT = "SEREVERSEDVAT";
	public static final String TYPE_VAT_EUREVERSEDVAT = "EUREVERSEDVAT";
	public static final String TYPE_VAT_EUVAT = "EUVAT";
	public static final String TYPE_VAT_EXPORT = "EXPORT";
	
	private String address1;
	private String address2;
	private String city;
	private String country;
	private String comments;
	private String currency;
	private String costCenter;
	private String countryCode;
	private String customerNumber;
	private DefaultDeliveryTypes defaultDeliveryTypes;
	private DefaultTemplates defaultTemplates;
	private String deliveryAddress1;
	private String deliveryAddress2;
	private String deliveryCity;
	private String deliveryCountry;
	private String deliveryCountryCode;
	private String deliveryFax;
	private String deliveryName;
	private String deliveryPhone1;
	private String deliveryPhone2;
	private String deliveryZipCode;
	private String email;
	private String emailInvoice;
	private String emailInvoiceBCC;
	private String emailInvoiceCC;
	private String emailOffer;
	private String emailOfferBCC;
	private String emailOfferCC;
	private String emailOrder;
	private String emailOrderBCC;
	private String emailOrderCC;
	private String fax;
	private String invoiceAdministrationFee;
	private String invoiceDiscount;
	private String invoiceFreight;
	private String invoiceRemark;
	private String name;
	private String organisationNumber;
	private String ourReference;
	private String phone1;
	private String phone2;
	private String priceList;
	private String project;
	private String salesAccount;
	private String showPriceVatIncluded;
	private String termsOfDelivery;
	private String termsOfPayment;
	private String type;
	private String vatNumber;
	private String vatType;
	private String visitingAddress;
	private String visitingCity;
	private String visitingCountry;
	private String visitingCountryCode;
	private String visitingZipCode;
	private String www;
	private String wayOfDeliveryCode;
	private String yourReference;
	private String zipCode;

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

	@XmlElement(name="City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name="Comments")
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	@XmlElement(name="Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	@XmlElement(name="CustomerNumber")
	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@XmlElement(name="DefaultDeliveryTypes")
	public DefaultDeliveryTypes getDefaultDeliveryTypes() {
		return defaultDeliveryTypes;
	}

	public void setDefaultDeliveryTypes(
			DefaultDeliveryTypes defaultDeliveryTypes) {
		this.defaultDeliveryTypes = defaultDeliveryTypes;
	}

	@XmlElement(name="DefaultTemplates")
	public DefaultTemplates getDefaultTemplates() {
		return defaultTemplates;
	}

	public void setDefaultTemplates(DefaultTemplates defaultTemplates) {
		this.defaultTemplates = defaultTemplates;
	}

	@XmlElement(name="DeliveryAddress1")
	public String getDeliveryAddress1() {
		return deliveryAddress1;
	}

	public void setDeliveryAddress1(String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	@XmlElement(name="DeliveryAddress2")
	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	@XmlElement(name="DeliveryCity")
	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	@XmlElement(name="DeliveryCountry")
	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	public void setDeliveryCountry(String deliveryCountry) {
		this.deliveryCountry = deliveryCountry;
	}

	@XmlElement(name="DeliveryCountryCode")
	public String getDeliveryCountryCode() {
		return deliveryCountryCode;
	}

	public void setDeliveryCountryCode(String deliveryCountryCode) {
		this.deliveryCountryCode = deliveryCountryCode;
	}

	@XmlElement(name="DeliveryFax")
	public String getDeliveryFax() {
		return deliveryFax;
	}

	public void setDeliveryFax(String deliveryFax) {
		this.deliveryFax = deliveryFax;
	}

	@XmlElement(name="DeliveryName")
	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	@XmlElement(name="DeliveryPhone1")
	public String getDeliveryPhone1() {
		return deliveryPhone1;
	}

	public void setDeliveryPhone1(String deliveryPhone1) {
		this.deliveryPhone1 = deliveryPhone1;
	}

	@XmlElement(name="DeliveryPhone2")
	public String getDeliveryPhone2() {
		return deliveryPhone2;
	}

	public void setDeliveryPhone2(String deliveryPhone2) {
		this.deliveryPhone2 = deliveryPhone2;
	}

	@XmlElement(name="DeliveryZipCode")
	public String getDeliveryZipCode() {
		return deliveryZipCode;
	}

	public void setDeliveryZipCode(String deliveryZipCode) {
		this.deliveryZipCode = deliveryZipCode;
	}

	@XmlElement(name="Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name="EmailInvoice")
	public String getEmailInvoice() {
		return emailInvoice;
	}

	public void setEmailInvoice(String emailInvoice) {
		this.emailInvoice = emailInvoice;
	}

	@XmlElement(name="EmailInvoiceBCC")
	public String getEmailInvoiceBCC() {
		return emailInvoiceBCC;
	}

	public void setEmailInvoiceBCC(String emailInvoiceBCC) {
		this.emailInvoiceBCC = emailInvoiceBCC;
	}

	@XmlElement(name="EmailInvoiceCC")
	public String getEmailInvoiceCC() {
		return emailInvoiceCC;
	}

	public void setEmailInvoiceCC(String emailInvoiceCC) {
		this.emailInvoiceCC = emailInvoiceCC;
	}

	@XmlElement(name="EmailOffer")
	public String getEmailOffer() {
		return emailOffer;
	}

	public void setEmailOffer(String emailOffer) {
		this.emailOffer = emailOffer;
	}

	@XmlElement(name="EmailOfferBCC")
	public String getEmailOfferBCC() {
		return emailOfferBCC;
	}

	public void setEmailOfferBCC(String emailOfferBCC) {
		this.emailOfferBCC = emailOfferBCC;
	}

	@XmlElement(name="EmailOfferCC")
	public String getEmailOfferCC() {
		return emailOfferCC;
	}

	public void setEmailOfferCC(String emailOfferCC) {
		this.emailOfferCC = emailOfferCC;
	}

	@XmlElement(name="EmailOrder")
	public String getEmailOrder() {
		return emailOrder;
	}

	public void setEmailOrder(String emailOrder) {
		this.emailOrder = emailOrder;
	}

	@XmlElement(name="EmailOrderBCC")
	public String getEmailOrderBCC() {
		return emailOrderBCC;
	}

	public void setEmailOrderBCC(String emailOrderBCC) {
		this.emailOrderBCC = emailOrderBCC;
	}

	@XmlElement(name="EmailOrderCC")
	public String getEmailOrderCC() {
		return emailOrderCC;
	}

	public void setEmailOrderCC(String emailOrderCC) {
		this.emailOrderCC = emailOrderCC;
	}

	@XmlElement(name="Fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@XmlElement(name="InvoiceAdministrationFee")
	public String getInvoiceAdministrationFee() {
		return invoiceAdministrationFee;
	}

	public void setInvoiceAdministrationFee(String invoiceAdministrationFee) {
		this.invoiceAdministrationFee = invoiceAdministrationFee;
	}

	@XmlElement(name="InvoiceDiscount")
	public String getInvoiceDiscount() {
		return invoiceDiscount;
	}

	public void setInvoiceDiscount(String invoiceDiscount) {
		this.invoiceDiscount = invoiceDiscount;
	}

	@XmlElement(name="InvoiceFreight")
	public String getInvoiceFreight() {
		return invoiceFreight;
	}

	public void setInvoiceFreight(String invoiceFreight) {
		this.invoiceFreight = invoiceFreight;
	}

	@XmlElement(name="InvoiceRemark")
	public String getInvoiceRemark() {
		return invoiceRemark;
	}

	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
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

	@XmlElement(name="OurReference")
	public String getOurReference() {
		return ourReference;
	}

	public void setOurReference(String ourReference) {
		this.ourReference = ourReference;
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

	@XmlElement(name="PriceList")
	public String getPriceList() {
		return priceList;
	}

	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@XmlElement(name="SalesAccount")
	public String getSalesAccount() {
		return salesAccount;
	}

	public void setSalesAccount(String salesAccount) {
		this.salesAccount = salesAccount;
	}

	@XmlElement(name="ShowPriceVatIncluded")
	public String getShowPriceVatIncluded() {
		return showPriceVatIncluded;
	}

	public void setShowPriceVatIncluded(String showPriceVatIncluded) {
		this.showPriceVatIncluded = showPriceVatIncluded;
	}

	@XmlElement(name="TermsOfDelivery")
	public String getTermsOfDelivery() {
		return termsOfDelivery;
	}

	public void setTermsOfDelivery(String termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}

	@XmlElement(name="TermsOfPayment")
	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	@XmlElement(name="Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="VATNumber")
	public String getVATNumber() {
		return vatNumber;
	}

	public void setVATNumber(String vATNumber) {
		this.vatNumber = vATNumber;
	}

	@XmlElement(name="VATType")
	public String getVATType() {
		return vatType;
	}

	public void setVATType(String vATType) {
		this.vatType = vATType;
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

	@XmlElement(name="WWW")
	public String getWWW() {
		return www;
	}

	public void setWWW(String wWW) {
		this.www = wWW;
	}

	@XmlElement(name="WayOfDeliveryCode")
	public String getWayOfDeliveryCode() {
		return wayOfDeliveryCode;
	}

	public void setWayOfDeliveryCode(String wayOfDeliveryCode) {
		this.wayOfDeliveryCode = wayOfDeliveryCode;
	}

	@XmlElement(name="YourReference")	
	public String getYourReference() {
		return yourReference;
	}

	public void setYourReference(String yourReference) {
		this.yourReference = yourReference;
	}

	@XmlElement(name="ZipCode")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
