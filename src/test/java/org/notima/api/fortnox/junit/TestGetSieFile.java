package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.entities3.CompanySetting;
import org.notima.api.fortnox.entities3.FinancialYearSubset;

/**
 * Tests retrieving a SIE file and saving it to current working directory.
 * 
 * @author Daniel Tamm
 *
 */
public class TestGetSieFile extends FortnoxTest {

	String TEST_DATE = "2019-12-01";
	
	@Test
	public void testGetSieFile() throws Exception{
		
		try {
			CompanySetting cs = client.getCompanySetting();
			log.info("Retrieving SIE file from: {} : {}", cs.getDatabaseNumber(), cs.getName());
			FinancialYearSubset fs = client.getFinancialYear(FortnoxClient3.s_dfmt.parse(TEST_DATE));
			if (fs==null) {
				fail("No fiscal year defined for " + cs.getName());
				return;
			}
			int yearId = fs.getId();
			String dir = System.getProperty("user.dir");
			String fileStr = client.retrieveSieAndSaveToFile(4, yearId, dir, cs.getName() + "_" + fs.getToDate() + ".si");
			log.info("Saved SIE4 to {}", fileStr);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
