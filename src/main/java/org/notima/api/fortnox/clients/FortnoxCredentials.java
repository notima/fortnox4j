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

    private boolean refreshLocked = false;

    void setRefreshLocked(boolean refreshLock) {
        this.refreshLocked = refreshLock;
    }

    public void lockRefresh() {
        refreshLocked = true;
    }

    public void unlockRefresh() {
        refreshLocked = false;
    }

    public boolean isRefreshLocked() {
        return refreshLocked;
    }

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
    
    @Transient
    public String getRefreshTokenAbbreviated() {
    	if (!hasRefreshToken()) {
    		return ("N/A");
    	} else {
    		if (refreshToken.length()>8) {
    			return ".." + refreshToken.substring(refreshToken.length()-8, refreshToken.length());
    		}
    		return legacyToken;
    	}
    }
    
    /**
     * The actual scope for this specific credential instance
     * 
     * @return
     */
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
    
    /**
     * Return true if this token is good for another 'minutes' minutes.
     * 
     * @param minutes	Number of minutes we need at least.
     * @return			True if the token is good for the given minutes.
     */
    public boolean goodForAnotherMinutes(int minutes) {

    	if (hasLegacyToken())
    		return true;
    	
    	long expiresAt = lastRefresh + (expiresIn * 1000);
    	long currentTime = System.currentTimeMillis();
    	long milliSecondsLeft = expiresAt - currentTime;
    	
    	return milliSecondsLeft > (minutes*1000);
    	
    }
    
    public void setLastRefresh(long lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public void setLastRefreshToNow() {
    	lastRefresh = new Date().getTime();
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
    public boolean hasAuthorizationCode() {
    	return (authorizationCode!=null && authorizationCode.trim().length()>0);
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
    	StringBuffer buf = new StringBuffer();
    	buf.append("OrgNo: " + orgNo + ", ");
    	if (hasLegacyToken()) {
    		buf.append("Legacy: " + getLegacyTokenAbbreviated());
    		return buf.toString();
    	}
    	buf.append("ClientID: " + getClientId() + ", Secret: " + (hasClientSecret() ? "Yes" : "No") + ", ");
    	buf.append("Oauth2: " + getAccessTokenAbbreviated() + ", ");
    	if (hasRefreshToken()) {
    		buf.append("RefreshToken: " + getRefreshTokenAbbreviated() + ", ");
    	} else {
    		buf.append("No refresh token, ");
    	}
    	buf.append(getLastRefreshAsDate() + " (" + lastRefresh + ")");
        if(isRefreshLocked()) {
            buf.append(" [Locked]");
        }
    	return (buf.toString());
    }
    
    public boolean equals(FortnoxCredentials that) {
    	
    	if (!orgNo.equals(that.orgNo))
    		return false;
    	
    	if (hasLegacyToken()) {
    		return (legacyTokenEquals(that) && clientSecretEquals(that));
    	} else {
    		return (accessTokenEquals(that) && (lastRefresh == that.lastRefresh));
    	}
    	
    }
    
    @Transient
    public boolean hasAccessToken() {
    	return accessToken!=null && accessToken.trim().length()>0;
    }
    
    private boolean accessTokenEquals(FortnoxCredentials that) {
    	if (that==null) return false;
    	return (hasAccessToken() && accessToken.equals(that.getAccessToken()));
    }

    @Transient
    public boolean hasClientId() {
    	return (clientId!=null && clientId.trim().length()>0);
    }
    
    @Transient
    public boolean hasClientSecret() {
    	return (clientSecret!=null && clientSecret.trim().length()>0);
    }
    
    @Transient
    public boolean hasLegacyToken() {
    	return (legacyToken!=null && legacyToken.trim().length()>0);
    }
    
    @Transient
    public boolean hasRefreshToken() {
    	return (refreshToken!=null && refreshToken.trim().length()>0);
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
