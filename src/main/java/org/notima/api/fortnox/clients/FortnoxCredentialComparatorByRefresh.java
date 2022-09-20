package org.notima.api.fortnox.clients;

import java.util.Comparator;

/**
 * Compares credentials using the last refresh date
 * 
 * @author Daniel Tamm
 *
 */
public class FortnoxCredentialComparatorByRefresh implements Comparator<FortnoxCredentials> {

	@Override
	public int compare(FortnoxCredentials o1, FortnoxCredentials o2) {
		if (o1==null && o2==null) return 0;
		if (o1==null) return -1;
		if (o2==null) return 1;
		return (o1.getLastRefresh() < o2.getLastRefresh() ? -1 : 1);
	}
	
	
}
