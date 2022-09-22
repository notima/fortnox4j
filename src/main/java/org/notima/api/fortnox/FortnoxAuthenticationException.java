package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxCredentials;

public class FortnoxAuthenticationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1144464080938537096L;

	private FortnoxCredentials credentials;
	
	public FortnoxAuthenticationException(String format) {
        super(format);
    }

	public boolean hasCredentials() {
		return (credentials!=null);
	}
	
	public FortnoxCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(FortnoxCredentials credentials) {
		this.credentials = credentials;
	}
	
}
