package org.notima.api.fortnox.entities3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SupplierInvoiceRows {

	private List<SupplierInvoiceRow> 	supplierInvoiceRow;

	@XmlElement(name="SupplierInvoiceRow")
	public List<SupplierInvoiceRow> getSupplierInvoiceRow() {
		return supplierInvoiceRow;
	}

	public void setSupplierInvoiceRow(List<SupplierInvoiceRow> supplierInvoiceRow) {
		this.supplierInvoiceRow = supplierInvoiceRow;
	}
	
}
