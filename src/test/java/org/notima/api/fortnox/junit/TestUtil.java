package org.notima.api.fortnox.junit;

import java.io.FileNotFoundException;
import java.util.List;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientManager;
import org.notima.api.fortnox.clients.FortnoxCredentials;
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

	private void makeSureThereAreCredentials() throws Exception {
		
		if (testClientInfo!=null) {
			setDefaultCredentialsProvider();
		}
		
	}
	
	private void setDefaultCredentialsProvider() throws Exception {
		credentialsProvider = new FileCredentialsProvider(testClientInfo.getOrgNo());
		FortnoxCredentials fc = credentialsProvider.getCredentials();
		if (fc==null || !fc.hasAccessToken()) { 
			credentialsProvider.setCredentials(createCredentials());
		}
	}
	
	private FortnoxCredentials createCredentials() {
		FortnoxCredentials c = new FortnoxCredentials();
		c.setOrgNo(testClientInfo.getOrgNo());
		c.setClientId(testClientInfo.getClientId());
		c.setClientSecret(testClientInfo.getClientSecret());
		c.setAccessToken(testClientInfo.getApiKey().getAccessToken());
		c.setRefreshToken(testClientInfo.getApiKey().getRefreshToken());
		return c;
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
