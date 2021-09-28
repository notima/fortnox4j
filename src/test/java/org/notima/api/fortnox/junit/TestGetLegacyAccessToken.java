package org.notima.api.fortnox.junit;

import static org.junit.Assert.*;

import java.net.URL;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.entities3.LegacyAuthorization;

public class TestGetLegacyAccessToken {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		URL url = ClassLoader.getSystemResource("Authorization.xml");
		LegacyAuthorization obj = JAXB.unmarshal(url, LegacyAuthorization.class);
		assertEquals("Can't read access token", "TEST", obj.getAccessToken());
		
	}

}
