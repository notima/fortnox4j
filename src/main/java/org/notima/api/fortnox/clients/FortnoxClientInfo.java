package org.notima.api.fortnox.clients;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.notima.api.fortnox.entities3.CompanySetting;

/**
 * Class containing information about a Fortnox client
 * 
 * @author Daniel Tamm
 *
 */
// @XmlRootElement(name="FortnoxClientInfo")
public class FortnoxClientInfo {

	private String	clientId;
	private String	apiCode;
	private String	clientSecret;
	
	private String	orgNo;
	private String	orgName;

	private String	contactName;
	private String 	contactEmail;
	private String 	contactDeviationEmail;
	
	private String	settingsSupplierNo;
	private Boolean sandbox;

	private String	pollType;
	private Date	lastPollTime;
	
	private String	customSettings;
	
	@XmlTransient
	private CompanySetting		companySetting;

	/**
	 * The client ID associated with this access token.
	 * 
	 * The client ID is an identifier of the API client talking to Fortnox.
	 * This API client can have access to several Fortnox clients (customers).
	 * 
	 * @return		The client ID
	 */
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * The API code is used once to get an access token. 
	 * 
	 * @return	The API code if set.
	 */
	@Deprecated
	public String getApiCode() {
		return apiCode;
	}

	@Deprecated
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	
	/**
	 * The client secret is used to gain access to the Fortnox organization 
	 * paired with the access token.
	 * The client secret can also be supplied in the FortnoxApiClient if this
	 * class is part of a FortnoxClientList. 
	 * 
	 * @see			FortnoxCredentialsProvider#getClientSecret()
	 * @see			FortnoxClientList
	 * 	
	 * @return		The client secret if set.
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String clientName) {
		this.orgName = clientName;
	}

	public String getContactName(){
		return contactName;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public String getContactEmail(){
		return contactEmail;
	}

	public void setContactEmail(String contactEmail){
		this.contactEmail = contactEmail;
	}

	public String getContactDeviationEmail(){
		return contactDeviationEmail;
	}

	public void setContactDeviationEmail(String contactDeviationEmail){
		this.contactDeviationEmail = contactDeviationEmail;
	}
	
	public String getSettingsSupplierNo() {
		return settingsSupplierNo;
	}

	public void setSettingsSupplierNo(String settingsSupplierNo) {
		this.settingsSupplierNo = settingsSupplierNo;
	}
	
	public Boolean getSandbox() {
		return sandbox;
	}

	public void setSandbox(Boolean sandbox) {
		this.sandbox = sandbox;
	}

	public Date getLastPollTime() {
		return lastPollTime;
	}

	public void setLastPollTime(Date lastPollTime) {
		this.lastPollTime = lastPollTime;
	}
	
	public String getCustomSettings() {
		return customSettings;
	}

	public void setCustomSettings(String customSettings) {
		this.customSettings = customSettings;
	}

	public CompanySetting getCompanySetting() {
		return companySetting;
	}

	public void setCompanySetting(CompanySetting companySetting) {
		this.companySetting = companySetting;
	}
	
}
