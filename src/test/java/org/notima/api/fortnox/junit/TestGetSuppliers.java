package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.notima.api.fortnox.entities3.SupplierSubset;
import org.notima.api.fortnox.entities3.Suppliers;

public class TestGetSuppliers extends FortnoxTest {
	
	@Test
	public void testGetSuppliers() {
		
		try {
			
			int supplierCount = 0;
			Suppliers suppliers = client.getSuppliers();
			for (SupplierSubset c : suppliers.getSupplierSubset()) {
				log.debug("ID: " + c.getSupplierNumber() + " : " + c.getName());
				supplierCount++;
			}
			log.info("{} suppliers retrieved.", supplierCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
