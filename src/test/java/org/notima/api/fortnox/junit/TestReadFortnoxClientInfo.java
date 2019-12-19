package org.notima.api.fortnox.junit;

import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Test;
import org.notima.api.fortnox.FortnoxUtil;
import org.notima.api.fortnox.clients.FortnoxClientList;

public class TestReadFortnoxClientInfo extends FortnoxTest {

	public static final String CLIENTLIST_SAMPLE_FILE = "FortnoxClientListSample.xml";
	
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test to create a client info XML struct
	 */
	@Test
	public void testReadFortnoxClientInfo() {
		
			FortnoxClientList list;
			try {
				list = FortnoxUtil.readFortnoxClientListFromFile(CLIENTLIST_SAMPLE_FILE);
	
				StringWriter swr = new StringWriter();
				JAXB.marshal(list, swr);
				
				System.out.println(swr.toString());
				log.debug(swr.toString());
			
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
