package org.notima.api.fortnox.junit;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Test;
import org.notima.api.fortnox.clients.FortnoxApiClient;
import org.notima.api.fortnox.clients.FortnoxClientInfo;
import org.notima.api.fortnox.clients.FortnoxClientList;
import org.notima.api.fortnox.clients.FortnoxCredentials;
import org.notima.api.fortnox.clients.ListOfApiClient;
import org.notima.api.fortnox.clients.ListOfClientInfo;

public class TestFortnoxClientInfo extends FortnoxTest {

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test to create a client info XML struct
	 */
	@Test
	public void testFortnoxClientInfo() {
		
		FortnoxClientList list = new FortnoxClientList();
		FortnoxApiClient apiClient = new FortnoxApiClient();
		apiClient.setClientId("TestClientId");
		apiClient.setClientSecret("TestClientSecret");
		apiClient.setClientApiName("Fortnox4J");
		List<FortnoxApiClient> apiClients = new ArrayList<FortnoxApiClient>();
		apiClients.add(apiClient);
		ListOfApiClient lia = new ListOfApiClient();
		lia.setApiClient(apiClients);
		list.setApiClients(lia);

		FortnoxCredentials credentials = new FortnoxCredentials();
		credentials.setAccessToken("test-access-token");
		credentials.setRefreshToken("test-refresh-token");
		credentials.setScope("settings");
		credentials.setExpiresIn(3600);
		credentials.setTokenType("bearer");
		credentials.setLastRefresh(new Date().getTime());
		
		List<FortnoxClientInfo> clientList = new ArrayList<FortnoxClientInfo>();
		FortnoxClientInfo fortnoxClient = new FortnoxClientInfo();
		fortnoxClient.setOrgNo("555555-5555");
		fortnoxClient.setApiKey(credentials);
		fortnoxClient.setClientId(apiClient.getClientId());
		fortnoxClient.setOrgName("TestOrgName");
		fortnoxClient.setSettingsSupplierNo("1000");
		fortnoxClient.setSandbox(Boolean.TRUE);
		fortnoxClient.setCustomSettings("{ \"someJsonSetting\" : \"setting\" }");
		
		clientList.add(fortnoxClient);
		ListOfClientInfo li = new ListOfClientInfo();
		li.setFortnoxClient(clientList);
		list.setClients(li);
		
		StringWriter swr = new StringWriter();
		JAXB.marshal(list, swr);
		
		System.out.println(swr.toString());
		log.debug(swr.toString());
	}

}
