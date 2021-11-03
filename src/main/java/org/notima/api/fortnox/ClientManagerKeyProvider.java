package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxApiKey;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientManager;

public class ClientManagerKeyProvider extends FortnoxKeyProvider {

    private FortnoxClientManager clientManager;

    public ClientManagerKeyProvider(String orgNo, FortnoxClientManager clientManager) {
        super(orgNo);
        this.clientManager = clientManager;
    }

    @Override
    public FortnoxApiKey getKey() throws Exception {
        return getClientInfo(orgNo).getApiKey();
    }

    @Override
    public void setKey(FortnoxApiKey key) throws Exception {
        FortnoxClientInfo clientInfo = getClientInfo(orgNo);
        clientInfo.setApiKey(key);
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
