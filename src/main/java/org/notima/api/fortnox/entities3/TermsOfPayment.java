package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TermsOfPayment")
public class TermsOfPayment extends TermsOfPaymentSubset {

	public TermsOfPayment() {}
	
	public TermsOfPayment(TermsOfPaymentSubset ms) {
		setUrl(ms.getUrl());
		setCode(ms.getCode());
		setDescription(ms.getDescription());
	}
	

}
