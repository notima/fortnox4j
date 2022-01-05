package org.notima.api.fortnox.junit;

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

    private FortnoxClientInfo getClientInfo(String orgNo) {
        for(FortnoxClientInfo clientInfo : clientManager.getFortnoxClients()) {
            if(clientInfo.getOrgNo().equals(orgNo)) {
                return clientInfo;
            }
        }
        return null;
    }
    
}
