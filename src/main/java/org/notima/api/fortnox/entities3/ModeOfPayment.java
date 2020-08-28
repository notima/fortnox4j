package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ModeOfPayment")
public class ModeOfPayment extends ModeOfPaymentSubset {

	public ModeOfPayment() {}
	
	public ModeOfPayment(ModeOfPaymentSubset ms) {
		setUrl(ms.getUrl());
		setAccountNumber(ms.getAccountNumber());
		setDescription(ms.getDescription());
		setCode(ms.getCode());
	}
	
}
