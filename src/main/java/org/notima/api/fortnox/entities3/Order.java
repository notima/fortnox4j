package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Order")
public class Order {

	private double administrationFee;
	private double administrationFeeVat;
	private String	address1;
	private String	address2;
	private double basisTaxReduction;
	private boolean	cancelled;
	private String	city;
	private String	comments;
	private double 	contributionPercent;
	private double	contributionValue;
	private boolean	copyRemarks;
	private String	costCenter;
	private String	currency;
	private double	currencyRate;
	private double	currencyUnit;
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
	private double 	freight;
	private double	freightVAT;
	private double	gross;
	private boolean	houseWork;
	private String	invoiceReference;
	private String	language;
	private double	net;
	private boolean	notCompleted;
	private int		offerReference;
	private String	orderDate;
	private OrderRows	orderRows;
	private String	organisationNumber;
	private String	ourReference;
	private String	phone1;
	private String	phone2;
	private String	priceList;
	private String	printTemplate;
	private int		project;
	private String	remarks;
	private double	roundOff;
	private boolean	sent;
	private String	taxReduction;
	private String	termsOfDelivery;
	private String	termsOfPayment;
	private double	total;
	private double	totalToPay;
	private double	totalVAT;
	private boolean	VATIncluded;
	private String	WayOfDelivery;
	private String	yourReference;
	private String	yourOrderNumber;
	private String	zipCode;

	@XmlElement(name="AdministrationFee")
	public double getAdministrationFee() {
		return administrationFee;
	}
	public void setAdministrationFee(double administrationFee) {
		this.administrationFee = administrationFee;
	}
	
	@XmlElement(name="AdministrationFeeVAT")
	public double getAdministrationFeeVat() {
		return administrationFeeVat;
	}
	public void setAdministrationFeeVat(double administrationFeeVat) {
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
	public double getBasisTaxReduction() {
		return basisTaxReduction;
	}
	public void setBasisTaxReduction(double basisTaxReduction) {
		this.basisTaxReduction = basisTaxReduction;
	}
	
	@XmlElement(name="Cancelled")
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
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
	public double getContributionPercent() {
		return contributionPercent;
	}
	public void setContributionPercent(double contributionPercent) {
		this.contributionPercent = contributionPercent;
	}
	
	@XmlElement(name="ContributionValue")
	public double getContributionValue() {
		return contributionValue;
	}
	public void setContributionValue(double contributionValue) {
		this.contributionValue = contributionValue;
	}
	
	@XmlElement(name="CopyRemarks")
	public boolean isCopyRemarks() {
		return copyRemarks;
	}
	public void setCopyRemarks(boolean copyRemarks) {
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
	public double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(double currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	@XmlElement(name="CurrencyUnit")
	public double getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(double currencyUnit) {
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
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	
	@XmlElement(name="FreightVAT")
	public double getFreightVAT() {
		return freightVAT;
	}
	public void setFreightVAT(double freightVAT) {
		this.freightVAT = freightVAT;
	}
	
	@XmlElement(name="Gross")
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	
	@XmlElement(name="HouseWork")
	public boolean isHouseWork() {
		return houseWork;
	}
	public void setHouseWork(boolean houseWork) {
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
	public double getNet() {
		return net;
	}
	public void setNet(double net) {
		this.net = net;
	}
	
	@XmlElement(name="NotCompleted")
	public boolean isNotCompleted() {
		return notCompleted;
	}
	public void setNotCompleted(boolean notCompleted) {
		this.notCompleted = notCompleted;
	}
	
	@XmlElement(name="OfferReference")
	public int getOfferReference() {
		return offerReference;
	}
	public void setOfferReference(int offerReference) {
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
	public int getProject() {
		return project;
	}
	public void setProject(int project) {
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
	public double getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(double roundOff) {
		this.roundOff = roundOff;
	}
	
	@XmlElement(name="Sent")
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	@XmlElement(name="TotalToPay")
	public double getTotalToPay() {
		return totalToPay;
	}
	public void setTotalToPay(double totalToPay) {
		this.totalToPay = totalToPay;
	}
	
	@XmlElement(name="TotalVAT")
	public double getTotalVAT() {
		return totalVAT;
	}
	public void setTotalVAT(double totalVAT) {
		this.totalVAT = totalVAT;
	}
	
	@XmlElement(name="VATIncluded")
	public boolean isVATIncluded() {
		return VATIncluded;
	}
	public void setVATIncluded(boolean vATIncluded) {
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
