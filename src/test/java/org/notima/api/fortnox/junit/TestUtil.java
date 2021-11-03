package org.notima.api.fortnox.junit;

import org.notima.api.fortnox.ClientManagerKeyProvider;
import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxKeyProvider;
import org.notima.api.fortnox.clients.FortnoxClientManager;

public class TestUtil {

	/**
	 * Returns a Fortnox-client (if a configuration file exists and is valid)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static FortnoxClient3 getFortnoxClient() throws Exception {

		FortnoxClientManager clientManager = new FortnoxClientManager("src/test/resources/FortnoxClientList.xml");
		FortnoxKeyProvider keyProvider = new ClientManagerKeyProvider("555555-5555", clientManager);
		FortnoxClient3 client = new FortnoxClient3("FortnoxClientList.xml", keyProvider);
		
		return client;
		
	}
	
}
