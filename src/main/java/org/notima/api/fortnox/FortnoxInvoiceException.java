package org.notima.api.fortnox;

import org.notima.api.fortnox.entities3.ErrorInformation;

public class FortnoxInvoiceException extends FortnoxException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2887262799066246605L;
	
	private String	invoiceNo;
	
	public FortnoxInvoiceException(ErrorInformation e, String invoiceNo) {
		super(e);
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String toString() {
		String tmp = super.toString();
		return tmp + (invoiceNo!=null ? " : Invoice # " + invoiceNo : "");
	}

}
