package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class AccountSubset {

	private String 	url;
	
	private Boolean active;
	private String	description;
	private Integer	number;
	private String 	SRU;
	private Integer	year;
	private String	VATCode;
	
	@XmlAttribute(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name="Active")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="Number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@XmlElement(name="SRU")
	public String getSRU() {
		return SRU;
	}
	public void setSRU(String sRU) {
		SRU = sRU;
	}
	
	@XmlElement(name="Year")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@XmlElement(name="VATCode")
	public String getVATCode() {
		return VATCode;
	}
	public void setVATCode(String vATCode) {
		VATCode = vATCode;
	}
	
	
	
}
