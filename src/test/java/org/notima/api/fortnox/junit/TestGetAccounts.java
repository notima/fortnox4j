package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.AccountSubset;
import org.notima.api.fortnox.entities3.Accounts;
import org.notima.api.fortnox.entities3.FinancialYearSubset;

public class TestGetAccounts extends FortnoxTest {
	
	private FinancialYearSubset fy;
	
	
	@Test
	public void testGetAccounts() throws Exception {
		
		fy = client.getFinancialYear(null);
		Accounts accounts = client.getAccounts(fy.getId());
		
		for (AccountSubset a : accounts.getAccountSubset()) {
			if (a.getVATCode()!=null && a.getVATCode().trim().length()>0)
				System.out.println(a.getNumber() + " : " + a.getDescription() + " : " + a.getVATCode());
		}
		
	}
	

}
