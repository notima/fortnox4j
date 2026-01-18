package org.notima.api.fortnox.junit;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxConstants;
import org.notima.api.fortnox.FortnoxScope;

public class TestFortnoxScope {

	private Set<String> scopesToTest = new TreeSet<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(FortnoxConstants.SCOPE_BOOKKEEPING);
			add(FortnoxConstants.SCOPE_CONNECT_FILE);
			add(FortnoxConstants.SCOPE_COST_CENTER);
			add(FortnoxConstants.SCOPE_COMPANYINFORMATION);
			add(FortnoxConstants.SCOPE_CURRENCY);
			add(FortnoxConstants.SCOPE_CUSTOMER);
			add(FortnoxConstants.SCOPE_INBOX);
			add(FortnoxConstants.SCOPE_INVOICE);
			add(FortnoxConstants.SCOPE_ARTICLE);
			add(FortnoxConstants.SCOPE_ORDER);
			add(FortnoxConstants.SCOPE_PAYMENT);
			add(FortnoxConstants.SCOPE_PROFILE);
			add(FortnoxConstants.SCOPE_PROJECT);
			add(FortnoxConstants.SCOPE_SETTINGS);
			add(FortnoxConstants.SCOPE_SUPPLIER);
			add(FortnoxConstants.SCOPE_SUPPLIERINVOICE);
			add(FortnoxConstants.SCOPE_DELETEVOUCHER);
		}
		
	};
	
	@Test
	public void testGetScopeUrlParameter() {
		
		FortnoxScope scope = new FortnoxScope(scopesToTest);
		String urlParameter = scope.getScopesAsUrlParameter();
		System.out.println("Testing Scope URL parameter: " + urlParameter);
		assertEquals("scope=companyinformation%20settings%20profile%20project%20article%20supplierinvoice%20connectfile%20bookkeeping%20supplier%20deletevoucher%20currency%20payment%20invoice%20costcenter%20inbox%20customer%20order", urlParameter);
		
	}

}
