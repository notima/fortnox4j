package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxCredentials;

/**
 * Credential provider that contains a legacy access token.
 * 
 * @author Daniel Tamm
 *
 */
public class LegacyTokenCredentialsProvider extends FortnoxCredentialsProvider {

	private FortnoxCredentials credentials;
	
	public LegacyTokenCredentialsProvider(String legacyToken) {
		super(null);
		credentials = new FortnoxCredentials();
		credentials.setLegacyToken(legacyToken);
	}
	
	@Override
	public FortnoxCredentials getCredentials() throws Exception {
		return credentials;
	}

	@Override
	public void setCredentials(FortnoxCredentials credentials) throws Exception {
		this.credentials = credentials;
	}

	@Override
	public void removeCredentials() throws Exception {
		credentials = null;
	}

}
