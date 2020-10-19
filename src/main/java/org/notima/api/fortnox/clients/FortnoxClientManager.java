package org.notima.api.fortnox.clients;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.FortnoxUtil;
import org.notima.api.fortnox.entities3.CompanySetting;
import org.notima.api.fortnox.entities3.PreDefinedAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to manage several different clients.
 * 
 * @author Daniel Tamm
 *
 */
public class FortnoxClientManager {

	private Logger log = LoggerFactory.getLogger(FortnoxClientManager.class);	
	
	private FortnoxClientList clientList;
	
	// The path to the clients file
	private String	clientsFile;

	// The default client secret
	private String	defaultClientSecret;
	
	public FortnoxClientManager() {}
	
	/**
	 * Instantiates this client manager by reading clients from given file.
	 * 
	 * @param fortnoxClientsFile	An XML-file containing client data
	 * @throws FileNotFoundException	If the file is not found.
	 */
	public FortnoxClientManager(String fortnoxClientsFile) throws FileNotFoundException {

		readClientsFromFile(fortnoxClientsFile);
		
	}
	
	public FortnoxClientManager getThis() {
		return this;
	}
	
	/**
	 * Takes the FortnoxClientInfo parameter and updates / adds it to the
	 * client list and saves the list to the file specified by clientsFile.
	 * 
	 * @param ci		The client info
	 * @return			The client info.
	 * @throws Exception	If something goes wrong.
	 */
	public FortnoxClientInfo updateAndSaveClientInfo(FortnoxClientInfo ci) throws Exception {
		
		if (ci.getOrgNo()==null) {
			throw new Exception("OrgNo is mandatory");
		}
		
		FortnoxClientInfo dst = getClientInfoByOrgNo(ci.getOrgNo());
		if (dst==null) {
			dst = addClient(ci);
		} else {
			// Update existing
			dst.setAccessToken(ci.getAccessToken());
			dst.setClientSecret(ci.getClientSecret());
		}
		CompanySetting cs = ci.getCompanySetting();
		if (cs!=null) {
			dst.setOrgName(cs.getName());
		}

		boolean saved = saveClientInfo();
		if (!saved) {
			log.warn("No FortnoxClientsFile specified. Update of orgNo {} not persisted.", ci.getOrgNo());
		}
		
		return dst;
		
	}

	/**
	 * Saves current client info to file.
	 * 	
	 * @return	True if it was saved to file. False if there was no file to save to.
	 * 
	 * @throws Exception	If something goes wrong.
	 */
	public boolean saveClientInfo() throws Exception {

		// Save to file if a file is specified
		if (clientsFile!=null) {
			FortnoxUtil.writeFortnoxClientListToFile(clientList, clientsFile);
			log.info("{} file updated.", clientsFile);
			return true;
		} else  {	
			return false;
		}
		
	}
	
	/**
	 * Reads clients from xml-file and sets the clients file.
	 * 
	 * @param fortnoxClientsFile	Path to XML file. Can be in classpath.
	 * @return	True if clients where read.
	 * @throws FileNotFoundException	if the client's file is not found.
	 */
	public Boolean readClientsFromFile(String fortnoxClientsFile) throws FileNotFoundException {
		
		try {
			clientList = FortnoxUtil.readFortnoxClientListFromFile(fortnoxClientsFile);
			clientsFile = fortnoxClientsFile;
			return clientList!=null ? Boolean.TRUE : Boolean.FALSE; 
		} catch (JAXBException e) {
			log.warn("Can't read Fortnox Client file: {} ", fortnoxClientsFile);
			log.debug(e.getMessage());
			return Boolean.FALSE;
		}
		
	}

	/**
	 * Path to the clients xml-file.
	 * 	
	 * @return	A path if set.
	 */
	public String getClientsFile() {
		return clientsFile;
	}

	/**
	 * Sets the clients file property (doesn't read the file).
	 * 
	 * @param clientsFile		The client's file.
	 */
	public void setClientsFile(String clientsFile) {
		this.clientsFile = clientsFile;
	}

	/**
	 * A list of FortnoxClientInfo
	 * 
	 * @return	A list of clients and their credentials
	 */
	public List<FortnoxClientInfo> getFortnoxClients() {
		List<FortnoxClientInfo> list = new ArrayList<FortnoxClientInfo>();
		if (clientList==null)
			return list;
		ListOfClientInfo ll = clientList.getClients();
		if (ll==null)
			return list;
		List<FortnoxClientInfo> tmpList = ll.getFortnoxClient();
		FortnoxClientInfo fi;
		for (FortnoxClientInfo ii : tmpList) {
			fi = getClientInfoByOrgNo(ii.getOrgNo());
			if (fi!=null)
				list.add(fi);
		}
		return list;
	}

	/**
	 * Adds a new client info to the list.
	 * No checking for duplicates is made here.
	 * 
	 * @param ci		The client info.
	 * @return			The added client info.
	 */
	public FortnoxClientInfo addClient(FortnoxClientInfo ci) {
		
		List<FortnoxClientInfo> list = getFortnoxClients();
		list.add(ci);
		ListOfClientInfo ll = clientList.getClients();
		if (ll==null) {
			ll = new ListOfClientInfo();
			clientList.setClients(ll);
		}
		ll.setFortnoxClient(list);
		return ci;
	}
	
	/**
	 * Removes a client from the list.
	 * Remember to 
	 * 
	 * @param ci		The client to be removed
	 * @return			True if the client was removed. False if not found.
	 */
	public boolean removeClient(FortnoxClientInfo ci) {
		
		String orgNo = ci.getOrgNo();
		if (orgNo==null) return false;
		
		ListOfClientInfo ll = clientList.getClients();
		if (ll==null)
			return false;
		
		List<FortnoxClientInfo> tmpList = ll.getFortnoxClient();
		int removeIndex = -1;
		for (int i = 0; i<tmpList.size(); i++) {
			if (tmpList.get(i).getOrgNo().equalsIgnoreCase(orgNo)) {
				removeIndex = i;
				break;
			}
		}
		
		if (removeIndex>=0) {
			tmpList.remove(removeIndex);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the client list data
	 * 
	 * @return	FortnoxClientList
	 */
	public FortnoxClientList getClientList() {
		return clientList;
	}

	public void setClientList(FortnoxClientList clientList) {
		this.clientList = clientList;
	}
	
	/**
	 * A place to store a default client secret for this manager.
	 * 
	 * @return		The default client secret (if any).
	 */
	public String getDefaultClientSecret() {
		return defaultClientSecret;
	}

	public void setDefaultClientSecret(String defaultClientSecret) {
		this.defaultClientSecret = defaultClientSecret;
	}

	/**
	 * Gets client info by using the org no as key.
	 * 
	 * If client secret is supplied or can be derived from the list of Api Clients, the secret is also
	 * returned.
	 * 
	 * @param orgNo	The org number.
	 * @return		A FortnoxClientInfo record.
	 */
	public FortnoxClientInfo getClientInfoByOrgNo(String orgNo) {
		if (clientList==null) {
			log.warn("No Fortnox Clients available.");
			return null;
		}
		
		FortnoxClientInfo result = clientList.getClientInfoByOrgNo(orgNo);
		
		if (result!=null) {
			// Check and see if client secret is available
			if (result.getClientSecret()==null || result.getClientSecret().trim().length()==0) {
				// Lookup API client
				FortnoxApiClient apic = clientList.getApiClientById(result.getClientId());
				if (apic!=null) {
					result.setClientSecret(apic.getClientSecret());
				}
			}
		}
		
		return result;
	}
	

	/**
	 * Validates the connection. Gets an accessToken if an API-code is supplied. 
	 * 
	 * @param 		ci		The FortnoxClientInfo for the client to be validated.
	 * @return		True if the connection is validated.
	 * @throws Exception	If something goes wrong.
	 */
	public boolean validateConnection(FortnoxClientInfo ci) throws Exception {
		
		// Check if we have a situation where the access token must be renewed.
		// API code is submitted and the current credentials don't work
		if (ci.getAccessToken()!=null && ci.getAccessToken().trim().length()>0 
			&& ci.getApiCode()!=null && ci.getApiCode().trim().length()>0) {
			
			FortnoxClient3 cl3 = new FortnoxClient3(ci.getAccessToken(), ci.getClientSecret());
			// Try an access call
			try {
				PreDefinedAccount pda = cl3.getPreDefinedAccount("INVAT");
				if (pda!=null) {
					// Clear the api code since the login is working.
					ci.setApiCode(null);
				}
			} catch (FortnoxException ee) {
				if (FortnoxClient3.ERROR_INVALID_LOGIN.equals(ee.getErrorInformation().getCode())) {
					// Clear the access token so we can request a new access token.
					ci.setAccessToken(null);
					log.info("Invalid login. Clearing previous access token to request a new token for {}.", ci.getOrgNo());
				}
			}
			
		}

		// Check for no Access Token (ie we must get an access token using the API code)
		if (ci.getAccessToken()==null || ci.getAccessToken().trim().length()==0) {
			
			// Check for API-code to request access token
			if (ci.getApiCode()==null || ci.getApiCode().trim().length()==0) {
				log.info("Missing API-code. Can't request access token.");
				
				return false;
				
			}
			
			// Check for client secret and API-code
			if (ci.getClientSecret()==null || ci.getClientSecret().trim().length()==0) {
				if (defaultClientSecret==null) {
					throw new Exception("Missing client secret. Can't request access token.");
				} else {
					log.info("Using default client secret for access token request.");
					ci.setClientSecret(defaultClientSecret);
				}
			}
			
			log.info("Requesting Access Token for orgNo: " + ci.getOrgNo());

			String accessToken = null; 
			try {
				
				FortnoxClient3 client = new FortnoxClient3();
				accessToken = client.getAccessToken(ci.getApiCode(), ci.getClientSecret());
				
				if (accessToken==null) {
						log.error("Empty access token returned for orgNo: {}", ci.getOrgNo());
						throw new Exception("Empty access token returned for orgNo " + ci.getOrgNo());
				}
				
				ci.setAccessToken(accessToken);
			} catch (FortnoxException ee) {
				log.error("Can't retrieve accesstoken for orgNo: {}. Reason: {}", ci.getOrgNo(), ee.getErrorInformation().getMessage());
				throw ee;
			}
			
			// If we're here we got a new access token. Try saving it first
			updateAndSaveClientInfo(ci);
			// Get company info to update the name
			
		} 

		// Read settings
		FortnoxClient3 cl3 = new FortnoxClient3(ci.getAccessToken(), ci.getClientSecret());
		try {
			CompanySetting cs = cl3.getCompanySetting();
			ci.setCompanySetting(cs);
			// Save the company name
			if (ci.getOrgName()==null || ci.getOrgName().trim().length()==0) {
				ci.setOrgName(cs.getName());
			}
		} catch (FortnoxException fe) {
			log.warn(fe.toString());
		}
		updateAndSaveClientInfo(ci);
		
		return true;
		
	}
	
}
