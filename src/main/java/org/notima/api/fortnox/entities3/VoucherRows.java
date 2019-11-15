package org.notima.api.fortnox.entities3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class VoucherRows {

	private List<VoucherRow> 	voucherRow;

	@XmlElement(name="VoucherRow")
	public List<VoucherRow> getVoucherRow() {
		return voucherRow;
	}

	public void setVoucherRow(List<VoucherRow> voucherRow) {
		this.voucherRow = voucherRow;
	}
	
	
}
