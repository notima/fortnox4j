package org.notima.api.fortnox.junit;

import java.io.FileReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxCredentials;

public class TestUtil {

	/**
	 * Returns a Fortnox-client (if a configuration file exists and is valid)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static FortnoxClient3 getFortnoxClient() throws Exception {
		FortnoxClient3 client = new FortnoxClient3(new FortnoxCredentialsProvider("") {
			URL file = Thread.currentThread().getContextClassLoader().getResource("FortnoxCredentials.json");
			private Gson gson = new GsonBuilder().setPrettyPrinting().create();

			@Override
			public FortnoxCredentials getCredentials() throws Exception {
				JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        		FortnoxCredentials credentials = gson.fromJson(reader, FortnoxCredentials.class);
				return credentials;
			}


			@Override
			public void setCredentials(FortnoxCredentials credentials) throws Exception {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void removeCredentials() throws Exception {
				// TODO Auto-generated method stub
				
			}
		});
		
		return client;
		
	}
	
}
