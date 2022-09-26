package org.notima.api.fortnox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.notima.api.fortnox.clients.FortnoxCredentialComparatorByRefresh;
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
    
    /**
     * Removes old Oauth tokens with a last refresh lower than lastRefresh. The youngest
     * to be removed is older than 31 days.
     * 
     * @param 		lastRefresh - If zero, default 31 days and older are selected
     * @return		The number of credentials removed.
     * @throws 		Exception
     */
    public int purgeOauthCredentialsUntil(long lastRefresh) throws Exception {

    	// Make sure the last refresh isn't "younger" than 31 days.
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -31);
    	if (lastRefresh == 0 || lastRefresh > cal.getTimeInMillis()) {
    		lastRefresh = cal.getTimeInMillis();
    	}
    	
    	String clientId = null;
    	String clientSecret = null;
    	int removeCount = 0;
    	
    	List<FortnoxCredentials> allCredentials = getAllCredentials();
    	List<FortnoxCredentials> credentialsToKeep = new ArrayList<FortnoxCredentials>();
    	List<FortnoxCredentials> listCopy = new ArrayList<FortnoxCredentials>();
    	listCopy.addAll(allCredentials);
    	Collections.sort(listCopy, new FortnoxCredentialComparatorByRefresh());
    	
    	for (FortnoxCredentials credential : listCopy) {
    		if (credential.hasLegacyToken() || credential.hasAuthorizationCode()) {
    			continue;
    		}
    		// Get clientID and secret from existing credentials
    		if (clientId == null && credential.hasClientId()) {
    			clientId = credential.getClientId();
    		}
    		if (clientSecret == null && credential.hasClientSecret()) {
    			clientSecret = credential.getClientSecret();
    		}
    		
    		if (credential.hasRefreshToken() && credential.getLastRefresh() < lastRefresh) {
    			removeCredential(credential);
    			removeCount++;
    			continue;
    		}

    		// If we're here, we're keeping the credential
    		credentialsToKeep.add(credential);
    		
    	}

    	// Make sure there's at least one credential left.
    	if (credentialsToKeep.size()==0 && listCopy.size()>0) {
    		credentialsToKeep.add(listCopy.get(listCopy.size()-1));
    	}

    	boolean updated;
    	// Make sure we have clientId and clientSecret
    	for (FortnoxCredentials credential : credentialsToKeep) {
    		updated = false;
    		if (!credential.hasClientId()) {
    			credential.setClientId(clientId);
    			updated = true;
    		}
    		if (!credential.hasClientSecret()) {
    			credential.setClientSecret(clientSecret);
    			updated = true;
    		}
        	// Set credentials to new list
    		if (updated)
    			setCredentials(credential);
    	}
    	
    	return removeCount;
    	
    }
    
}
