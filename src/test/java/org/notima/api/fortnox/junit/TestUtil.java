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
		FortnoxClient3 client = new FortnoxClient3("FortnoxClientList.xml", new FortnoxCredentialsProvider("") {
			URL file = Thread.currentThread().getContextClassLoader().getResource("FortnoxCredentials.json");
			private Gson gson = new GsonBuilder().setPrettyPrinting().create();

			@Override
			public FortnoxCredentials getCredentials() throws Exception {
				JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        		FortnoxCredentials credentials = gson.fromJson(reader, FortnoxCredentials.class);
				return credentials;
			}

		FortnoxClientManager clientManager = new FortnoxClientManager("src/test/resources/FortnoxClientList.xml");
		FortnoxKeyProvider keyProvider = new ClientManagerKeyProvider("555555-5555", clientManager);
		FortnoxClient3 client = new FortnoxClient3("FortnoxClientList.xml", keyProvider);
		
		return client;
		
	}
	
}
