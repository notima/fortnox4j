package org.notima.api.fortnox.clients;

import java.beans.Transient;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class FortnoxCredentials {

    private String orgNo;

    private String clientId;
    private String clientSecret;

    @SerializedName("authorization_code")
    private String authorizationCode;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    private String scope;

    @SerializedName("expires_in")
    private int expiresIn;

    @SerializedName("token_type")
    private String tokenType;

    private long lastRefresh;
    
    private String legacyToken;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getLastRefresh() {
        return lastRefresh;
    }

    @Transient
    public Date getLastRefreshAsDate() {
    	
    	Date date = new Date();
    	date.setTime(lastRefresh);
    	return date;
    	
    }
    
    public void setLastRefresh(long lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public String getLegacyToken() {
        return legacyToken;
    }

    public void setLegacyToken(String legacyToken) {
        this.legacyToken = legacyToken;
    }

    @Transient
    public String getLegacyTokenAbbreviated() {
    	if (!hasLegacyToken()) {
    		return ("N/A");
    	} else {
    		if (legacyToken.length()>8) {
    			return ".." + legacyToken.substring(legacyToken.length()-8, legacyToken.length());
    		}
    		return legacyToken;
    	}
    }
    
    @Transient
    public String getAccessTokenAbbreviated() {
    	
    	if (!hasAccessToken()) {
    		return("N/A");
    	} else {
    		if (accessToken.length()>8) {
    			return ".." + accessToken.substring(accessToken.length()-8, accessToken.length());
    		} else {
    			return accessToken;
    		}
    	}
    	
    }
    
    @Transient
    public boolean hasLegacyTokenAndClientSecret() {
    	return (hasLegacyToken() &&
    			hasClientSecret());
    }
    
    /**
     * A string representation of the credential
     */
    public String toString() {
    	if (hasLegacyToken()) {
    		return "Legacy: " + getLegacyTokenAbbreviated();
    	}
    	if (hasAccessToken()) {
    		return ("Oauth2: " + getAccessTokenAbbreviated() + ", Refresh: " + getLastRefreshAsDate());
    	}
    	return ("ClientID: " + clientId + ". Credentials uncomplete.");
    }
    
    public boolean equals(FortnoxCredentials that) {
    	
    	if (hasLegacyToken()) {
    		return (legacyTokenEquals(that) && clientSecretEquals(that));
    	} else {
    		return (accessTokenEquals(that) && (lastRefresh == that.lastRefresh));
    	}
    	
    }
    
    private boolean hasAccessToken() {
    	return accessToken!=null && accessToken.trim().length()>0;
    }
    
    private boolean accessTokenEquals(FortnoxCredentials that) {
    	if (that==null) return false;
    	return (hasAccessToken() && accessToken.equals(that.getAccessToken()));
    }
    
    private boolean hasClientSecret() {
    	return (clientSecret!=null && clientSecret.trim().length()>0);
    }
    
    private boolean hasLegacyToken() {
    	return (legacyToken!=null && legacyToken.trim().length()>0);
    }
    
    private boolean legacyTokenEquals(FortnoxCredentials that) {
    	if (that==null || !hasLegacyToken()) return false;
    	return (legacyToken.equals(that.getLegacyToken()));
    }
    
    private boolean clientSecretEquals(FortnoxCredentials that) {
    	if (that==null || !hasClientSecret()) return false;
    	return (clientSecret.equals(that.getClientSecret()));
    }
     
}
