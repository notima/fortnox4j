package org.notima.api.fortnox;

import org.notima.api.fortnox.clients.FortnoxCredentials;

public abstract class FortnoxCredentialsProvider {
    protected String orgNo;

    public FortnoxCredentialsProvider(String orgNo) {
        this.orgNo = orgNo;
    }
    
    public abstract FortnoxCredentials getCredentials() throws Exception;
    public abstract void setCredentials(FortnoxCredentials credentials) throws Exception;
}
