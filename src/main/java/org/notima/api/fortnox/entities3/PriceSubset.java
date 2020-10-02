package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class PriceSubset {

	private String articleNumber;
	private Double fromQuantity;
	private Double price;
	private String priceList;

	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	@XmlElement(name="FromQuantity")
	public Double getFromQuantity() {
		return fromQuantity;
	}

	public void setFromQuantity(Double fromQuantity) {
		this.fromQuantity = fromQuantity;
	}

	@XmlElement(name="Price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@XmlElement(name="PriceList")
	public String getPriceList() {
		return priceList;
	}

	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

}

