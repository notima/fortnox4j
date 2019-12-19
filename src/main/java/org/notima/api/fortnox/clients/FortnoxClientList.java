package org.notima.api.fortnox.clients;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A client list is a collection API-clients and corresponding of Fortnox Clients
 * 
 * @author Daniel Tamm
 *
 */
@XmlRootElement(name="FortnoxClientList")
public class FortnoxClientList {

	private ListOfApiClient				apiClients;
	private ListOfClientInfo			clients;
	
	private Map<String, FortnoxApiClient> apiClientMap;
	private Map<String, FortnoxClientInfo> clientMap;
	
	@XmlElement(name="FortnoxApiClients")	
	public ListOfApiClient getApiClients() {
		return apiClients;
	}
	public void setApiClients(ListOfApiClient apiClients) {
		this.apiClients = apiClients;
	}

	@XmlElement(name = "FortnoxClients")
	public ListOfClientInfo getClients() {
		return clients;
	}
	
	public void setClients(ListOfClientInfo clients) {
		this.clients = clients;
	}
	
	/**
	 * Generates API map from the list of API clients.
	 * 
	 * @param regenerate	If the map should be generated even if it exists.
	 */
	public void generateApiMap(boolean regenerate) {
		
		if (apiClientMap!=null)
			if (regenerate)
				apiClientMap.clear();
		else {
			if (!regenerate)
				return;
			apiClientMap = new TreeMap<String, FortnoxApiClient>();
		}
		
		if (apiClients==null || apiClients.getApiClient()==null || apiClients.getApiClient().size()==0)
			return;
		
		for (FortnoxApiClient fac : apiClients.getApiClient()) {
			apiClientMap.put(fac.getClientId(), fac);
		}
		
	}
	
	/**
	 * Generates API map from the list of Fortnox Clients
	 * 
	 * @param regenerate	If the map should be generated even if it exists.
	 */
	public void generateFortnoxClientMap(boolean regenerate) {
		
		if (clientMap!=null)
			if (regenerate)
				clientMap.clear();
		else {
			if (!regenerate)
				return;
			clientMap = new TreeMap<String, FortnoxClientInfo>();
		}
		
		if (clients==null || clients.getFortnoxClient()==null || clients.getFortnoxClient().size()==0)
			return;
		
		for (FortnoxClientInfo fci : clients.getFortnoxClient()) {
			clientMap.put(fci.getOrgNo(), fci);
		}
		
	}
	

	/**
	 * @param	clientId		The clientId to look for
	 * @return 	The FortnoxApiClient that belongs to the clientId. Null if not found.
	 */
	public FortnoxApiClient getApiClientById(String clientId) {
		generateApiMap(false);
		return apiClientMap.get(clientId);
	}

	/**
	 * 
	 * @param orgNo		The orgno to look for
	 * @return	The FortnoxClientInfo that belongs to this orgno. Null if not found
	 */
	public FortnoxClientInfo getClientInfoByOrgNo(String orgNo) {
		generateFortnoxClientMap(false);
		return clientMap.get(orgNo);
	}
	
}
