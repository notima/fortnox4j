package org.notima.api.fortnox.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.FortnoxUtil;

public class TestRemoveNonAllowedCharacters {

	public String[] testStr = new String[] {
		"Blow-Off Cane | 3” TC for Chronicals ...",
		"Denna innehåller bara tillåtna tecken."
	};
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemoveNonAllowedCharacters() {
		
		String result;
		
		for (String s : testStr) {
			System.out.println("Src: " + s);
			result = FortnoxUtil.removeNonAllowedCharacters(s);
			System.out.println("Result: " + result);
		}
		
	}

}
