package org.notima.api.fortnox.junit;

import org.notima.api.fortnox.FortnoxClient3;

public class TestUtil {

	/**
	 * Returns a Fortnox-client (if a configuration file exists and is valid)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static FortnoxClient3 getFortnoxClient() throws Exception {

		FortnoxClient3 client = new FortnoxClient3("FortnoxClientList.xml");
		
		return client;
		
	}
	
}
