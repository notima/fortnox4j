package org.notima.api.fortnox.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.notima.api.fortnox.entities3.CompanySetting;

public class TestGetCompanySettings extends FortnoxTest {

	@Test
	public void testGetCompanySetting() throws Exception{
		
		try {
			CompanySetting cs = client.getCompanySetting();
			
			log.info("Running tests on database: {} : {}", cs.getDatabaseNubmer(), cs.getName());
			log.info("Adress: " + cs.getAdress() + " | Email: " + cs.getEmail() + 
					"\nNumber: " + cs.getPhone1() + " | orgNr: " + cs.getOrganizationNumber() + " | Location: " + cs.getCity() + " " + cs.getZipCode()+
					"\nBankGiro: " + cs.getBg()) ;
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
