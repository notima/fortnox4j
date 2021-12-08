package org.notima.api.fortnox.entities3;

/**
 * Abstraction of invoice to be able to treat Invoice and InvoiceSubset equally.
 * 
 * @author Daniel Tamm
 *
 */
public interface InvoiceInterface {

	public String getDocumentNumber();
	
	public String getInvoiceDate();
	
	public Double getBalance();
	
}
