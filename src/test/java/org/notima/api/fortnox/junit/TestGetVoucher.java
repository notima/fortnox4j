package org.notima.api.fortnox.junit;

import java.util.List;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.entities3.FinancialYearSubset;
import org.notima.api.fortnox.entities3.Voucher;
import org.notima.api.fortnox.entities3.VoucherRow;
import org.notima.api.fortnox.entities3.VoucherRows;

public class TestGetVoucher extends FortnoxTest {
	
	private FinancialYearSubset fy;
	
	@Test
	public void testGetFinancialYear() throws Exception {
		
		fy = client.getFinancialYear(null);
		System.out.println(fy.getFromDate() + " - " + fy.getToDate());
		
	}
	
	@Test
	public void testGetVoucher() throws Exception {
		
		fy = client.getFinancialYear(null);
		Voucher voucher = null;
		try {
			voucher = client.getVoucher(fy.getId(), "A", 1);
			VoucherRows ir = voucher.getVoucherRows();
			List<VoucherRow> rows = ir.getVoucherRow();
			System.out.println(voucher.getTransactionDate());
			System.out.println(voucher.getComments());
			for (VoucherRow r : rows) {
				System.out.println("  " + r.getAccount() + " " + r.getDescription() + " " + (r.getDebit() - r.getCredit()));
			}
		} catch (FortnoxException e) {
			System.out.println(e.toString());
		}
	}
	

}
