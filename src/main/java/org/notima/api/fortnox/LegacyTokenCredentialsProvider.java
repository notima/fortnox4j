package org.notima.api.fortnox;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<FortnoxCredentials> getAllCredentials() throws Exception {
		List<FortnoxCredentials> allCreds = new ArrayList<FortnoxCredentials>();
		if (credentials!=null)
			allCreds.add(credentials);
		
		return allCreds;
	}

	@Override
	public void removeCredential(FortnoxCredentials removeThis) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
