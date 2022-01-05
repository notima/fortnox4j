package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.entities3.Currency;

public class TestGetCurrency extends FortnoxTest {
	
	@Test
	public void testGetCurrency() throws Exception {
		
		try {
			Currency currency = client.getCurrency("EUR");
			
			System.out.println("Sellrate of EUR @ " + currency.getDate()  + " " + currency.getSellRate());
			
		} catch (FortnoxException e) {
			System.out.println(e.toString());
		}
	}
	
	@Test
	public void testInvalidCurrency() throws Exception {

		Currency currency = client.getCurrency("XYZ");
		org.junit.Assert.assertNull(currency);
		
	}

}
