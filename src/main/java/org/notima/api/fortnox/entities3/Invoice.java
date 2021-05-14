package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.notima.api.fortnox.FortnoxConstants;

@XmlRootElement(name = "Invoice")
public class Invoice {

	private Double administrationFee;
	private Double administrationFeeVAT;
	private String address1;
	private String address2;
	private Double balance;
	private Double basisTaxReduction;
	private Boolean booked;
	private Boolean cancelled;
	private String city;
	private String comments;
	private Double contributionPercent;
	private Double contributionValue;
	private String country;
	private String costCenter;
	private Boolean credit;
	private String creditInvoiceReference;
	private String currency;
	private Double currencyRate;
	private Double currencyUnit;
	private String customerName;
	private String customerNumber;
	private String deliveryAddress1;
	private String deliveryAddress2;
	private String deliveryCity;
	private String deliveryCountry;
	private String deliveryDate;
	private String deliveryName;
	private String deliveryZipCode;
	private String documentNumber;
	private String dueDate;
	private EDIInformation ediInformation;
	private EmailInformation emailInformation;
	private String externalInvoiceReference1;
	private String externalInvoiceReference2;
	private Double freight;
	private Double freightVAT;
	private Double gross;
	private Boolean houseWork;
	private String invoiceDate;
	private String invoicePeriodStart;
	private String invoicePeriodEnd;
	private String invoiceReference;
	private InvoiceRows invoiceRows;
	private String invoiceType; // Possible type = INVOICE
	private String language; // Possible value = SV
	private String lastRemindDate;
	private Double net;
	private Boolean notCompleted;
	private String OCR;
	private String offerReference;
	private String orderReference;
	private String organisationNumber;
	private String ourReference;
	private String phone1;
	private String phone2;
	private String priceList;
	private String printTemplate;
	private String project;
	private String remarks;
	private Integer reminders;
	private Double roundOff;
	private Boolean sent;
	private Double taxReduction;
	private String termsOfDelivery;
	private String termsOfPayment;
	private Double total;
	private Double totalToPay;
	private Double totalVAT;
	private Boolean vatIncluded;
	private String voucherNumber;
	private String voucherSeries;
	private String voucherYear;
	private Boolean warehouseReady;
	private String wayOfDelivery;
	private String yourOrderNumber;
	private String yourReference;
	private String zipCode;

	@XmlElement(name="AdministrationFee")
	public Double getAdministrationFee() {
		return administrationFee;
	}

	public void setAdministrationFee(Double administrationFee) {
		this.administrationFee = administrationFee;
	}

	@XmlElement(name="AdministrationFeeVAT")	
	public Double getAdministrationFeeVAT() {
		return administrationFeeVAT;
	}

	public void setAdministrationFeeVAT(Double administrationFeeVAT) {
		this.administrationFeeVAT = administrationFeeVAT;
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

	@XmlElement(name="Balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@XmlElement(name="BasisTaxReduction")
	public Double getBasisTaxReduction() {
		return basisTaxReduction;
	}

	public void setBasisTaxReduction(Double basisTaxReduction) {
		this.basisTaxReduction = basisTaxReduction;
	}

	@XmlElement(name="Booked")
	public Boolean isBooked() {
		return booked;
	}

	public void setBooked(Boolean booked) {
		this.booked = booked;
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

	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	@XmlElement(name="Credit")	
	public Boolean isCredit() {
		return credit;
	}

	public void setCredit(Boolean credit) {
		this.credit = credit;
	}

	@XmlElement(name="CreditInvoiceReference")	
	public String getCreditInvoiceReference() {
		return creditInvoiceReference;
	}

	public void setCreditInvoiceReference(String creditInvoiceReference) {
		this.creditInvoiceReference = creditInvoiceReference;
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

	@XmlElement(name="DueDate")
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@XmlElement(name="EDIInformation")
	public EDIInformation getEdiInformation() {
		return ediInformation;
	}

	public void setEdiInformation(EDIInformation ediInformation) {
		this.ediInformation = ediInformation;
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

	@XmlElement(name="InvoiceDate")
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@XmlElement(name="InvoicePeriodStart")
	public String getInvoicePeriodStart() {
		return invoicePeriodStart;
	}
	
	@XmlElement(name="InvoicePeriodEnd")
	public String getInvoicePeriodEnd() {
		return invoicePeriodEnd;
	}
	
	public void setInvoicePeriodStart(String invoicePeriodStart) {
		this.invoicePeriodStart = invoicePeriodStart;
	}

	public void setInvoicePeriodEnd(String invoicePeriodEnd) {
		this.invoicePeriodEnd = invoicePeriodEnd;
	}

	@XmlElement(name="InvoiceReference")	
	public String getInvoiceReference() {
		return invoiceReference;
	}

	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}

	@XmlElement(name="InvoiceRows")	
	public InvoiceRows getInvoiceRows() {
		return invoiceRows;
	}

	public void setInvoiceRows(InvoiceRows invoiceRows) {
		this.invoiceRows = invoiceRows;
	}

	@XmlElement(name="InvoiceType")	
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	@XmlElement(name="Language")	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@XmlElement(name="LastRemindDate")	
	public String getLastRemindDate() {
		return lastRemindDate;
	}

	public void setLastRemindDate(String lastRemindDate) {
		this.lastRemindDate = lastRemindDate;
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

	@XmlElement(name="OCR")	
	public String getOCR() {
		return OCR;
	}

	public void setOCR(String oCR) {
		this.OCR = oCR;
	}

	@XmlElement(name="OfferReference")	
	public String getOfferReference() {
		return offerReference;
	}

	public void setOfferReference(String offerReference) {
		this.offerReference = offerReference;
	}

	@XmlElement(name="OrderReference")	
	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
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

	@XmlElement(name="Reminders")	
	public Integer getReminders() {
		return reminders;
	}

	public void setReminders(Integer reminders) {
		this.reminders = reminders;
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
	public Double getTaxReduction() {
		return taxReduction;
	}

	public void setTaxReduction(Double taxReduction) {
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
		return vatIncluded;
	}

	public void setVATIncluded(Boolean vATIncluded) {
		this.vatIncluded = vATIncluded;
	}

	@XmlElement(name="VoucherNumber")	
	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	@XmlElement(name="VoucherSeries")	
	public String getVoucherSeries() {
		return voucherSeries;
	}

	public void setVoucherSeries(String voucherSeries) {
		this.voucherSeries = voucherSeries;
	}

	@XmlElement(name="VoucherYear")	
	public String getVoucherYear() {
		return voucherYear;
	}

	public void setVoucherYear(String voucherYear) {
		this.voucherYear = voucherYear;
	}

	@XmlElement(name="WayOfDelivery")	
	public String getWayOfDelivery() {
		return wayOfDelivery;
	}

	public void setWayOfDelivery(String wayOfDelivery) {
		this.wayOfDelivery = wayOfDelivery;
	}
	
	@XmlElement(name="WarehouseReady")
	public Boolean getWarehouseReady() {
		return warehouseReady;
	}

	public void setWarehouseReady(Boolean warehouseReady) {
		this.warehouseReady = warehouseReady;
	}

	@XmlElement(name="YourOrderNumber")	
	public String getYourOrderNumber() {
		return yourOrderNumber;
	}

	public void setYourOrderNumber(String yourOrderNumber) {
		this.yourOrderNumber = yourOrderNumber;
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

	/**
	 * Makes sure that lines are valid and adjust if necessary.
	 * 
	 * Current validation check are only if description is missing. If description is missing, the
	 * description is set to a period ".".
	 * 
	 * A missing description means that there's quantity but no article or description text.
	 * 
	 * @return		The number of lines fixed
	 */
	public int fixInvoiceLines() {
		
		if (invoiceRows==null || invoiceRows.getInvoiceRow()==null || invoiceRows.getInvoiceRow().size()==0) {
			return 0;
		}
		
		int fixCount = 0;
		
		for (InvoiceRow ir : invoiceRows.getInvoiceRow()) {
			if (!ir.hasDescription()) {
				ir.setDescription("."); // Set empty line
				fixCount++;
			}
		}
		
		return fixCount;
		
	}
	
	/**
	 * Gets the reference in given field name and returns the reference.
	 * 
	 * @param referenceField		Any valid fields in FortnoxConstants.
	 * @return				The reference if any.
	 */
	public String fetchRefInfield(String referenceField) {
		
		String refInFortnox = null;
		
		if (FortnoxConstants.YOURORDERNUMBER.equalsIgnoreCase(referenceField)) {
			refInFortnox = getYourOrderNumber();
		} else if (FortnoxConstants.EXTREF1.equalsIgnoreCase(referenceField)) {
			refInFortnox = getExternalInvoiceReference1();
		} else if (FortnoxConstants.EXTREF2.equalsIgnoreCase(referenceField)) {
			refInFortnox = getExternalInvoiceReference2();
		} else if (FortnoxConstants.INVOICEREF.equalsIgnoreCase(referenceField)) {
			refInFortnox = getInvoiceReference();
		} else if (FortnoxConstants.OCR.equalsIgnoreCase(referenceField)) {
			refInFortnox = getOCR();
		} else if (FortnoxConstants.ORDERREF.equalsIgnoreCase(referenceField)) {
			refInFortnox = getOrderReference();
		} else if (FortnoxConstants.OURREF.equalsIgnoreCase(referenceField)) {
			refInFortnox = getOurReference();
		} else if (FortnoxConstants.YOURREF.equalsIgnoreCase(referenceField)) {
			refInFortnox = getYourReference();
		}
		
		return refInFortnox;
	}
	
}
