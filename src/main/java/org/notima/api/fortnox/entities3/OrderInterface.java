package org.notima.api.fortnox.entities3;

/**
 * Abstraction of invoice to be able to treat Invoice and InvoiceSubset equally.
 * 
 * @author Daniel Tamm
 *
 */
public interface OrderInterface {

	public String getDocumentNumber();
	
	public String getOrderDate();
	
}
