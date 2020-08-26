package org.notima.api.fortnox.entities3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="VoucherSeries")
public class VoucherSeries extends VoucherSeriesSubset {

	private Integer nextVoucherNumber;

	@XmlElement(name="NextVoucherNumber")
	public Integer getNextVoucherNumber() {
		return nextVoucherNumber;
	}

	public void setNextVoucherNumber(Integer nextVoucherNumber) {
		this.nextVoucherNumber = nextVoucherNumber;
	}

	
	
}
