package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Price")
public class Price {

	private String articleNumber;
	private String date;
	private Double fromQuantity;
	private Double percent;
	private Double price;
	private String priceList;

	@XmlElement(name="ArticleNumber")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	@XmlElement(name="Date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement(name="FromQuantity")
	public Double getFromQuantity() {
		return fromQuantity;
	}

	public void setFromQuantity(Double fromQuantity) {
		this.fromQuantity = fromQuantity;
	}

	@XmlElement(name="Percent")
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
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
