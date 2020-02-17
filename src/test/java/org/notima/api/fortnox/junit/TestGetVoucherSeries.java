package org.notima.api.fortnox.junit;

import org.junit.Test;
import org.notima.api.fortnox.entities3.VoucherSeriesSubset;

public class TestGetVoucherSeries extends FortnoxTest {
	
	public static final String DEFAULT_VOUCHER_SERIES = "A";
	
	@Test
	public void testGetVoucherSeries() throws Exception {

		VoucherSeriesSubset s = client.getVoucherSeries(DEFAULT_VOUCHER_SERIES);

		if (s==null) {
			log.warn("No voucher series: {}", DEFAULT_VOUCHER_SERIES);
		} else {
			log.info("Voucher series '{} : {}' retrieved", s.getCode(), s.getDescription());
		}
		
	}
	

}
