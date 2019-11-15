package org.notima.api.fortnox;

import java.net.URL;

import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class TestUtil {

	/**
	 * Returns a Fortnox-client (if a configuration file exists and is valid)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static FortnoxClient3 getFortnoxClient() throws Exception {

		URL url = ClassLoader.getSystemResource("test-config3.xml");
		if (url==null) {
			return null;
		}
		
		FileConfiguration fc = new XMLConfiguration();
		fc.setURL(url);
		fc.load();
		
		
		FortnoxClient3 client = new FortnoxClient3();
		
		String clientSecret = fc.getString("clientSecret");
		String accessToken = fc.getString("accessToken");
		
		client.setAccessToken(accessToken, clientSecret);

		return client;
		
	}
	
}
