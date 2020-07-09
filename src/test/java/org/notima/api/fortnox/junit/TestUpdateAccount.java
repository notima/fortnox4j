package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.Account;

public class TestUpdateAccount extends FortnoxTest {
	
	
	@Test
	public void testUpdateAccount() throws Exception {

		Account acct = client.getAccount(0, 1910);
		if (acct==null) {
			System.out.println("Account 1910 not found");
		} else {
			acct.setActive(true);
			try {
				acct = client.updateAccount(acct.getYear(), acct);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		
		System.out.println(acct.getNumber() + " : " + acct.getDescription() + " : " + acct.getActive() + " : " + acct.getVATCode());
		
	}
	

}
