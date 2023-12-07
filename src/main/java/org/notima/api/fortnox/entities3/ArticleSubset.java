package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class ArticleSubset implements ArticleInterface {

	private String articleNumber;
	private String description;
	private Double disposableQuantity;
	private String ean;
	private Boolean housework;
	private Double purchasePrice;
	private Double quantityInStock;
	private Double reservedQuantity;
	private Double salesPrice;
	private String stockPlace;
	private Double stockValue;
	private String unit;
	private Double vat;
	private Boolean webshopArticle;

	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="DisposableQuantity")
	public Double getDisposableQuantity() {
		return disposableQuantity;
	}

	public void setDisposableQuantity(Double disposableQuantity) {
		this.disposableQuantity = disposableQuantity;
	}

	@XmlElement(name="EAN")
	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	@XmlElement(name="Housework")
	public Boolean getHousework() {
		return housework;
	}

	public void setHousework(Boolean housework) {
		this.housework = housework;
	}

	@XmlElement(name="PurchasePrice")
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@XmlElement(name="QuantityInStock")
	public Double getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Double quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	@XmlElement(name="ReservedQuantity")
	public Double getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Double reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	@XmlElement(name="SalesPrice")
	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	@XmlElement(name="StockPlace")
	public String getStockPlace() {
		return stockPlace;
	}

	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}

	@XmlElement(name="StockValue")
	public Double getStockValue() {
		return stockValue;
	}

	public void setStockValue(Double stockValue) {
		this.stockValue = stockValue;
	}

	@XmlElement(name="Unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@XmlElement(name="VAT")
	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	@XmlElement(name="WebshopArticle")
	public Boolean getWebshopArticle() {
		return webshopArticle;
	}

	public void setWebshopArticle(Boolean webshopArticle) {
		this.webshopArticle = webshopArticle;
	}

}
