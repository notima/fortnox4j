package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.notima.api.fortnox.entities3.Supplier;

public class TestCreateSupplier extends FortnoxTest {
	
	@Test
	public void testCreateSupplier() {
		
		try {
			Supplier s = new Supplier();
			
			s.setName("Fortnox4J");
			s.setComments("Test supplier created by Fortnox4J");
			
			s = client.setSupplier(s, true);
			
			log.info("Supplier {} successfully created.", s.getSupplierNumber());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
