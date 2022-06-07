package org.notima.api.fortnox;

import java.util.List;

import org.notima.api.fortnox.clients.FortnoxCredentials;

public abstract class FortnoxCredentialsProvider {
    protected String orgNo;

    public FortnoxCredentialsProvider(String orgNo) {
        this.orgNo = orgNo;
    }
    
    public String getOrgNo() {
    	return orgNo;
    }

    /**
     * 
     * @return	Credentials for this client.
     * @throws Exception
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
