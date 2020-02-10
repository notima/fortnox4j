package org.notima.api.fortnox.junit;

import java.util.Date;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxClient3;

/**
 * Test get locked until.
 * 
 * @author Daniel Tamm
 *
 */
public class TestGetLockedUntil extends FortnoxTest {
	
	@Test
	public void testGetLockedUntil() throws Exception {
		
		Date lockedUntil = client.getLockedPeriodUntil();

		if (lockedUntil==null) {
			log.info("No locked period found");
		} else {
			log.info("Locked until {}", FortnoxClient3.s_dfmt.format(lockedUntil));
		}
	}
	

}
