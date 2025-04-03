package org.notima.api.fortnox.oauth2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCredentialsProvider extends FortnoxCredentialsProvider {
	

    protected static Logger	logger = LoggerFactory.getLogger(FileCredentialsProvider.class);    

    private String clientId = null; 
    private String clientSecret = null;
    
    private CredentialsFile file = null;
    
    public FileCredentialsProvider(String orgNo) throws IOException {
        super(orgNo);
        file = new CredentialsFile();
    }
    
    public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
     * Return the credentials with the highest lastRefresh.
     * 
     * @throws Exception	If the credentials can't be read from the underlying system.
     */
    @Override
    public FortnoxCredentials getCredentials() throws Exception {
    	
    	FortnoxCredentials result = null;
    	
        for(FortnoxCredentials credentials : file.getKeyList()) {
            if (credentials.getOrgNo().equals(orgNo)){
            	if (clientId==null && credentials.getClientId()!=null) {
            		clientId = credentials.getClientId();
            	}
            	if (clientSecret==null && credentials.getClientSecret()!=null) {
            		clientSecret = credentials.getClientSecret();
            	}
            	if (result!=null) {
            		if (result.getLastRefresh()<credentials.getLastRefresh()) {
            			result = credentials;
            		} else {
            			continue;
            		}
            	} else {
            		result = credentials;
            	}
            }
        }
        if (result!=null && clientId!=null && (result.getClientId()==null || result.getClientId().trim().length()==0)) {
        	result.setClientId(clientId);
        }
        if (result!=null && clientSecret!=null && (result.getClientSecret()==null || result.getClientSecret().trim().length()==0)) {
        	result.setClientSecret(clientSecret);
        }
        
        checkClientIdAndSecret(result);
        
        return result;
    }

    private void checkClientIdAndSecret(FortnoxCredentials cred) {
    	
    	if (cred==null) return;
    	
        if (clientId==null && !cred.hasClientId()) {
        	cred.setClientId(this.defaultClientId);
        }
        if (clientSecret==null && !cred.hasClientSecret()) {
        	cred.setClientSecret(this.defaultClientSecret);
        }
    	
    }
    
    @Override
    public void setCredentials(FortnoxCredentials credentials) throws Exception {
        credentials.setOrgNo(orgNo);
        List<FortnoxCredentials> keys = file.getKeyList();
        boolean updated = false;
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).getOrgNo().equals(credentials.getOrgNo())) {
                keys.set(i, credentials);
                updated = true;
                break;
            }
        }
        if(!updated) {
            keys.add(credentials);
        }
        
        checkClientIdAndSecret(credentials);
        
        file.writeToFile();
        logger.debug((updated ? "Updated" : "Added") + " credentials for " + credentials.getOrgNo() + " to file " + file.getFilePath());
    }

    @Override
    public void removeAllCredentials() throws Exception {
        List<FortnoxCredentials> credentialsList = file.getKeyList();
        List<FortnoxCredentials> credentialsToRemove = new ArrayList<FortnoxCredentials>();
        for(int i = 0; i < credentialsList.size(); i++) {
            if (credentialsList.get(i).getOrgNo().equals(orgNo)){
                credentialsToRemove.add(credentialsList.get(i));
            }
        }
        removeCredentials(credentialsToRemove);
    }


	@Override
	public List<FortnoxCredentials> getAllCredentials() throws Exception {
		
		List<FortnoxCredentials> allCreds = new ArrayList<FortnoxCredentials>();
		
		for(FortnoxCredentials credentials : file.getKeyList()) {
			if (credentials.getOrgNo().equals(orgNo))
				allCreds.add(credentials);
		}
		
		return allCreds;
	}

	@Override
	public void removeCredential(FortnoxCredentials removeThis) throws Exception {

		List<FortnoxCredentials> credentials = file.getKeyList();
		boolean removed = false;
		
		for (FortnoxCredentials credential : credentials) {
			if (credential.equals(removeThis)) {
				credentials.remove(credential);
				removed = true;
				break;
			}
		}
		if (removed) {
			file.writeToFile();
			logger.debug("Removed one credential for " + orgNo + " from file " + file.getFilePath());
		}
		
	}

	@Override
	public int removeCredentials(List<FortnoxCredentials> removeThese) throws Exception {

		if (removeThese==null || removeThese.size()==0)
			return 0;
		
		List<FortnoxCredentials> credentials = file.getKeyList();
		List<FortnoxCredentials> listToRemove = new ArrayList<FortnoxCredentials>();
		
		for (FortnoxCredentials credential : credentials) {
			if (isInList(credential, removeThese)) {
				listToRemove.add(credential);
			}
		}

		int removeCount = 0;
		
		for (FortnoxCredentials remove : listToRemove) {
			credentials.remove(remove);
			removeCount++;
		}
		
		if (removeCount>0) {
			file.writeToFile();
			logger.debug("Removed " + removeCount + " credential(s) for " + orgNo + " from file " + file.getFilePath());
		}
		
		return removeCount;
	}
	
	private boolean isInList(FortnoxCredentials credential, List<FortnoxCredentials> list) {
		
		if (list==null || list.size()==0) return false;
		if (credential == null) return false;
		
		for (FortnoxCredentials c : list) {
			if (c.equals(credential))
				return true;
		}
		
		return false;
	}
	
}
