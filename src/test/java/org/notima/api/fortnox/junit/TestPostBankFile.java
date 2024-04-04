package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxConstants;
import org.notima.api.fortnox.entities3.FortnoxFile;

/**
 * NOTE! For this test to work, a BGMax-file must first be generated.
 * 
 * @author Daniel Tamm
 *
 */
public class TestPostBankFile extends FortnoxTest {
	
	@Test
	public void testPostBankFile() {
		
		try {
			
			String filePath = getFile("BGMax200121.IN").getAbsolutePath();
			
			FortnoxFile result = client.uploadFile(filePath, FortnoxConstants.INBOX_BANK_FILES);
			
			log.info("{} response.", result.getUrl());
			
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		
	}

}
