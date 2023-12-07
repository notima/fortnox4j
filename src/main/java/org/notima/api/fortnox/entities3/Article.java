package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Article")
public class Article implements ArticleInterface {

	public static final String TYPE_STOCK = "STOCK";
	public static final String TYPE_SERVICE = "SERVICE";

	public static final String TYPE_HOUSEWORK_CONSTRUCTION = "CONSTRUCTION";
	public static final String TYPE_HOUSEWORK_ELECTRICITY = "ELECTRICITY";
	public static final String TYPE_HOUSEWORK_GLASSMETALWORK = "GLASSMETALWORK";
	public static final String TYPE_HOUSEWORK_GROUNDDRAINAGEWORK = "GROUNDDRAINAGEWORK";
	public static final String TYPE_HOUSEWORK_MASONRY = "MASONRY";
	public static final String TYPE_HOUSEWORK_PAINTINGWALLPAPERING = "PAINTINGWALLPAPERING";
	public static final String TYPE_HOUSEWORK_HVAC = "HVAC";
	public static final String TYPE_HOUSEWORK_MAJORAPPLIANCEREPAIR = "MAJORAPPLIANCEREPAIR";
	public static final String TYPE_HOUSEWORK_MOVINGSERVICES = "MOVINGSERVICES";
	public static final String TYPE_HOUSEWORK_ITSERVICES = "ITSERVICES";
	public static final String TYPE_HOUSEWORK_CLEANING = "CLEANING";
	public static final String TYPE_HOUSEWORK_TEXTILECLOTHING = "TEXTILECLOTHING";
	public static final String TYPE_HOUSEWORK_SNOWP = "SNOWP";
	public static final String TYPE_HOUSEWORK_GARDENING = "GARDENING";
	public static final String TYPE_HOUSEWORK_BABYSITTING = "BABYSITTING";
	public static final String TYPE_HOUSEWORK_OTHERCARE = "OTHERCARE";
	public static final String TYPE_HOUSEWORK_OTHERCOSTS = "OTHERCOSTS";

	private String articleNumber;
	private Boolean bulky;
	private Integer constructionAccount;
	private Integer depth;
	private String description;
	private Double disposableQuantity;
	private String ean;
	private Integer euAccount;
	private Integer euVatAccount;
	private Integer exportAccount;
	private Integer height;
	private Boolean housework;
	private String houseworkType;
	private Boolean active;
	private String manufacturer;
	private String manufacturerArticleNumber;
	private String note;
	private Integer purchaseAccount;
	private Double purchasePrice;
	private Double quantityInStock;
	private Double reservedQuantity;
	private Integer salesAccount;
	private Double salesPrice;
	private Boolean stockGoods;
	private String stockPlace;
	private Double stockValue;
	private Double stockWarning;
	private String supplierName;
	private String supplierNumber;
	private String type;
	private String unit;
	private Double vat;
	private Boolean webshopArticle;
	private Double weight;
	private Integer width;
	private Boolean expired;

	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	@XmlElement(name="Bulky")
	public Boolean getBulky() {
		return bulky;
	}

	public void setBulky(Boolean bulky) {
		this.bulky = bulky;
	}

	@XmlElement(name="ConstructionAccount")
	public Integer getConstructionAccount() {
		return constructionAccount;
	}

	public void setConstructionAccount(Integer constructionAccount) {
		this.constructionAccount = constructionAccount;
	}

	@XmlElement(name="Depth")
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
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

	@XmlElement(name="EUAccount")
	public Integer getEuAccount() {
		return euAccount;
	}

	public void setEuAccount(Integer euAccount) {
		this.euAccount = euAccount;
	}

	@XmlElement(name="EUVATAccount")
	public Integer getEuVatAccount() {
		return euVatAccount;
	}

	public void setEuVatAccount(Integer euVatAccount) {
		this.euVatAccount = euVatAccount;
	}

	@XmlElement(name="ExportAccount")
	public Integer getExportAccount() {
		return exportAccount;
	}

	public void setExportAccount(Integer exportAccount) {
		this.exportAccount = exportAccount;
	}

	@XmlElement(name="Height")
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@XmlElement(name="Housework")
	public Boolean getHousework() {
		return housework;
	}

	public void setHousework(Boolean housework) {
		this.housework = housework;
	}

	@XmlElement(name="HouseworkType")
	public String getHouseworkType() {
		return houseworkType;
	}

	public void setHouseworkType(String houseworkType) {
		this.houseworkType = houseworkType;
	}

	@XmlElement(name="Active")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@XmlElement(name="Manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@XmlElement(name="ManufacturerArticleNumber")
	public String getManufacturerArticleNumber() {
		return manufacturerArticleNumber;
	}

	public void setManufacturerArticleNumber(String manufacturerArticleNumber) {
		this.manufacturerArticleNumber = manufacturerArticleNumber;
	}

	@XmlElement(name="Note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@XmlElement(name="PurchaseAccount")
	public Integer getPurchaseAccount() {
		return purchaseAccount;
	}

	public void setPurchaseAccount(Integer purchaseAccount) {
		this.purchaseAccount = purchaseAccount;
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

	@XmlElement(name="SalesAccount")
	public Integer getSalesAccount() {
		return salesAccount;
	}

	public void setSalesAccount(Integer salesAccount) {
		this.salesAccount = salesAccount;
	}

	@XmlElement(name="SalesPrice")
	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	@XmlElement(name="StockGoods")
	public Boolean getStockGoods() {
		return stockGoods;
	}

	public void setStockGoods(Boolean stockGoods) {
		this.stockGoods = stockGoods;
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

	@XmlElement(name="StockWarning")
	public Double getStockWarning() {
		return stockWarning;
	}

	public void setStockWarning(Double stockWarning) {
		this.stockWarning = stockWarning;
	}

	@XmlElement(name="SupplierName")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@XmlElement(name="SupplierNumber")
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	@XmlElement(name="Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@XmlElement(name="Weight")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@XmlElement(name="Width")
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@XmlElement(name="Expired")
	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
}
