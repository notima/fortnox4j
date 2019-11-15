package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestSearch extends FortnoxTest {

	@Test
	public void testSearch() throws Exception{
		
		try {
			
			StringBuffer result = client.search("orders", "CustomerNumber", "1475");
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
