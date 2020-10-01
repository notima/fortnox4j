package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;

public class TermsOfPaymentSubset {

	private String code;
	private String description;

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

}
