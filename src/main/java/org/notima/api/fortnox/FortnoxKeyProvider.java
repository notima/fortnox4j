package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxApiKey;

public abstract class FortnoxKeyProvider {
    protected String orgNo;

    public FortnoxKeyProvider(String orgNo) {
        this.orgNo = orgNo;
    }
    
    public abstract FortnoxApiKey getKey() throws Exception;
    public abstract void setKey(FortnoxApiKey key) throws Exception;
}
