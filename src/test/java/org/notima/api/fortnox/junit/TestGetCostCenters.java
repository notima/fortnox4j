package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.CostCenterSubset;
import org.notima.api.fortnox.entities3.CostCenters;

/**
 * Test get all cost centers
 * 
 * @author Daniel Tamm
 *
 */
public class TestGetCostCenters extends FortnoxTest {
	
	@Test
	public void testGetCostCenters() throws Exception {
		
		CostCenters ccs = client.getCostCenters();
		
		int count = 0;
		
		if (ccs.getTotalResources()==0) {
			log.info("No cost centers");
			return;
		}
		
		for (CostCenterSubset m : ccs.getCostCenterSubset()) {
			log.info(m.getCode() + " : " + m.getDescription() + " : " + m.getNote());
			count++;
		}

		log.info("{} cost centers.", count);
	}
	

}
