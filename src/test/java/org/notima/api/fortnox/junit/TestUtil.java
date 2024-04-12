package org.notima.api.fortnox.junit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientManager;
import org.notima.api.fortnox.oauth2.FileCredentialsProvider;

public class TestUtil {

	private FortnoxClientManager clientManager;
	private FortnoxClientInfo	 testClientInfo;
	private FortnoxCredentialsProvider	credentialsProvider;
	
	private void getFirstClientToTest() throws FileNotFoundException {
		
		clientManager = new FortnoxClientManager("FortnoxClientList.xml");
		
		List<FortnoxClientInfo> clients = clientManager.getFortnoxClients();
		if (clients.size()>0) {
			testClientInfo = clients.get(0);
		}
		
	}

	private void makeSureThereAreCredentials() throws IOException {
		
		if (testClientInfo!=null) {
			setDefaultCredentialsProvider();
		}
		
	}
	
	private void setDefaultCredentialsProvider() throws IOException {
		credentialsProvider = new FileCredentialsProvider(testClientInfo.getOrgNo());	
	}
	
	/**
	 * Returns a Fortnox-client (if a configuration file exists and is valid)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public FortnoxClient3 getFortnoxClient() throws Exception {
		
		getFirstClientToTest();
		makeSureThereAreCredentials();
		FortnoxClient3 client = new FortnoxClient3(credentialsProvider);
		
		return client;
		
	}
	
}
