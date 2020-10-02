package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class PriceListSubset {

	private String code;
	private String description;
	private String comment;
	private Boolean preSelected;

	@XmlElement(name="Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="Comments")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@XmlElement(name="PreSelected")
	public Boolean getPreSelected() {
		return preSelected;
	}

	public void setPreSelected(Boolean preSelected) {
		this.preSelected = preSelected;
	}

}
