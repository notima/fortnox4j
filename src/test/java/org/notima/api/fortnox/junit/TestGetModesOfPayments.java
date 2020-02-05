package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.ModeOfPaymentSubset;
import org.notima.api.fortnox.entities3.ModesOfPayments;

/**
 * Test get all modes of payments
 * 
 * @author Daniel Tamm
 *
 */
public class TestGetModesOfPayments extends FortnoxTest {
	
	@Test
	public void testGetModesOfPayments() throws Exception {
		
		ModesOfPayments modes = client.getModesOfPayments();
		
		int count = 0;
		
		for (ModeOfPaymentSubset m : modes.getModeOfPaymentSubset()) {
			log.info(m.getCode() + " : " + m.getAccountNumber() + " : " + m.getDescription());
			count++;
		}

		log.info("{} modes of payments.", count);
	}
	

}
