package org.notima.api.fortnox.clients;


/**
 * The Fortnox API client is the API client used to access Fortnox Clients 
 * 
 * @author Daniel Tamm
 *
 */
public class FortnoxApiClient {

	private String	clientId;
	private String	clientSecret;
	
	private String	clientApiName;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getClientApiName() {
		return clientApiName;
	}

	public void setClientApiName(String clientApiName) {
		this.clientApiName = clientApiName;
	}
	
	
	
}
