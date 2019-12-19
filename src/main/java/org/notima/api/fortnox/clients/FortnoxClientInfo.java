package org.notima.api.fortnox.clients;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class containing information about a Fortnox client
 * 
 * @author Daniel Tamm
 *
 */
// @XmlRootElement(name="FortnoxClientInfo")
public class FortnoxClientInfo {

	private String	clientId;
	private String	accessToken;
	
	private String	orgNo;
	private String	clientName;

	private String	pollType;
	private Date	lastPollTime;

	/**
	 * The client ID associated with this access token.
	 * 
	 * @return		The client ID
	 */
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getPollType() {
		return pollType;
	}

	public void setPollType(String pollType) {
		this.pollType = pollType;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getLastPollTime() {
		return lastPollTime;
	}

	public void setLastPollTime(Date lastPollTime) {
		this.lastPollTime = lastPollTime;
	}

	
	
}
