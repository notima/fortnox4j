package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.entities3.InvoiceSubset;
import org.notima.api.fortnox.entities3.Invoices;

/**
 * Counts the number of unpaid / unbooked invoices.
 * 
 * @author Daniel Tamm
 *
 */
public class TestGetUnpaidUnbookedInvoices extends FortnoxTest {

	String firstInvoice = null;
	
	@Test
	public void testGetUnpaidUnbookedInvoices() {
		
		try {
			int invoiceCount = 0;
			Invoices result = client.getUnpaidAndUnbookedCustomerInvoices();
			for (@SuppressWarnings("unused") InvoiceSubset i : result.getInvoiceSubset()) {
				invoiceCount++;
			}
			log.info("{} invoices unbooked and/or unpaid.", invoiceCount);
		} catch (FortnoxException fe) {
			log.error(fe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}

}
