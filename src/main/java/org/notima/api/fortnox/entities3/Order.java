package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Order")
public class Order implements OrderInterface {

	private Double administrationFee;
	private Double administrationFeeVat;
	private String	address1;
	private String	address2;
	private Double basisTaxReduction;
	private Boolean	cancelled;
	private String	city;
	private String	comments;
	private Double 	contributionPercent;
	private Double	contributionValue;
	private Boolean	copyRemarks;
	private String	costCenter;
	private String	currency;
	private Double	currencyRate;
	private Double	currencyUnit;
	private String	customerName;
	private String	customerNumber;
	private String	deliveryAddress1;
	private String	deliveryAddress2;
	private String	deliveryCity;
	private String	deliveryCountry;
	private String	deliveryDate;
	private String	deliveryName;
	private String	deliveryZipCode;
	private String	documentNumber;
	private EmailInformation emailInformation;
	private String 	externalInvoiceReference1;
	private String	externalInvoiceReference2;
	private Double 	freight;
	private Double	freightVAT;
	private Double	gross;
	private Boolean	houseWork;
	private String	invoiceReference;
	private String	language;
	private Double	net;
	private Boolean	notCompleted;
	private Integer	offerReference;
	private String	orderDate;
	private OrderRows	orderRows;
	private String	organisationNumber;
	private String	ourReference;
	private String	phone1;
	private String	phone2;
	private String	priceList;
	private String	printTemplate;
	private String	project;
	private String	remarks;
	private Double	roundOff;
	private Boolean	sent;
	private String	taxReduction;
	private String	termsOfDelivery;
	private String	termsOfPayment;
	private Double	total;
	private Double	totalToPay;
	private Double	totalVAT;
	private Boolean	VATIncluded;
	private String	WayOfDelivery;
	private String	yourReference;
	private String	yourOrderNumber;
	private String	zipCode;

	@XmlElement(name="AdministrationFee")
	public Double getAdministrationFee() {
		return administrationFee;
	}
	public void setAdministrationFee(Double administrationFee) {
		this.administrationFee = administrationFee;
	}
	
	@XmlElement(name="AdministrationFeeVAT")
	public Double getAdministrationFeeVat() {
		return administrationFeeVat;
	}
	public void setAdministrationFeeVat(Double administrationFeeVat) {
		this.administrationFeeVat = administrationFeeVat;
	}
	
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
	
	@XmlElement(name="BasisTaxReduction")
	public Double getBasisTaxReduction() {
		return basisTaxReduction;
	}
	public void setBasisTaxReduction(Double basisTaxReduction) {
		this.basisTaxReduction = basisTaxReduction;
	}
	
	@XmlElement(name="Cancelled")
	public Boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@XmlElement(name="City")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name="Comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@XmlElement(name="ContributionPercent")
	public Double getContributionPercent() {
		return contributionPercent;
	}
	public void setContributionPercent(Double contributionPercent) {
		this.contributionPercent = contributionPercent;
	}
	
	@XmlElement(name="ContributionValue")
	public Double getContributionValue() {
		return contributionValue;
	}
	public void setContributionValue(Double contributionValue) {
		this.contributionValue = contributionValue;
	}
	
	@XmlElement(name="CopyRemarks")
	public Boolean isCopyRemarks() {
		return copyRemarks;
	}
	public void setCopyRemarks(Boolean copyRemarks) {
		this.copyRemarks = copyRemarks;
	}
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	
	@XmlElement(name="Currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name="CurrencyRate")
	public Double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	@XmlElement(name="CurrencyUnit")
	public Double getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(Double currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	
	@XmlElement(name="CustomerName")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@XmlElement(name="CustomerNumber")
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
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
	
	@XmlElement(name="DeliveryDate")
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@XmlElement(name="DeliveryName")
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	
	@XmlElement(name="DeliveryZipCode")
	public String getDeliveryZipCode() {
		return deliveryZipCode;
	}
	public void setDeliveryZipCode(String deliveryZipCode) {
		this.deliveryZipCode = deliveryZipCode;
	}
	
	@XmlElement(name="DocumentNumber")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@XmlElement(name="EmailInformation")
	public EmailInformation getEmailInformation() {
		return emailInformation;
	}
	public void setEmailInformation(EmailInformation emailInformation) {
		this.emailInformation = emailInformation;
	}
	
	@XmlElement(name="ExternalInvoiceReference1")
	public String getExternalInvoiceReference1() {
		return externalInvoiceReference1;
	}
	public void setExternalInvoiceReference1(String externalInvoiceReference1) {
		this.externalInvoiceReference1 = externalInvoiceReference1;
	}
	
	@XmlElement(name="ExternalInvoiceReference2")
	public String getExternalInvoiceReference2() {
		return externalInvoiceReference2;
	}
	public void setExternalInvoiceReference2(String externalInvoiceReference2) {
		this.externalInvoiceReference2 = externalInvoiceReference2;
	}
	
	@XmlElement(name="Freight")
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	
	@XmlElement(name="FreightVAT")
	public Double getFreightVAT() {
		return freightVAT;
	}
	public void setFreightVAT(Double freightVAT) {
		this.freightVAT = freightVAT;
	}
	
	@XmlElement(name="Gross")
	public Double getGross() {
		return gross;
	}
	public void setGross(Double gross) {
		this.gross = gross;
	}
	
	@XmlElement(name="HouseWork")
	public Boolean isHouseWork() {
		return houseWork;
	}
	public void setHouseWork(Boolean houseWork) {
		this.houseWork = houseWork;
	}
	
	@XmlElement(name="InvoiceReference")
	public String getInvoiceReference() {
		return invoiceReference;
	}
	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}
	
	@XmlElement(name="Language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@XmlElement(name="Net")
	public Double getNet() {
		return net;
	}
	public void setNet(Double net) {
		this.net = net;
	}
	
	@XmlElement(name="NotCompleted")
	public Boolean isNotCompleted() {
		return notCompleted;
	}
	public void setNotCompleted(Boolean notCompleted) {
		this.notCompleted = notCompleted;
	}
	
	@XmlElement(name="OfferReference")
	public Integer getOfferReference() {
		return offerReference;
	}
	public void setOfferReference(Integer offerReference) {
		this.offerReference = offerReference;
	}
	
	@XmlElement(name="OrderDate")
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	@XmlElement(name="OrderRows")
	public OrderRows getOrderRows() {
		return orderRows;
	}
	public void setOrderRows(OrderRows orderRows) {
		this.orderRows = orderRows;
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
	
	@XmlElement(name="PrintTemplate")
	public String getPrintTemplate() {
		return printTemplate;
	}
	
	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}
	
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	@XmlElement(name="Remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@XmlElement(name="RoundOff")
	public Double getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(Double roundOff) {
		this.roundOff = roundOff;
	}
	
	@XmlElement(name="Sent")
	public Boolean isSent() {
		return sent;
	}
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	
	@XmlElement(name="TaxReduction")
	public String getTaxReduction() {
		return taxReduction;
	}
	public void setTaxReduction(String taxReduction) {
		this.taxReduction = taxReduction;
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
	
	@XmlElement(name="Total")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	@XmlElement(name="TotalToPay")
	public Double getTotalToPay() {
		return totalToPay;
	}
	public void setTotalToPay(Double totalToPay) {
		this.totalToPay = totalToPay;
	}
	
	@XmlElement(name="TotalVAT")
	public Double getTotalVAT() {
		return totalVAT;
	}
	public void setTotalVAT(Double totalVAT) {
		this.totalVAT = totalVAT;
	}
	
	@XmlElement(name="VATIncluded")
	public Boolean isVATIncluded() {
		return VATIncluded;
	}
	public void setVATIncluded(Boolean vATIncluded) {
		VATIncluded = vATIncluded;
	}
	
	@XmlElement(name="WayOfDelivery")
	public String getWayOfDelivery() {
		return WayOfDelivery;
	}
	public void setWayOfDelivery(String wayOfDelivery) {
		WayOfDelivery = wayOfDelivery;
	}
	
	@XmlElement(name="YourReference")
	public String getYourReference() {
		return yourReference;
	}
	public void setYourReference(String yourReference) {
		this.yourReference = yourReference;
	}
	
	@XmlElement(name="YourOrderNumber")
	public String getYourOrderNumber() {
		return yourOrderNumber;
	}
	public void setYourOrderNumber(String yourOrderNumber) {
		this.yourOrderNumber = yourOrderNumber;
	}
	
	@XmlElement(name="ZipCode")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
