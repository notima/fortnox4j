package org.notima.api.fortnox.clients;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ListOfClientInfo {

	private List<FortnoxClientInfo>		fortnoxClient;

	@XmlElement(name="FortnoxClientInfo")
	public List<FortnoxClientInfo> getFortnoxClient() {
		return fortnoxClient;
	}

	public void setFortnoxClient(List<FortnoxClientInfo> fortnoxClient) {
		this.fortnoxClient = fortnoxClient;
	}
	
	
	
}
