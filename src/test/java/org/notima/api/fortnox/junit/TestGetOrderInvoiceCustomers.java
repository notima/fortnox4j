package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.entities3.CustomerSubset;
import org.notima.api.fortnox.entities3.Customers;
import org.notima.api.fortnox.entities3.Invoice;
import org.notima.api.fortnox.entities3.InvoiceRow;
import org.notima.api.fortnox.entities3.InvoiceRows;
import org.notima.api.fortnox.entities3.InvoiceSubset;
import org.notima.api.fortnox.entities3.Invoices;
import org.notima.api.fortnox.entities3.OrderSubset;
import org.notima.api.fortnox.entities3.Orders;

public class TestGetOrderInvoiceCustomers extends FortnoxTest {

	String firstInvoice = null;
	
	@Test
	public void testGetInvoices() {
		
		try {
			int invoiceCount = 0;
			Invoices result = client.getInvoices(null);
			for (InvoiceSubset i : result.getInvoiceSubset()) {
				log.debug("ID: " + i.getDocumentNumber() + " Customer: " + i.getCustomerName() + "  Expiredate: " + i.getDueDate() );
				invoiceCount++;
				if (firstInvoice==null) {
					firstInvoice = i.getDocumentNumber();
				}
			}
			log.info("{} invoices retrieved.", invoiceCount);
		} catch (FortnoxException fe) {
			log.error(fe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {

			if (firstInvoice!=null) {
				Invoice inv;
					inv = client.getInvoice(firstInvoice);
				InvoiceRows ir = inv.getInvoiceRows();
				List<InvoiceRow> rows = ir.getInvoiceRow();
				System.out.println(inv.getTotal());
				System.out.println(inv.getNet());
				for (InvoiceRow r : rows) {
					log.debug("  " + r.getArticleNumber() + " " + r.getDescription() + " " + r.getTotal());
				}
				log.info("Invoice rows for invoice {} successfully retrieved", firstInvoice);
			} else {
				log.warn("No invoice available to fetch.");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	
	@Test
	public void testGetOrders() throws Exception {
		Orders orders = client.getOrders(FortnoxClient3.FILTER_INVOICENOTCREATED);
		int orderCount = 0;
		if (orders!=null) {
			for (OrderSubset os : orders.getOrderSubset()) {
				log.debug("Order: " + os.getDocumentNumber());
				orderCount++;
			}
			log.info("{} not invoiced orders retrieved.", orderCount);
		}
	}
	
	@Test
	public void testGetCustomers() {
		
		try {
			int customerCount = 0;
			Customers customers = client.getCustomers();
			for (CustomerSubset c : customers.getCustomerSubset()) {
				log.debug("ID: " + c.getCustomerNumber() + " : " + c.getName());
				customerCount++;
			}
			log.info("{} customers retrieved.", customerCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
