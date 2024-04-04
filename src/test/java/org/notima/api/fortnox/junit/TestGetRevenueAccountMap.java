package org.notima.api.fortnox.junit;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.VatInfo;

public class TestGetRevenueAccountMap extends FortnoxTest {

	private FinancialYearSubset fy;
	
	/**
	 * Tries to figure out all relevant revenue accounts by checking
	 * predefined accounts and VAT-codes in the chart of accounts.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetRevenueAccountMap() throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		
		fy = client.getFinancialYear(cal.getTime());

		Map<String, VatInfo> accountMap = client.getRevenueAccountMap(fy.getId());
		
		if (accountMap!=null) {
			Set<String> keys = accountMap.keySet();
			
			for (String k : keys) {
				System.out.println(k + " => " + accountMap.get(k));
			}
			
		}
		
	}
	

}
