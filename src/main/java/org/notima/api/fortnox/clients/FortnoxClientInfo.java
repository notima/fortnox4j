package org.notima.api.fortnox.clients;

import java.beans.Transient;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.notima.api.fortnox.entities3.CompanySetting;

import com.google.gson.annotations.SerializedName;

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
	private String	accessToken;
	private String	clientSecret;
	private FortnoxCredentials apiKey;
	
	private String	orgNo;
	private String	orgName;
	private String	countryCode = "SE";		// Currently Fortnox is only for Swedish organizations.

	private String	contactName;
	private String 	contactEmail;
	private String 	contactDeviationEmail;
	
	private String	settingsSupplierNo;
	private Boolean	useSettingsSupplier = Boolean.FALSE;
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
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * The API code is used once to get an access token (pre-oauth) 
	 * 
	 * @return	The API code if set.
	 */
	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	/**
	 * Used by legacy access token json-struct.
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * The Access Token is used together with the Client Secret to gain access
	 * to a particular Fortnox organization. (pre-oauth)
	 * 
	 * XmlElement accessToken is required for the legacy token to work from the
	 * fortnoxClients.xml file.
	 * 
	 * @return		The access token if set.
	 */
	@XmlElement(name = "accessToken")
	public String getLegacyAccessToken() {
		if(apiKey == null)
			return accessToken;
		return apiKey.getLegacyToken();
	}
	
	public void setLegacyAccessToken(String accessToken) {
		if(apiKey == null)
			apiKey = new FortnoxCredentials();
		apiKey.setLegacyToken(accessToken);
	}

	/**
	 * The key used to authenticate this client
	 * 
	 * @return		The api key object
	 */
	public FortnoxCredentials getApiKey() {
		return apiKey;
	}

	public void setApiKey(FortnoxCredentials apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * The client secret is used to gain access to the Fortnox organization 
	 * paired with the access token.
	 * The client secret can also be supplied in the FortnoxApiClient if this
	 * class is part of a FortnoxClientList. 
	 * 
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
		if (hasCompanySetting() && companySetting.hasOrganizationNumber()) {
			return companySetting.getOrganizationNumber();
		} else {
			return orgNo;
		}
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
		if (hasCompanySetting()) {
			companySetting.setOrganizationNumber(orgNo);
		}
	}

	public String getPollType() {
		return pollType;
	}

	public void setPollType(String pollType) {
		this.pollType = pollType;
	}

	public boolean hasOrgName() {
		return (getOrgName()!=null && getOrgName().trim().length()>0);
	}
	
	public String getOrgName() {
		if (hasCompanySetting() && companySetting.hasName()) {
			return companySetting.getName();
		} else {
			return orgName;
		}
	}

	public void setOrgName(String clientName) {
		this.orgName = clientName;
		if (hasCompanySetting()) {
			companySetting.setName(clientName);
		}
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

	@Transient
	public boolean useSettingsSupplier() {
		return useSettingsSupplier!=null && useSettingsSupplier.booleanValue();
	}
	
	public Boolean getUseSettingsSupplier() {
		return useSettingsSupplier;
	}

	public void setUseSettingsSupplier(Boolean useSettingsSupplier) {
		this.useSettingsSupplier = useSettingsSupplier;
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

	public boolean hasCompanySetting() {
		return companySetting!=null;
	}
	
}
