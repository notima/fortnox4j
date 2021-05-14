package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class InvoiceRow {

    private String accountNumber;
    private String articleNumber;
    private Double contributionPercent;
    private Double contributionValue;
    private String costCenter;
    private Double deliveredQuantity;
    private String description;
    private Double discount;
    private String discountType; // Possible value = PERCENT
    private Boolean houseWork;
    private Double price;
    private String project;
    private Double total;
    private String unit;
    private Double vat;
    
    @XmlElement(name="AccountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
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
	@XmlElement(name="CostCenter")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	@XmlElement(name="DeliveredQuantity")
	public Double getDeliveredQuantity() {
		return deliveredQuantity;
	}
	public void setDeliveredQuantity(Double deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name="Discount")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@XmlElement(name="DiscountType")
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	@XmlElement(name="HouseWork")
	public Boolean isHouseWork() {
		return houseWork;
	}
	public void setHouseWork(Boolean houseWork) {
		this.houseWork = houseWork;
	}
	@XmlElement(name="Price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@XmlElement(name="Project")
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	@XmlElement(name="Total")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@XmlElement(name="Unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@XmlElement(name="VAT")
	public Double getVAT() {
		return vat;
	}
	public void setVAT(Double vAT) {
		this.vat = vAT;
	}

	/**
	 * 
	 * @return		True if this line has an appropriate description.
	 */
	public boolean hasDescription() {
		if ((articleNumber==null || articleNumber.trim().length()==0) &&
			(description==null || description.trim().length()==0) &&
			deliveredQuantity!=0D) {
			return false;
		} else {
			return true;
		}
	}
	
}
