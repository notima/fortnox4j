package org.notima.api.fortnox.clients;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ListOfApiClient {

	private List<FortnoxApiClient>		fortnoxApiClient;

	@XmlElement(name="FortnoxApiClient")
	public List<FortnoxApiClient> getApiClient() {
		return fortnoxApiClient;
	}

	public void setApiClient(List<FortnoxApiClient> fortnoxApiClient) {
		this.fortnoxApiClient = fortnoxApiClient;
	}
	
	
}
