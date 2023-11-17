package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.CustomerSubset;
import org.notima.api.fortnox.entities3.Customers;

public class TestGetCustomers extends FortnoxTest {
	
	
	@Test
	public void testGetCustomers() throws Exception {

		Customers customers = client.getCustomersActiveOnly();
		
		for (CustomerSubset customer : customers.getCustomerSubset()) {
			System.out.println(customer.getName());
		}
		
	}
	

}
