package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.PreDefinedAccountSubset;
import org.notima.api.fortnox.entities3.PreDefinedAccounts;

public class TestGetPreDefinedAccounts extends FortnoxTest {
	
	@Test
	public void testGetPreDefinedAccounts() throws Exception {
		
		PreDefinedAccounts accounts = client.getPreDefinedAccounts();
		
		int unsetCount = 0;
		int count = 0;
		
		for (PreDefinedAccountSubset a : accounts.getPreDefinedAccountSubset()) {
			log.info(a.getAccount() + " : " + a.getName());
			count++;
			if (a.getAccount()==0) {
				unsetCount++;
			}
		}

		log.info("{} predefined accounts. {} not set.", count, unsetCount);
	}
	

}
