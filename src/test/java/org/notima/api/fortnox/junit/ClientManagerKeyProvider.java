package org.notima.api.fortnox.junit;

import java.util.List;

import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientManager;
import org.notima.api.fortnox.clients.FortnoxCredentials;


public class ClientManagerKeyProvider extends FortnoxCredentialsProvider {

    private FortnoxClientManager clientManager;

    public ClientManagerKeyProvider(String orgNo, FortnoxClientManager clientManager) {
        super(orgNo);
        this.clientManager = clientManager;
    }

    @Override
    public FortnoxCredentials getCredentials()  throws Exception {
        return getClientInfo(orgNo).getApiKey();
    }

    @Override
    public void setCredentials(FortnoxCredentials credentials) throws Exception {
        FortnoxClientInfo clientInfo = getClientInfo(orgNo);
        clientInfo.setApiKey(credentials);
        clientManager.updateAndSaveClientInfo(clientInfo);
    }

    @Override
    public void removeAllCredentials() throws Exception {}

    private FortnoxClientInfo getClientInfo(String orgNo) {
        for(FortnoxClientInfo clientInfo : clientManager.getFortnoxClients()) {
            if(clientInfo.getOrgNo().equals(orgNo)) {
                return clientInfo;
            }
        }
        return null;
    }

	@Override
	public List<FortnoxCredentials> getAllCredentials() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCredential(FortnoxCredentials removeThis) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int removeCredentials(List<FortnoxCredentials> removeThese) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
