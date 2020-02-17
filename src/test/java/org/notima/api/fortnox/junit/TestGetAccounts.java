package org.notima.api.fortnox.junit;

import java.util.Map;

import org.junit.Test;
import org.notima.api.fortnox.entities3.AccountSubset;

public class TestGetAccounts extends FortnoxTest {
	
	
	@Test
	public void testGetAccountMap() throws Exception {

		Map<String, AccountSubset> acctMap = client.getAccountMap(null);
		
		for (AccountSubset a : acctMap.values()) {
			if (a.getVATCode()!=null && a.getVATCode().trim().length()>0)
				System.out.println(a.getNumber() + " : " + a.getDescription() + " : " + a.getVATCode());
		}
		
	}
	

}
