package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxConstants;
import org.notima.api.fortnox.entities3.FortnoxFile;

/**
 * Tests uploading a sample PDF to the voucher inbox.
 * 
 * @author Daniel Tamm
 *
 */
public class TestUploadVoucherPDF extends FortnoxTest {
	
	@Test
	public void testPostBankFile() {
		
		try {
			
			String filePath = getFile("SamplePDF.pdf").getAbsolutePath();
			
			FortnoxFile result = client.uploadFile(filePath, FortnoxConstants.INBOX_VOUCHERS);
			
			log.info("{} response.", result.getUrl());
			
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		
	}

}
