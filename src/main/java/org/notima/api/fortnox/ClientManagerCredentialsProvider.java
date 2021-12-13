package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientManager;

public class ClientManagerCredentialsProvider extends FortnoxCredentialsProvider {

    private FortnoxClientManager clientManager;

    public ClientManagerCredentialsProvider(String orgNo, FortnoxClientManager clientManager) {
        super(orgNo);
        this.clientManager = clientManager;
    }

    @Override
    public FortnoxCredentials getCredentials() throws Exception {
        clientManager.readClientsFromFile(clientManager.getClientsFile());
        return getClientInfo(orgNo).getCredentials();
    }

    @Override
    public void setCredentials(FortnoxCredentials key) throws Exception {
        FortnoxClientInfo clientInfo = getClientInfo(orgNo);
        clientInfo.setCredentials(key);
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
