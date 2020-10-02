package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class TermsOfDeliveries {

	private List<TermsOfDeliverySubset> subset;

	@XmlElement(name="TermsOfDeliverySubset")
	public List<TermsOfDeliverySubset> getTermsOfDeliverySubset() {
		return subset;
	}

	public void setTermsOfDeliverySubset(List<TermsOfDeliverySubset> subset) {
		this.subset = subset;
	}

}
