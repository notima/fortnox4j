package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.Account;

public class TestGetAccount extends FortnoxTest {
	
	
	@Test
	public void testGetAccount() throws Exception {

		Account acct = client.getAccount(0, 1930);
		
		System.out.println(acct.getNumber() + " : " + acct.getDescription() + " : " + acct.getVATCode());
		
	}
	

}
