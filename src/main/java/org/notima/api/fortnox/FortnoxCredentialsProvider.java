package org.notima.api.fortnox;

import java.util.List;

import org.notima.api.fortnox.clients.FortnoxCredentials;

public abstract class FortnoxCredentialsProvider {
    protected String orgNo;

    protected String	defaultClientId;
    protected String	defaultClientSecret;
    
    public FortnoxCredentialsProvider(String orgNo) {
        this.orgNo = orgNo;
    }
    
    public String getOrgNo() {
    	return orgNo;
    }
    
    public String getDefaultClientId() {
		return defaultClientId;
	}

	public void setDefaultClientId(String defaultClientId) {
		this.defaultClientId = defaultClientId;
	}

	public boolean hasDefaultClientId() {
		return defaultClientId!=null && defaultClientId.trim().length()>0;
	}
	
	public boolean hasDefaultClientSecret() {
		return defaultClientSecret!=null && defaultClientSecret.trim().length()>0;
	}
	
	public String getDefaultClientSecret() {
		return defaultClientSecret;
	}

	public void setDefaultClientSecret(String defaultClientSecret) {
		this.defaultClientSecret = defaultClientSecret;
	}

	/**
     * 
     * @return	Credentials for this client.
     * @throws Exception	If the credentials can't be read from the underlying system.
     */
    public abstract FortnoxCredentials getCredentials() throws Exception;

    /**
     * 
     * @return	All credentials for this client (including historic ones)
     * @throws Exception
     */
    public abstract List<FortnoxCredentials> getAllCredentials() throws Exception;
    public abstract void setCredentials(FortnoxCredentials credentials) throws Exception;
    
    /**
     * Removes a specific credential.
     * 
     * @param removeThis
     * @throws Exception
     */
    public abstract void removeCredential(FortnoxCredentials removeThis) throws Exception;
    public abstract void removeCredentials() throws Exception;
}
