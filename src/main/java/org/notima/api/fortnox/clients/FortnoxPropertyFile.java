package org.notima.api.fortnox.clients;

import java.util.Dictionary;

public class FortnoxPropertyFile {

	private String 	fortnoxClientsFile;
	private String	fortnoxCredentialsFile;
	private String	defaultClientSecret;
	private String	defaultClientId;
	private String	defaultScope;
	
	public final static String DONT_STORE_HERE = "DONT_STORE_HERE";
	
	public FortnoxPropertyFile() {};
	
	public FortnoxPropertyFile(Dictionary<String, Object> dict) {
		setFromDictionary(dict);
	}
	
	public void setFromDictionary(Dictionary<String, Object> properties) {
	    fortnoxClientsFile = (String)properties.get("fortnoxClientsFile");
		fortnoxCredentialsFile = (String)properties.get("fortnoxCredentialsFile");
	    defaultClientSecret = (String)properties.get("defaultClientSecret");
	    defaultClientId = (String)properties.get("defaultClientId");
	    defaultScope = (String)properties.get("defaultScope");
	}

	public String getFortnoxClientsFile() {
		return fortnoxClientsFile;
	}


	public void setFortnoxClientsFile(String fortnoxClientsFile) {
		this.fortnoxClientsFile = fortnoxClientsFile;
	}


	public String getFortnoxCredentialsFile() {
		return fortnoxCredentialsFile;
	}


	public void setFortnoxCredentialsFile(String fortnoxCredentialsFile) {
		this.fortnoxCredentialsFile = fortnoxCredentialsFile;
	}


	public String getDefaultClientSecret() {
		if (DONT_STORE_HERE.equals(defaultClientSecret)) return null;
		return defaultClientSecret;
	}


	public void setDefaultClientSecret(String defaultClientSecret) {
		this.defaultClientSecret = defaultClientSecret;
	}


	public String getDefaultClientId() {
		if (DONT_STORE_HERE.equals(defaultClientId)) return null;
		return defaultClientId;
	}


	public void setDefaultClientId(String defaultClientId) {
		this.defaultClientId = defaultClientId;
	}

	public String getDefaultScope() {
		return defaultScope;
	}

	public void setDefaultScope(String defaultScope) {
		this.defaultScope = defaultScope;
	}
	
	
}
