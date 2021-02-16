package org.notima.api.fortnox.junit;

import java.util.List;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.Voucher;
import org.notima.api.fortnox.entities3.VoucherRow;
import org.notima.api.fortnox.entities3.VoucherRows;
import org.notima.api.fortnox.entities3.VoucherSubset;
import org.notima.api.fortnox.entities3.Vouchers;

public class TestGetVouchers extends FortnoxTest {
	
	private String VOUCHER_SERIES = "A";
	
	private FinancialYearSubset fy;
	
	@Test
	public void testGetFinancialYear() throws Exception {
		
		fy = client.getFinancialYear(null);
		System.out.println(fy.getFromDate() + " - " + fy.getToDate());
		
	}
	
	@Test
	public void testGetVouchers() throws Exception {
		
		fy = client.getFinancialYear(null);
		Vouchers vouchers = null;
		try {
			vouchers = client.getVouchers(fy.getId(), VOUCHER_SERIES);
			
			if (vouchers.getVoucherSubset()==null) {
				System.out.println("No vouchers in series " + VOUCHER_SERIES);
			}
			
			Voucher v;
			for (VoucherSubset vs : vouchers.getVoucherSubset()) {
				v = client.getVoucher(fy.getId(), vs.getVoucherSeries(), vs.getVoucherNumber());
				VoucherRows ir = v.getVoucherRows();
				List<VoucherRow> rows = ir.getVoucherRow();
				System.out.println(v.getTransactionDate());
				System.out.println(v.getComments());
				for (VoucherRow r : rows) {
					System.out.println("  " + r.getAccount() + " " + r.getDescription() + " " + (r.getDebit() - r.getCredit()));
				}
				
			}
		} catch (FortnoxException e) {
			System.out.println(e.toString());
		}
	}
	

}
