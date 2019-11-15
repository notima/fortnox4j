package org.notima.api.fortnox.entities3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class InvoiceRows {

	private List<InvoiceRow> 	invoiceRow;

	@XmlElement(name="InvoiceRow")
	public List<InvoiceRow> getInvoiceRow() {
		return invoiceRow;
	}

	public void setInvoiceRow(List<InvoiceRow> invoiceRow) {
		this.invoiceRow = invoiceRow;
	}
	
}
