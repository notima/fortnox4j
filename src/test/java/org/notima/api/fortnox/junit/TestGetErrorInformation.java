package org.notima.api.fortnox.junit;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.entities3.ErrorInformation;

public class TestGetErrorInformation {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		URL url = ClassLoader.getSystemResource("ErrorInformation.xml");
		ErrorInformation obj = JAXB.unmarshal(url, ErrorInformation.class);
		assertEquals("Can't read error information", "2000719", obj.getCode());
		
	}

}
